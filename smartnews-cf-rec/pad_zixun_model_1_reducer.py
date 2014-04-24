#!./py27/python/bin/python
# coding:gbk

import sys, json
import report_tool

def handle_group(item_str, group_data):
    if len(group_data) < 2:
        report_tool.inc_counter('3rd', 'len_group_data_lt_2')
        return
    zixun_model_str = None
    user_str_list = []
    for data in group_data:
        # check it whether a json or not
        if data.startswith('{') and data.endswith('}'):
            zixun_model_str = data
        else:
            user_str_list.append(data)
    if not zixun_model_str or not user_str_list:
        report_tool.inc_counter('3rd', 'miss_zixun_model_or_user_str')
        return
    report_tool.inc_counter('3rd', 'num_valid_users')
    for user_str in user_str_list:
        print '%s\t%s' %(user_str, zixun_model_str)


## main loop
last_key = None
group_data = []
for line in sys.stdin:
    line = line.strip()
    if not line:
        report_tool.inc_counter('3rd', 'not_line')
        continue
    items = line.split('\t')
    if len(items) != 2:
        report_tool.inc_counter('3rd', 'len_items_ne_2')
        continue
    (item_str, data_str) = items

    if last_key == None:
        last_key = item_str
    elif last_key != item_str:
        handle_group(last_key, group_data)
        last_key = item_str
        group_data = []
    group_data.append(data_str)

if group_data:
    handle_group(last_key, group_data)


