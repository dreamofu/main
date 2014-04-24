#!./py27/python/bin/python
# coding:gbk

import sys, os
import report_tool as report

min_perf_per_user = 5
def handle_group(user_str, item_str_list):
    '''
    将少于最小评分个数的用户去掉
    '''
    report.inc_counter('3rd', '%d' %len(item_str_list))
    if len(item_str_list) < min_perf_per_user:
        return
    for item_str in item_str_list:
        print '%s\t%s\t1' %(user_str, item_str)


last_key = None
group_data = []
for line in sys.stdin:
    line = line.strip()
    if not line:
        report.inc_counter('3rd', 'not_line')
        continue
    items = line.split('\t')
    if len(items) != 3:
        report.inc_counter('3rd', 'len_items_ne_3')
        continue
    (user_str, item_str, cnt) = items

    if last_key == None:
        last_key = user_str
    elif last_key != user_str:
        handle_group(last_key, group_data)
        last_key = user_str
        group_data = []
    group_data.append(item_str)

if group_data:
    handle_group(last_key, group_data)
