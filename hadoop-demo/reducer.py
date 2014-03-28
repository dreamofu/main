#!./py27/python/bin/python
# coding:gbk

import sys

def handle_group(key, group_data):
    print '%s' %key


last_key = None
group_data = []
for line in sys.stdin:
    line = line.strip()
    if not line:
        continue
    items = line.split('\t')
    if len(items) != 2:
        continue
    (tag, null) = items

    if last_key == None:
        last_key = tag
    elif last_key != tag:
        handle_group(last_key, group_data)
        last_key = tag
        group_data = []
    group_data.append(null)

if group_data:
    handle_group(last_key, group_data)




