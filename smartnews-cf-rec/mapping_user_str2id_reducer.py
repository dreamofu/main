#!./py27/python/bin/python
# coding:gbk

import sys, os

# 全局用户编号
USER_ID = 1  
# 数据后缀标识
SUFFIX_DATA = os.getenv('suffix.data')
SUFFIX_MAPPING = os.getenv('suffix.mapping')
if not SUFFIX_DATA or not SUFFIX_MAPPING:
    sys.stderr.write('[ERROR] env variable suffix.data/suffix.mapping not set, set default.')
    SUFFIX_MAPPING = 'A'
    SUFFIX_DATA = 'B'

def handle_group(user_str, item_list):
    global USER_ID
    """
    为每个用户分配一个唯一的USER_ID，输出两个文件
    1. user_id \t user_str（映射文件）
    2. user_id \t item_str \t 1（已完成USER_ID映射的数据文件）
    """
    # output data
    for item_str in item_list:
        print '%d\t%s\t1#%s' %(USER_ID, item_str, SUFFIX_DATA)
    # output mapping
    print '%d\t%s#%s' %(USER_ID, user_str, SUFFIX_MAPPING)
    USER_ID += 1


last_key = None
group_data = []
for line in sys.stdin:
    line = line.strip()
    if not line:
        continue
    items = line.split('\t')
    if len(items) != 3:
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
    

