package com.fh.shop.backend;

import com.fh.shop.backend.biz.brand.IBrandService;
import com.fh.shop.backend.po.brand.Brand;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TestMainDao {
    /*通过main方法进行测试*/
    public static void main(String[] args) {
        String[] source = {"classpath:spring/spring-common.xml"};
        //通过ClassPathXmlApplicationContext加载spring的配置文件
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(source);
        //通过getBean（）来获取实例化好的bean
        IBrandService brandService = (IBrandService) context.getBean("brandService");
        List<Brand> brandList = brandService.queryBrandList();
        System.out.println(brandList);
    }
}
