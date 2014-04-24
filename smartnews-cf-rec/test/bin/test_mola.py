#!/usr/bin/env/python

import os,sys
base_dir = os.path.split(os.path.realpath(__file__))[0]
sys.path.insert(0, '%(base_dir)s/../..' %locals())
import mola
import unittest


class SmartNewsMolaTestCase(unittest.TestCase): 

    def test_mola(self):
        table = 'smartnews_cf'
        keys = ['000000000000000_20:02:AF:4D:F1:1F', 'null_ec:89:f5:b8:68:66']
        for key in keys:
            err_code, ret = mola.get(table, key)
            self.assertEquals(0, err_code)
        mola.destroy_adapter(table)


