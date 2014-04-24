#!/bin/bash
basedir=$(cd "$(dirname "$0")"; pwd)
source $basedir/colorprint.sh

# dataworks2.0 rp-rd
#ZK_HADOOP='/home/users/zhangxiang04/.dataworks/client-rd/hadoop/bin/hadoop'
# dataworks2.0 rp-product
ZK_HADOOP='/home/work/dataworks_client/v2/dataworks_client_v2/hadoop/bin/hadoop'

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

VERSION=$(get_conf "version")
if [ "$VERSION" == "" ]; then
    echo -e "$PREFIX_RED[ERROR] version not set.$SUFFIX" 
    exit -1
fi

JOB_NAME="smartnews_cf_$VERSION"
JOB_DIR="../$(basename $(pwd))"

# 删除已有zk任务, 提交zk任务
$ZK_HADOOP cr -delete $JOB_NAME
$ZK_HADOOP cr -submit $JOB_DIR -type SHELL -id $JOB_NAME

if [ $? -eq 0 ]; then
    echo -e "${PREFIX_GREEN}[INFO] Submit '$JOB_NAME' task succ.$SUFFIX"  
fi    

exit 0
