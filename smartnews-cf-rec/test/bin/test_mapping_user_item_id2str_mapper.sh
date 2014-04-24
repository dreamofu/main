#!/bin/bash

base_dir=$(cd "$(dirname "$0")"; pwd)
input_file="$base_dir/../data/mapping_user_item_id2str_mapper.input"
output_file="$base_dir/../data/mapping_user_item_id2str_mapper.output"
exp_output_file="$base_dir/../data/mapping_user_item_id2str_mapper.expect"
mapper_file="$base_dir/../../mapping_user_item_id2str_mapper.py"

cat $input_file | python $mapper_file > $output_file 2>/dev/null
diff -w -B $output_file $exp_output_file
exit $?
