#!./py27/python/bin/python
# coding:gbk

import sys, json

# 点击行为的命令编号
CLICK_ACTION_CMD_ID = 3
# 输入文件编码
ENCODING = 'gbk'


def is_valid(id):
    '''
    若包含空格，则不合法
    '''
    if ' ' in id or '\t' in id:
        return False
    return True

def handle_line(line):
    '''
    @return (user_id, item_id) if a valid click action, otherwise None.
    '''
    try:
        a_dict = json.loads(line, ENCODING)
        if 'data' in a_dict and 'cmdid' in a_dict['data'] and 'nid' in a_dict['data']['data']:
            user_id = a_dict['data']['imei']
            cmd_id = int(a_dict['data']['cmdid'])
            item_id = a_dict['data']['data']['nid']
            if cmd_id == CLICK_ACTION_CMD_ID and is_valid(user_id) and is_valid(item_id):
                return (user_id, item_id)
    except Exception, ex:
        print '[EXP] %s' %ex
    return None


for line in sys.stdin:
    line = line.strip()
    if not line:
        continue
    res = handle_line(line)
    if res:
        (user_id, item_id) = res
        print '%s\t%s\t1' % (user_id, item_id)



