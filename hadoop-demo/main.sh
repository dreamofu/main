#!/bin/bash

hadoop="$HADOOP_HOME/bin/hadoop"
input='/user/rp-rd/zhangxiang04/tag-news.v1.0/output/2014032*/*/merge'
output="/user/rp-rd/zhangxiang04/test/get-all-subscribe-tags"

# tar job archive, put to HDFS
job_archive="./get-all-subscribe-tags.tgz"
hdfs_job_archive="/user/rp-rd/zhangxiang04/test/get-all-subscribe-tags.tgz"
tar -czf $job_archive *.py 
$hadoop fs -put $job_archive $hdfs_job_archive
rm -rf $job_archive

# submit job
$hadoop fs -rmr $output
$hadoop streaming \
    -input $input \
    -output $output \
    -mapper 'app/mapper.py' \
    -reducer 'app/reducer.py' \
    -cacheArchive hdfs:///user/rp-product/dcache/thirdparty/python2.7.tar.gz#py27 \
    -cacheArchive hdfs://$hdfs_job_archive#app \
    -jobconf mapred.job.name="get-all-subscribe-tags" \
    -jobconf mapred.reduce.tasks=20

