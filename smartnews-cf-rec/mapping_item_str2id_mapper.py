#!./py27/python/bin/python
# coding:gbk

import sys, json

for line in sys.stdin:
    line = line.strip()
    if not line:
        continue
    items = line.split('\t')
    if len(items) != 3:
        continue
    (user_id, item_str, cnt) = items
    print '%s\t%s' % (item_str, user_id)


