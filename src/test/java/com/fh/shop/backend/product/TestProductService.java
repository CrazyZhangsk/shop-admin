package com.fh.shop.backend.product;

import com.fh.shop.backend.biz.product.IProductService;
import com.fh.shop.backend.po.FileInfo;
import com.fh.shop.backend.po.brand.Brand;
import com.fh.shop.backend.po.product.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-common.xml"})
public class TestProductService extends AbstractJUnit4SpringContextTests {

    @Resource(name="productService")
    private IProductService productService;

    @Test
    public void add(){
        Product product = new Product();
        product.setProductName("小王八");
        product.setProductPrice(Float.valueOf(23));
        File file = new File("C:\\Users\\Shinelon\\Pictures\\Saved Pictures\\0002.jpg");
        FileInfo fileInfo = new FileInfo();
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            fileInfo.setFileName("0002.jpg");
            fileInfo.setFileSize((long) 20480);
            fileInfo.setIs(fileInputStream);
            String rootPath = "G:\\Feihu Education\\fh_MyeclipseWorkSpace\\FiveMonth\\admin_shop";
            List<FileInfo> fileInfoList = new ArrayList<>();
            fileInfoList.add(fileInfo);
            productService.add(product,fileInfo,rootPath,fileInfoList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if (fileInputStream==null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    @Test
    public void deleteBatch(){
        productService.deleteBatch("72,73,74");
    }

    @Test
    public void exportProductExcel(){
        Product product = new Product();
        List<Product> productList = productService.exportProductExcel(product);
        System.out.println(productList.size());
    }

    @Test
    public void queryProductInfo(){
        List<Product> productList = productService.queryProductInfo();
        System.out.println(productList.size());
    }

    @Test
    public void deleteProduct(){
        productService.deleteProduct(75);
    }

    @Test
    public void findBatchProductImage(){
        String ids = "71,70,69";
        List<Product> batchProductImage = productService.findBatchProductImage(ids);
        System.out.println(batchProductImage.size());
    }

    @Test
    public void toUpdateById(){
        Product product = productService.toUpdateById(71);
        System.out.println(product);
    }

    @Test
    public void updateProduct(){
        Product product = new Product();
        product.setId(70);
        product.setProductName("iphone 4s");
        product.setProductPrice((float) 500);
        Brand brand = new Brand();
        brand.setId(18);
        product.setBrand(brand);

        File file = new File("C:\\Users\\Shinelon\\Pictures\\Saved Pictures\\0002.jpg");
        FileInfo fileInfo = new FileInfo();
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            fileInfo.setFileName("0002.jpg");
            fileInfo.setFileSize((long) 20480);
            fileInfo.setIs(fileInputStream);
            String rootPath = "G:\\Feihu Education\\fh_MyeclipseWorkSpace\\FiveMonth\\admin_shop";
            List<FileInfo> fileInfoList = new ArrayList<>();
            fileInfoList.add(fileInfo);
            productService.updateProduct(product,fileInfo,fileInfoList,rootPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if (fileInputStream==null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
