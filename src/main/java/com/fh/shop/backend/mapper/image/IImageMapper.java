package com.fh.shop.backend.mapper.image;

import com.fh.shop.backend.po.image.Image;

import java.util.List;

public interface IImageMapper {
    void addImage(List<Image> childImageList);

    List<Image> findProductImageList(Integer id);

    void delProductImage(List<Integer> idsList);

    void delImgByProductId(Integer id);

    List<Image> findBatchProductImage(List<Integer> idsList);

    void delImageBatch(List<Integer> idsList);

}
