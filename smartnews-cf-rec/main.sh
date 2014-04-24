#!/bin/bash
basedir=$(cd "$(dirname "$0")"; pwd)
source $basedir/colorprint.sh
hadoop="$HADOOP_HOME/bin/hadoop"
conf_file="$basedir/conf/main.conf"

get_conf(){
    mark="0"
    if [ -f $conf_file ]; then
        cat $conf_file | while read LINE; do
            if [ "$LINE" == "$1" ]; then
                mark="1"
            elif [ "$mark" == "1" ]; then
                echo $LINE
                return
            fi
        done
    else
        echo ""
    fi
}

# parse and check configuration
VERSION=$(get_conf "version")
OUTPUT_BASE_DIR=$(get_conf "output_base_dir")
if [ "$VERSION" == "" ] || [ "$OUTPUT_BASE_DIR" == "" ]; then
    echo -e "$PREFIX_RED[ERROR] output_dir/version not set.$SUFFIX"
    exit -1
fi    
OUTPUT_BASE_DIR=$OUTPUT_BASE_DIR.$VERSION
echo "output_base_dir ==> $OUTPUT_BASE_DIR"


# tar job archive and put to hdfs
update_job_archive(){
    echo "[INFO] update job archive..."
    tar_file='smartnews-cf-dataworks-job.tgz'
    tar_hdfs_path="$OUTPUT_BASE_DIR/job_archive/$tar_file"
    tar -czf $tar_file *
    $hadoop fs -rmr $tar_hdfs_path
    $hadoop fs -put $tar_file $tar_hdfs_path
    rm -f $tar_file
}

## 1.prepare_data
hist_days=3
log_base_dir="/user/rp-product/arch/smartnews/hdfs_importer/rp-smartnews-userdata-pipe_1"
concat_inputs(){
    out_str=''
    for((i=0;i<=$hist_days;i++)); do
        date_str=`date +%Y%m%d -d -${i}day`
        out_str="$out_str -input $log_base_dir/$date_str*/*.log"
    done
    echo $out_str
}
prepare_data_job(){
    echo "[INFO] running prepare_data_job..."

    date_str=$1
    pp_input=$(concat_inputs)
    pp_output="$OUTPUT_BASE_DIR/$date_str/prepare_data"
    $hadoop fs -rmr $pp_output
    $hadoop streaming \
        $pp_input \
        -output $pp_output \
        -mapper 'app/prepare_data_mapper.py' \
        -reducer 'app/prepare_data_reducer.py' \
        -cacheArchive hdfs:///user/rp-product/dcache/thirdparty/python2.7.tar.gz#py27 \
        -cacheArchive "hdfs://$tar_hdfs_path#app" \
        -jobconf mapred.job.name="smartnews_cf/prepare_data" \
        -jobconf mapred.reduce.tasks=20
    return $?
}


## 2.mapping_user_str2id
mapping_user_str2id_job(){
    echo "[INFO] running mapping_user_str2id_job..."
    
    date_str=$1
    mu_input="$OUTPUT_BASE_DIR/$date_str/prepare_data"
    mu_mapping_output="$OUTPUT_BASE_DIR/$date_str/user_id2str"
    mu_data_output="$OUTPUT_BASE_DIR/$date_str/mapping_user_str2id"
    suffix_mapping='A'
    suffix_data='B'
    $hadoop fs -rmr $mu_mapping_output $mu_data_output
    $hadoop streaming \
        -input $mu_input \
        -output $mu_mapping_output \
        -mapper '/bin/cat' \
        -reducer 'app/mapping_user_str2id_reducer.py' \
        -cacheArchive hdfs:///user/rp-product/dcache/thirdparty/python2.7.tar.gz#py27 \
        -cacheArchive "hdfs://$tar_hdfs_path#app" \
        -outputformat org.apache.hadoop.mapred.lib.SuffixMultipleTextOutputFormat \
        -cmdenv suffix.mapping=$suffix_mapping \
        -cmdenv suffix.data=$suffix_data \
        -jobconf mapred.job.name="smartnews_cf/mapping_user_str2id" \
        -jobconf mapred.reduce.tasks=1 \
        -jobconf mapred.job.priority=HIGH
    succ=$?
    # move $suffix_data output result to $mu_data_output
    $hadoop fs -mkdir $mu_data_output
    $hadoop fs -mv $mu_mapping_output/part-*-$suffix_data $mu_data_output
    return $succ
}


