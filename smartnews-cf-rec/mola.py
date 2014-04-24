#!/usr/bin/env python
# -*-coding:gbk-*-
# @author zhang,xiang
# @date 2013/11/20


import os, sys 
pwd = os.path.split(os.path.realpath(__file__))[0]
sys.path.append(pwd + '/lib')
import kvcache 

APB_OFFLINE_CONF_PATH = pwd + '/conf/ac-phone-browser.offline'  # offline
SN_CONF_PATH = pwd + '/conf/smartnews-cf.online'    # smartnews_cf online
ZX_CONF_PATH = pwd + '/conf/zixun_content.online'   # zixun_content online

CONF_FILE = 'kvcache.conf'
SO_PATH = pwd + '/lib'

# mola API
def create_kvcache_adapter(conf_path, conf_file, so_relative_path):
    return kvcache.create_kvcache_adapter(conf_path, conf_file, so_relative_path)

def destroy_kvcache_adapter(kvadapter):
    return kvcache.destroy_kvcache_adapter(kvadapter)

def kvcache_adapter_read_kv(kvadapter, key):
    return kvcache.kvcache_adapter_read_kv(kvadapter, key)

def kvcache_adapter_write_kv(kvadapter, key, value): 
    return kvcache.kvcache_adapter_write_kv(kvadapter, key, value)

def kvcache_adapter_read_kv_int(kvadapter, key):
    return kvcache.kvcache_adapter_read_kv_int(kvadapter, key)

def kvcache_adapter_write_kv_int(kvadapter, key, value): 
    return kvcache.kvcache_adapter_write_kv_int(kvadapter, key, value)


# global adapters
apb_offline_adapter = None
sn_adapter = None
zx_adapter = None

def __get_adapter(name):
    global apb_offline_adapter
    global sn_adapter
    global zx_adapter
    try:
        if name == 'ac_phone_browser_offline':
            if not apb_offline_adapter:
                apb_offline_adapter = create_kvcache_adapter(APB_OFFLINE_CONF_PATH, CONF_FILE, SO_PATH)
            return (apb_offline_adapter, )
        elif name == 'smartnews_cf':
            if not sn_adapter:
                sn_adapter = create_kvcache_adapter(SN_CONF_PATH, CONF_FILE, SO_PATH)
            return (sn_adapter, )
        elif name == 'zixun_content':
            if not zx_adapter:
                zx_adapter = create_kvcache_adapter(ZX_CONF_PATH, CONF_FILE, SO_PATH)
            return (zx_adapter, )
    except Exception, ex:
        print 'exception:%s' %ex


def destroy_adapter(name):
    global apb_offline_adapter
    global sn_adapter
    global zx_adapter
    try:
        if name == 'ac_phone_browser_offline':
            if apb_offline_adapter:
                destroy_kvcache_adapter(apb_offline_adapter)
        elif name == 'smartnews_cf':
            if sn_adapter:
                destroy_kvcache_adapter(sn_adapter)
        elif name == 'zixun_content':
            if zx_adapter:
                destroy_kvcache_adapter(zx_adapter)
    except Exception, ex:
        print 'exception:%s' %ex



def set(table, key, val):
    '''
    @return 失败False,成功True
    '''
    succ = True
    try:
        adapters = __get_adapter(table)
        if not adapters:
            raise Exception, 'get adapters failed.'
        for adapter in adapters:
            if kvcache_adapter_write_kv(adapter, key, val) != 0:
                succ = False
    except Exception, ex:
        print 'set kv error:%s' %ex
    return succ


def get(table, key):
    '''
    @return (errcode, val)
    读取失败 (-1, None)
    key不存在(-2, None)
    读取成功(0, val)
    '''
    errcode = -1
    val = None
    try:
        adapters = __get_adapter(table)
        if not adapters:
            raise Exception, 'get adapters failed.'
        for adapter in adapters:
            val = kvcache_adapter_read_kv(adapter, key)
            if val:
                errcode = 0
                break
    except Exception, ex:
        print 'get kv error:%s' %ex
    return (errcode, val)



if __name__ == '__main__':
    #table = 'ac_phone_browser_offline'
    table = 'smartnews_cf'
    keys = ['A1000030C236173_00:23:b1:50:8a:78', '355637056492226_40:0E:85:1A:7D:E0', '861411011654446_00:16:6d:f0:4d:f0']
    keys = ['000000000000000_20:02:AF:85:4B:71']
    for key in keys:
        #set(table, key, key+'_test_20140401')
        err_code, val = get(table, key)
        print val #val.decode('gbk').encode('gbk')
    destroy_adapter(table)

