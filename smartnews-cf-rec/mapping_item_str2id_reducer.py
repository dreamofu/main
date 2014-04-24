#!./py27/python/bin/python
# coding:gbk

import sys, os

# ȫ����Ʒ���
ITEM_ID = 1  
# ���ݺ�׺��ʶ
SUFFIX_DATA = os.getenv('suffix.data')
SUFFIX_MAPPING = os.getenv('suffix.mapping')
if not SUFFIX_DATA or not SUFFIX_MAPPING:
    sys.stderr.write('[ERROR] env variable suffix.data/suffix.mapping not set, set default.')
    SUFFIX_MAPPING = 'A'
    SUFFIX_DATA = 'B'

def handle_group(item_str, user_list):
    global ITEM_ID
    """
    Ϊÿ����Ʒ����һ��Ψһ��ITEM_ID����������ļ�
    1. item_id \t item_str��ӳ���ļ���
    2. user_id \t item_id \t 1�������USER_ID/ITEM_IDӳ��������ļ���
    """
    # output data
    for user_id in user_list:
        print '%s\t%d\t1#%s' %(user_id, ITEM_ID, SUFFIX_DATA)
    # output mapping
    print '%d\t%s#%s' %(ITEM_ID, item_str, SUFFIX_MAPPING)
    ITEM_ID += 1


last_key = None
group_data = []
for line in sys.stdin:
    line = line.strip()
    if not line:
        continue
    items = line.split('\t')
    if len(items) != 2:
        continue
    (item_str, user_id) = items

    if last_key == None:
        last_key = item_str
    elif last_key != item_str:
        handle_group(last_key, group_data)
        last_key = item_str
        group_data = []
    group_data.append(user_id)

if group_data:
    handle_group(last_key, group_data)
