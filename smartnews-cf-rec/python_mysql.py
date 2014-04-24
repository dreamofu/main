#!/usr/bin/env python
# -*- coding:utf-8 -*-

import sys, time
#import simplejson as json
import json
import MySQLdb

conn = None
def init_conn():
    global conn
    try:
        conn = MySQLdb.connect(host='10.81.39.68', port=5055, user='liuyonggang_r', \
                passwd='PtmWgW7QN2default_db',db='news_smart')
    except Exception, ex:
        print '[ERROR] create connection fail:%(ex)s' %locals()
    else:
        print '[INFO] create connection success.'
    return conn

init_conn()

def close_conn():
    try:
        if conn:
            conn.close()
    except Exception, ex:
        print '[ERROR] close conn fail:%(ex)s' %locals()


def select_data(cate_id):
    try:
        sql = 'select title from news_smart where url_sign=%d' %(cate_id)
        cur = conn.cursor()
        cur.execute(sql)
        res = cur.fetchone()    
        return res[0]
        #print 'key:%d, val_len:%d' % (cate_id, len(res[1]))
        #print res[1].decode('utf-8').encode('gbk')
    except Exception, ex:
        print '[ERROR] select data fail:%(ex)s' %locals()


def insert_data(cate_id, data_major, data_extra):
    try:
        sql = "insert into info_catenews (cate_id, data_major, data_extra) values (%d, '%s', '%s')" \
                % (cate_id, data_major, data_extra)
        cur = conn.cursor()
        cur.execute(sql)
        conn.commit()
        cur.close()
    except Exception, ex:
        print '[ERROR] insert data fail:%(ex)s' %locals()
    else:
        print '[INFO] insert key(%(cate_id)d) success.' %locals()


def query_encode(s):
    if isinstance(s, str):
        return 'str'
    elif isinstance(s, unicode):
        return 'unicode'
    else:
        return 'unknown'


if __name__ == '__main__':
    file = sys.argv[1]
    test_insert(file)
    #for i in range(100):
    #    test_select()
    close_conn()


