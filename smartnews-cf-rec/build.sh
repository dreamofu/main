#!/bin/bash

base_dir=$(cd "$(dirname "$0")"; pwd)
out_dir=$base_dir/output
tmp_out_dir=$base_dir/../output.smartnews_cf

mkdir -p $tmp_out_dir
cp -r * $tmp_out_dir
mv $tmp_out_dir $out_dir

echo -e '[INFO] build success.'
