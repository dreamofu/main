#!/usr/bin/env python
import os
import PasserellePy
base_dir = os.path.split(os.path.realpath(__file__))[0]

# smartnews_cf config path
smartnews_cf_conf_path = '%s/conf/smartnews_cf' %base_dir

smartnew_cf_adapter = None

def get_adapter(name):
    global smartnew_cf_adapter
    try:
        if name == 'smartnews_cf':
            if not smartnew_cf_adapter:
                smartnew_cf_adapter = PasserellePy.init_client(smartnews_cf_conf_path, \
                    "sofa_config.xml", "log.conf", "client.conf")
            return smartnew_cf_adapter
    except Exception, ex:   
        print 'get_adapter exception:%s' %ex

def destroy_adapter(name):
    global smartnew_cf_adapter
    try:
        if name == 'smartnews_cf' and smartnew_cf_adapter:
            PasserellePy.destroy_client(smartnew_cf_adapter)
    except Exception, ex:       
        print 'exception:%s' %ex   

def set(table, key, val):
    succ = True
    try:
        adapter = get_adapter(table)
        if not adapter:
            raise Exception, 'get adapter failed.'
        PasserellePy.transfer_data_with_singlekv(adapter, key, val)
    except Exception, ex:
        print 'set kv error:%s' %ex
    return succ

def get(table, key):
    raise Exception, 'get opeartion not supported.'


if __name__ == '__main__':
    table = 'smartnews_cf'
    key = 'test_random'
    val = 'random_test'
    set(table, key, val)
    destroy_adapter(table)
