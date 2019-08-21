package com.fh.shop.backend.po.image;

import com.fh.shop.backend.po.product.Product;

import java.io.Serializable;

public class Image implements Serializable {
    private Integer id;
    private Product product = new Product();
    private String imagePath;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
