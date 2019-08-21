package com.fh.shop.backend;

import com.fh.shop.backend.brand.TestBrandService;
import com.fh.shop.backend.product.TestProductService;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
@RunWith(Suite.class)
@Suite.SuiteClasses({TestBrandService.class,TestProductService.class})
public class TestSuite {
}
//测试组件