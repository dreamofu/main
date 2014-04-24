#!./py27/python/bin/python
# coding:gbk

import sys

for line in sys.stdin:
    line = line.strip()
    if not line:
        continue
    items = line.split('\t')
    if len(items) != 3:
        continue
    (user_id, item_id, cnt) = items
    print '%s,%s,1' % (user_id, item_id)


