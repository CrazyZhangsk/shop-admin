package com.fh.shop.backend.brand;

import com.fh.shop.backend.biz.brand.IBrandService;
import com.fh.shop.backend.po.brand.Brand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-common.xml"})
public class TestBrandService extends AbstractJUnit4SpringContextTests {
    @Resource(name="brandService")
    private IBrandService brandService;

    @Test
    public void queryBrandList(){
        List<Brand> brandList = brandService.queryBrandList();
        System.out.println(brandList);
    }

    @Test
    public void insertBrand(){
        Brand brand = new Brand();
        brand.setBrandName("锤子科技");
        brandService.insertBrand(brand);
    }

    @Test
    public void deleteBatch(){
        String ids = "43";
        brandService.deleteBatch(ids);
    }

    @Test
    public void toUpdate(){
        Integer id = 24;
        Brand brand = brandService.toUpdate(id);
        System.out.println(brand);
    }

    @Test
    public void updateBrand(){
        Brand brand = new Brand();
        brand.setId(22);
        brand.setBrandName("诺基亚");
        brandService.updateBrand(brand);
    }
}
