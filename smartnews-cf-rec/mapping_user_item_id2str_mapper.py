#!./py27/python/bin/python
# coding:gbk

import sys, os, json
import zixun_tool 

def load_dict(dict_path):
    if not os.path.exists(dict_path):
        sys.stderr.write('[ERROR] dict file %s does not exist.\n' %(dict_path))
        return None
    res = {}
    for line in open(dict_path):
       line = line.strip()
       if not line:
           continue
       items = line.split('\t')
       if len(items) != 2:
           continue
       (x_id, x_str) = items
       res[x_id] = x_str
    sys.stderr.write('[INFO] load %s succ, items:%d.\n' %(dict_path, len(res)))
    return res

user_dict = load_dict('./user_id2str')
item_dict = load_dict('./item_id2str')

if not user_dict or not item_dict:
    sys.stderr.write('[ERROR] user_dict/item_dict load fail.')
    sys.exit(-1)


def extract_fileds(news_dict):
    '''
    返回一个字典，包含url_sign, cont_sign, category字段
    '''
    res = {}
    res['url_sign'] = news_dict['url_sign']
    res['cont_sign'] = news_dict['cont_sign']
    res['category'] = news_dict['category']
    return res

for line in sys.stdin:
    line = line.strip()
    if not line:
        continue
    items = line.split('\t')
    if len(items) != 2:
        continue
    (user_id, item_id_list) = items
    if user_id not in user_dict:
        continue
    user_str = user_dict[user_id]
    item_str_list = []
    for item_id in item_id_list[1:-1].split(','):
        if item_id in item_dict:
            item_str = item_dict[item_id]
            item_str_list.append(item_str)
    if len(item_str_list) > 0:
        print '%s\t%s' %(user_str, ','.join(item_str_list))


