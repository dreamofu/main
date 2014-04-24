#!./py27/python/bin/python
# coding:gbk

import os, sys, json
import report_tool

# 两类输入类型
input_types = ['zixun_model', 'rec_result']
# 两类输入路径标识
ZIXUN_MODEL_MARK = 'rp-smartnews-al2out'
REC_RESULT_MARK = 'mahout_cf'
# 需要从内容模型中抽取的字段
fields = ['url_sign', 'cont_sign', 'rela_sign', 'source_id', 'category', 'time', 'title_simhash', 'content_simhash', 'term', 'plsa']
encoding = 'gbk'

def parse_input_type(input_file):
    if input_file.find(ZIXUN_MODEL_MARK) != -1:
        return input_types[0]
    elif input_file.find(REC_RESULT_MARK) != -1:
        return input_types[1]
    return None

def handle_zixun_model(line):
    '''
    抽取url_sign/cont_sign/category，并输出
    url_sign \t {url_sign:xx, cont_sign:xx, category:xx}
    '''
    try:
        news_dict = json.loads(line, encoding)
        if not news_dict:
            raise Exception, 'json_loads_fail'
        res = {}
        for field in fields:
            if field not in news_dict:
                raise Exception, 'field_not_found_%s' %field
            if isinstance(news_dict[field], unicode):
                res[field] = news_dict[field].encode(encoding)
            else:
                res[field] = news_dict[field]
        print '%s\t%s' %(res['url_sign'], json.dumps(res, ensure_ascii=False).encode(encoding))
    except Exception, ex:
        report_tool.inc_counter('3rd', 'json_exp_%s' %ex)
        sys.stderr.write('[EXP] handle_zixun_model:%s\n' %ex)


def handle_rec_result(line):
    items = line.split('\t')
    if len(items) != 2:
        return
    user_str = items[0]
    item_list_str = items[1]
    for item_str in item_list_str.split(','):
        print '%s\t%s' %(item_str, user_str)


# main loop
for line in sys.stdin:
    line = line.strip()
    if not line:
        continue
    input_type = parse_input_type(os.getenv('map_input_file'))
    if input_type == input_types[0]:
        report_tool.inc_counter('3rd', 'num_zixun_model_line')
        handle_zixun_model(line)
    elif input_type == input_types[1]:
        report_tool.inc_counter('3rd', 'num_rec_result_line')
        handle_rec_result(line)

 
