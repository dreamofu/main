#!./py27/python/bin/python
# coding:gbk

import os, sys
import mola_old as mola
import report_tool as report 
base_dir = os.path.split(os.path.realpath(__file__))[0]
conf_file = '%s/conf/main.conf' %base_dir

def get_conf(key):
    val = None
    mark = False
    if not os.path.exists(conf_file):
        sys.stderr.write('[ERROR] %s does not exist.\n' %conf_file)
        sys.exit(-1)
    for line in open(conf_file):
        if line.strip() == key:
            mark = True
        elif mark == True:
            val = line.strip()
            break
    return val

table = get_conf('mola_table')
if not table:
    sys.stderr.write('[ERROR] mola_table not set.\n')
    sys.exit(-1)
sys.stderr.write('[INFO] write data to %s...\n' %table)


def handle_group(user_str, item_list_str):
    if mola.set(table, user_str, item_list_str[0]):
        report.inc_counter('3rd', 'succ')
    else:
        report.inc_counter('3rd', 'fail')

last_key = None
group_data = []
for line in sys.stdin:
    line = line.strip()
    if not line:
        continue
    items = line.split('\t')
    if len(items) != 2:
        continue
    (user_str, item_list_str) = items

    if last_key == None:
        last_key = user_str
    elif last_key != user_str:
        handle_group(last_key, group_data)
        last_key = user_str
        group_data = []
    group_data.append(item_list_str)

if group_data:
    handle_group(last_key, group_data)

mola.destroy_adapter(table)
