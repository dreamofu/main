#!./py27/python/bin/python
# coding:gbk

import time
import datetime as dt


def is_n_days_ago(ts, n):
    '''
    判断时间戳ts是否为n天前的时间
    '''
    dt_now = dt.datetime.fromtimestamp(time.time())
    dt_check = dt.datetime.fromtimestamp(ts)
    if (dt_now - dt_check).days >= n:
        return True
    return False



if __name__ == '__main__':
    for i in range(1, 25):
        ts = time.time() - i * 3600
        print i, is_n_days_ago(ts, 1)

