#!/bin/bash

base_dir=$(cd "$(dirname "$0")"; pwd)
input_file="$base_dir/../data/mapping_user_str2id_reducer.input"
output_file="$base_dir/../data/mapping_user_str2id_reducer.output"
exp_output_file="$base_dir/../data/mapping_user_str2id_reducer.expect"
reducer_file="$base_dir/../../mapping_user_str2id_reducer.py"

cat $input_file | sort | python $reducer_file > $output_file 2>/dev/null
diff -w -B $output_file $exp_output_file
exit $?
