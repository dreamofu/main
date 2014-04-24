#!./py27/python/bin/python
# coding:gbk

import sys, json
import report_tool

encoding = 'gbk'

# ¶¨ÒåÍ³¼ÆÍÆ¼ö½á¹ûÊýÄ¿µÄ×î´óÖµ
MAX_NUM_REC = 20
def stat_num_rec(group_data):
    '''
    Í³¼Æ×îÖÕÃ¿¸öÓÃ»§ÍÆ¼ö½á¹ûÊýÄ¿µÄ·Ö²¼
    '''
    if len(group_data) <= MAX_NUM_REC:
        report_tool.inc_counter('stat_num_rec', '%d' %len(group_data))
    else:
        report_tool.inc_counter('stat_num_rec', '%d' %MAX_NUM_REC)
    

# Ã¿¸öÓÃ»§×î´óµÄÍÆ¼ö½á¹ûÌõÊý
MAX_REC_PER_USER = 10

def handle_group(user_str, group_data):
    '''
    ¾ÛºÏÃ¿¸öÓÃ»§µÄÍÆ¼ö½á¹û£¬²¢Êä³ö
    @prarm group_data list(item_json_str)
    '''
    stat_num_rec(group_data)
    res = []
    for item_json_str in group_data:
        try:
            item_dict = json.loads(item_json_str, encoding)
            if not item_dict:
                raise Exception, 'json parse error.' 
            # val encoding
            tmp_item_dict = {}
            for key in item_dict:
                if isinstance(item_dict[key], unicode):
                    tmp_item_dict[key] = item_dict[key].encode(encoding)
                else:
                    tmp_item_dict[key] = item_dict[key]
            res.append(tmp_item_dict)
            if len(res) == MAX_REC_PER_USER:
                break
        except Exception, ex:
            report_tool.inc_counter('3rd', 'json_exp')
    if res:
        res_dict = {}
        res_dict['rec_list'] = res
        print '%s\t%s' %(user_str, json.dumps(res_dict, ensure_ascii=False).encode(encoding))


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
    (user_str, item_json_str) = items

    if last_key == None:
        last_key = user_str
    elif last_key != user_str:
        handle_group(last_key, group_data)
        last_key = user_str
        group_data = []
    group_data.append(item_json_str)

if group_data:
    handle_group(last_key, group_data)