## 3.mapping_item_str2id
mapping_item_str2id_job(){
    echo "[INFO] running mapping_item_str2id_job..."

    date_str=$1
    mi_input="$OUTPUT_BASE_DIR/$date_str/mapping_user_str2id"
    mi_mapping_output="$OUTPUT_BASE_DIR/$date_str/item_id2str" 
    mi_data_output="$OUTPUT_BASE_DIR/$date_str/mapping_item_str2id"
    suffix_mapping='A'
    suffix_data='B'
    $hadoop fs -rmr $mi_mapping_output $mi_data_output
    $hadoop streaming \
        -input $mi_input \
        -output $mi_mapping_output \
        -mapper 'app/mapping_item_str2id_mapper.py' \
        -reducer 'app/mapping_item_str2id_reducer.py' \
        -cacheArchive hdfs:///user/rp-product/dcache/thirdparty/python2.7.tar.gz#py27 \
        -cacheArchive "hdfs://$tar_hdfs_path#app" \
        -outputformat org.apache.hadoop.mapred.lib.SuffixMultipleTextOutputFormat \
        -cmdenv suffix.mapping=$suffix_mapping \
        -cmdenv suffix.data=$suffix_data \
        -jobconf mapred.job.name="smartnews_cf/mapping_item_str2id" \
        -jobconf mapred.reduce.tasks=1 \
        -jobconf mapred.job.priority=HIGH
    succ=$?
    # move $suffix_data output result to $mi_data_output
    $hadoop fs -mkdir $mi_data_output
    $hadoop fs -mv $mi_mapping_output/part-*-$suffix_data $mi_data_output
    return $succ
}


## 4.mahout_cf
job_jar=$basedir/mahout.baidu.hpc.jar
mahout_cf_job(){
    echo "[INFO] running mahout_cf_job..."

    date_str=$1
    mc_input="$OUTPUT_BASE_DIR/$date_str/mapping_item_str2id"
    mc_output="$OUTPUT_BASE_DIR/$date_str/mahout_cf.raw"
    $hadoop fs -rmr $mc_output
    $hadoop jar $job_jar com.baidu.beidou.RecommendDriver recommend \
    -Dmapred.reduce.tasks=20 \
    -Dmapred.job.name="smartnews_cf/mahout-cf" \
    --input $mc_input \
    --output $mc_output \
    --numRecommendations 50 \
    --minPrefsPerUser 5 \
    --maxPrefsPerUser 30 \
    --similarityClassname SIMILARITY_COOCCURRENCE \
    --booleanData
}

## 5.mapping_user_item_id2str
mapping_user_item_id2str_job(){
    echo "[INFO] running mapping_user_item_id2str_job..."

    date_str=$1
    mui_input="$OUTPUT_BASE_DIR/$date_str/mahout_cf.raw/output"
    mui_output="$OUTPUT_BASE_DIR/$date_str/mahout_cf"
    $hadoop fs -rmr $mui_output 
    $hadoop streaming \
        -input $mui_input \
        -output $mui_output \
        -mapper 'app/mapping_user_item_id2str_mapper.py' \
        -reducer 'bin/cat' \
        -cacheArchive hdfs:///user/rp-product/dcache/thirdparty/python2.7.tar.gz#py27 \
        -cacheArchive "hdfs://$tar_hdfs_path#app" \
        -cacheFile "hdfs://$OUTPUT_BASE_DIR/$date_str/user_id2str/part-00000-A#user_id2str" \
        -cacheFile "hdfs://$OUTPUT_BASE_DIR/$date_str/item_id2str/part-00000-A#item_id2str" \
        -jobconf mapred.job.name="smartnews_cf/mapping_user_item_id2str" \
        -jobconf mapred.reduce.tasks=0 \
        -jobconf mapred.job.priority=HIGH
    return $?
}


