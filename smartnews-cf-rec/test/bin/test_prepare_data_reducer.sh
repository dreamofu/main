#!/bin/bash

base_dir=$(cd "$(dirname "$0")"; pwd)
input_file="$base_dir/../data/prepare_data_reducer.input"
output_file="$base_dir/../data/prepare_data_reducer.output"
exp_output_file="$base_dir/../data/prepare_data_reducer.expect"
reducer_file="$base_dir/../../prepare_data_reducer.py"

cat $input_file | sort | python $reducer_file > $output_file 2>/dev/null
diff -w -B $output_file $exp_output_file
exit $?
