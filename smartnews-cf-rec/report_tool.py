# coding:gbk

import sys

def inc_counter(group, key):
    '''
    更新JobCounter的计数
    '''
    sys.stderr.write('reporter:counter:%s,%s,%d\n' %(group, key, 1))


