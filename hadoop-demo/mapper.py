#!./py27/python/bin/python
# coding: gbk

import sys, json

for line in sys.stdin:
    line = line.strip()
    if not line:
        continue
    items = line.split('\t')
    if len(items) != 2:
        continue
    tag = items[0]
    print '%s\t%s' %(tag, 'None')