## 6.pad_zixun_model_1
al2_base_dir="/user/rp-product/arch/smartnews/hdfs_importer/rp-smartnews-al2out-pipe_1"
concat_al2_inputs(){
    out_str=''
    for((i=0;i<=$hist_days;i++)); do
        date_str=`date +%Y%m%d -d -${i}day`
        out_str="$out_str -input $al2_base_dir/$date_str/*.log"
    done
    echo $out_str
}
pad_zixun_model_1_job(){
    echo '[INFO] running pad_zixun_model_1_job...'

    date_str=$1
    pz1_rec_input="$OUTPUT_BASE_DIR/$date_str/mahout_cf"
    pz1_newsdict_input=$(concat_al2_inputs)
    pz1_output="$OUTPUT_BASE_DIR/$date_str/pad_zixun_model_1"
    $hadoop fs -rmr $pz1_output
    $hadoop streaming \
        -input $pz1_rec_input \
        $pz1_newsdict_input \
        -output $pz1_output \
        -mapper 'app/pad_zixun_model_1_mapper.py' \
        -reducer 'app/pad_zixun_model_1_reducer.py' \
        -cacheArchive hdfs:///user/rp-product/dcache/thirdparty/python2.7.tar.gz#py27 \
        -cacheArchive "hdfs://$tar_hdfs_path#app" \
        -jobconf mapred.job.name="smartnews_cf/pad_zixun_model_1" \
        -jobconf mapred.reduce.tasks=50 \
        -jobconf mapred.job.priority=HIGH
    return $?
}

## 7.pad_zixun_model_2
pad_zixun_model_2_job(){
    echo '[INFO] running pad_zixun_model_2_job...'

    date_str=$1
    pz2_input="$OUTPUT_BASE_DIR/$date_str/pad_zixun_model_1"
    pz2_output="$OUTPUT_BASE_DIR/$date_str/pad_zixun_model_2"
    $hadoop fs -rmr $pz2_output
    $hadoop streaming \
        -input $pz2_input \
        -output $pz2_output \
        -mapper '/bin/cat' \
        -reducer 'app/pad_zixun_model_2_reducer.py' \
        -cacheArchive hdfs:///user/rp-product/dcache/thirdparty/python2.7.tar.gz#py27 \
        -cacheArchive "hdfs://$tar_hdfs_path#app" \
        -jobconf mapred.job.name="smartnews_cf/pad_zixun_model_2" \
        -jobconf mapred.reduce.tasks=20 \
        -jobconf mapred.job.priority=HIGH
    return $?
}


## 8.write_mola
write_mola_job(){
    echo '[INFO] running write_mola_job...'

    date_str=$1
    wm_input="$OUTPUT_BASE_DIR/$date_str/pad_zixun_model_2"
    wm_output="$OUTPUT_BASE_DIR/$date_str/write-mola"
    $hadoop fs -rmr $wm_output
    $hadoop streaming \
        -input $wm_input \
        -output $wm_output \
        -mapper '/bin/cat' \
        -reducer 'app/write_mola_reducer.py' \
        -cacheArchive hdfs:///user/rp-product/dcache/thirdparty/python2.7.tar.gz#py27 \
        -cacheArchive "hdfs://$tar_hdfs_path#app" \
        -jobconf mapred.job.name="smartnews_cf/write_mola" \
        -jobconf mapred.reduce.tasks=5 \
        -jobconf mapred.job.priority=HIGH
    return $?
}

