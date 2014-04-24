#!./py27/python/bin/python
# coding:gbk

import mola, json, sys
import time_tool
#import python_mysql as mysql

table = 'zixun_content'
encoding = 'gbk'

def get_content_model(url_sign):
    '''
    依据url_sign获取对应的内容模型
    @return news_dict if succ, otherwise None.
    '''
    try:
        err_code, dict_str = mola.get(table, url_sign)
        if err_code != 0:
            return None
        news_dict = json.loads(dict_str, encoding)
        if not news_dict:
            return None
        return news_dict
    except Exception, ex:
        print '[EXP] %s' %ex
    return None


time_threshold = 2
def is_too_stale(news_dict):
    '''
    判断发布时间是否为2天以前(48小时之外)
    @return True if 发布时间为48小时之外/无法获取内容模型，otherwise False
    '''
    return False
    # ---- mola读太慢，直接返回，等待调试完后解注释 ----
    """
    if not news_dict:
        return True
    elif time_tool.is_n_days_ago(news_dict['time'], time_threshold):
        return True
    return False
    """

if __name__ == '__main__':
    num_succ = 0
    num_fail = 0
    for line in open(sys.argv[1]):
        line = line.strip()
        if not line:
            continue
        news_arr = json.loads(line, encoding)
        if not news_arr:
            print '[ERROR] json loads fail'
        else:
            news_arr = news_arr['nids']
            for item in news_arr:
                nid = item['nid']
                mark = int(item['display_strategy']['mark'])
                type = item['display_strategy']['type']
                if mark == 2:
                    print nid
                    """
                    print mysql.select_data(nid)
                    model = get_content_model(nid)
                    if model:
                        num_succ += 1
                        print model['title'].encode('gbk')
                    else:
                        num_fail += 1
                        # print '[ERROR] get_content_model fail.'
                    """
    #print 'num_succ:%d, num_fail:%d' %(num_succ, num_fail)

