#!/bin/bash

base_dir=$(cd "$(dirname "$0")"; pwd)
input_file="$base_dir/../data/mapping_item_str2id.input"
output_file="$base_dir/../data/mapping_item_str2id.output"
exp_output_file="$base_dir/../data/mapping_item_str2id.expect"
mapper_file="$base_dir/../../mapping_item_str2id_mapper.py"
reducer_file="$base_dir/../../mapping_item_str2id_reducer.py"

cat $input_file | python $mapper_file | sort | python $reducer_file > $output_file 2>/dev/null
diff -w -B $output_file $exp_output_file
exit $?