# ¼ì²édatestrµÄ¸ñÊ½
check_datestr(){
   echo "$1" | grep '[12][0-9]\{3\}[0-1][0-9][0-3][0-9]_[012][0-9]' > /dev/null
   return $?
}


main(){
    usage_str='Usage: main YYYYmmdd_HH start_step end_step'
    if [ $# -ne 3 ]; then
        echo $usage_str
        return -1
    fi

    date_str=$1
    check_datestr $date_str
    is_datestr_valid=$?
    if [ $is_datestr_valid -ne 0 ]; then
        echo $usage_str
        return -1
    fi
    start_step=$2
    end_step=$3
    step=1

    update_job_archive

    if [ $step -ge $start_step ] && [ $step -le $end_step ]; then
        prepare_data_job $date_str
        succ=$?
        if [ $succ -ne 0 ]; then
            echo -e "$PREFIX_RED[ERROR] prepare_data_job fail.$SUFFIX"
            exit -1
        fi
    fi
    let step=step+1

    if [ $step -ge $start_step ] && [ $step -le $end_step ]; then
        mapping_user_str2id_job $date_str
        succ=$?
        if [ $succ -ne 0 ]; then
            echo -e "$PREFIX_RED[ERROR] mapping_user_str2id_job fail.$SUFFIX"
            exit -2
        fi
    fi    
    let step=step+1

    if [ $step -ge $start_step ] && [ $step -le $end_step ]; then
        mapping_item_str2id_job $date_str
        succ=$?
        if [ $succ -ne 0 ]; then
            echo -e "$PREFIX_RED[ERROR] mapping_item_str2id_job fail.$SUFFIX"
            exit 1
        fi
    fi
    let step=step+1

    if [ $step -ge $start_step ] && [ $step -le $end_step ]; then
        mahout_cf_job $date_str
        succ=$?
        if [ $succ -ne 0 ]; then
            echo -e "$PREFIX_RED[ERROR] mahout_cf_job fail.$SUFFIX"
            exit 1
        fi
    fi
    let step=step+1

    if [ $step -ge $start_step ] && [ $step -le $end_step ]; then
        mapping_user_item_id2str_job $date_str
        succ=$?
        if [ $succ -ne 0 ]; then
            echo -e "$PREFIX_RED[ERROR] mapping_user_item_id2str_job fail.$SUFFIX"
            exit 1
        fi
    fi
    let step=step+1

    if [ $step -ge $start_step ] && [ $step -le $end_step ]; then
        pad_zixun_model_1_job $date_str
        succ=$?
        if [ $succ -ne 0 ]; then
            echo -e "$PREFIX_RED[ERROR] pad_zixun_model_1_job fail.$SUFFIX"
            exit 1
        fi
    fi
    let step=step+1

    if [ $step -ge $start_step ] && [ $step -le $end_step ]; then
        pad_zixun_model_2_job $date_str
        succ=$?
        if [ $succ -ne 0 ]; then
            echo -e "$PREFIX_RED[ERROR] pad_zixun_model_2_job fail.$SUFFIX"
            exit 1
        fi
    fi
    let step=step+1

    if [ $step -ge $start_step ] && [ $step -le $end_step ]; then
        write_mola_job $date_str
        succ=$?
        if [ $succ -ne 0 ]; then
            echo -e "$PREFIX_RED[ERROR] write_mola_job fail.$SUFFIX"
            exit 1
        fi
    fi
    let step=step+1
}


# -------------------------------------------------------------------------------------
: << job_index
    1.prepare_data
    2.mapping_user_str2id
    3.mapping_item_str2id
    4.mahout_cf
    5.mapping_user_item_id2str
    6.pad_zixun_model_1
    7.pad_zixun_model_2
    8.write_mola
job_index

# usage: main YYYYmmdd_HH start_job_index end_job_index
main $(date +%Y%m%d_%H) 1 8
#main 20140331_18 8 8

exit $?

