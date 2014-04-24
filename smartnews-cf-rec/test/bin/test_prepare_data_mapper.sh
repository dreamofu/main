#!/bin/bash

base_dir=$(cd "$(dirname "$0")"; pwd)
input_file="$base_dir/../data/prepare_data_mapper.input"
output_file="$base_dir/../data/prepare_data_mapper.output"
exp_output_file="$base_dir/../data/prepare_data_mapper.expect"
mapper_file="$base_dir/../../prepare_data_mapper.py"

cat $input_file | python $mapper_file > $output_file
diff -w -B $output_file $exp_output_file
exit $?
