#!/usr/bin/env python
# coding: gbk
# author: zhangxiang04@baidu.com

import os
import unittest


class SmartNewsCFTestCase(unittest.TestCase):
    
    def setUp(self):
        pass

    def tearDown(self):
        pass

    def test_prepare_data_mapper(self):
        exit_code = os.system('sh test_prepare_data_mapper.sh') >> 8
        self.assertEquals(0, exit_code)
    
    def test_prepare_data_reducer(self):
        exit_code = os.system('sh test_prepare_data_reducer.sh') >> 8
        self.assertEquals(0, exit_code)

    def test_mapping_user_str2id_reducer(self):
        exit_code = os.system('sh test_mapping_user_str2id_reducer.sh') >> 8
        self.assertEquals(0, exit_code)

    def test_mapping_item_str2id(self):
        exit_code = os.system('sh test_mapping_item_str2id.sh') >> 8
        self.assertEquals(0, exit_code)

    def test_mapping_user_item_id2str_mapper(self):
        exit_code = os.system('sh test_mapping_user_item_id2str_mapper.sh') >> 8
        self.assertEquals(0, exit_code)
