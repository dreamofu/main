# coding:gbk

import sys

def inc_counter(group, key):
    '''
    ����JobCounter�ļ���
    '''
    sys.stderr.write('reporter:counter:%s,%s,%d\n' %(group, key, 1))


