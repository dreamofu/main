#!/usr/bin/env python
import mola

def is_gbk(a_str):
    ret = False
    if isinstance(a_str, str):
        try:
            a_str.decode('gbk')
            ret = True
        except:
            pass
    return ret


data = {}
for line in open('./data/sample.20140331_18.pad_zixun_model_2.part-00007'):
    line = line.strip()
    if not line:
        continue
    items = line.split('\t')
    if len(items) != 2:
        continue
    (mid, rec_list_json_str) = items
    print is_gbk(mid), is_gbk(rec_list_json_str)
    data[mid] = rec_list_json_str
print '[INFO] load data ok, len:%d' %len(data)

table = 'smartnews_cf'
succ = 0
fail = 0
for key,val in data.items():
    if mola.set(table, key, val):
        print '[INFO] write key[%s] succ.' %key
        succ += 1
    else:
        print '[ERROR] write key[%s] fail.' %key
        fail += 1
mola.destroy_adapter(table)
print 'Succ:%d, Fail:%d' %(succ, fail)
