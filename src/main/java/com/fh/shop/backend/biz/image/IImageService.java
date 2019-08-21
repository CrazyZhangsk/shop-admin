package com.fh.shop.backend.biz.image;

import com.fh.shop.backend.po.image.Image;

import java.util.List;

public interface IImageService {
    void addImage(List<Image> childImageList);

    List<Image> findProductImageList(Integer id);

    void delProductImage(List<Integer> idsList);

    void delImgByProductId(Integer id);

    List<Image> findBatchProductImage(String ids);

    void delImageBatch(String ids);

}
