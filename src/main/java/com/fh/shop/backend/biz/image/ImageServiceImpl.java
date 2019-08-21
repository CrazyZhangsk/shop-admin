package com.fh.shop.backend.biz.image;

import com.alibaba.dubbo.rpc.protocol.dubbo.telnet.ListTelnetHandler;
import com.fh.shop.backend.common.DataTableResult;
import com.fh.shop.backend.mapper.image.IImageMapper;
import com.fh.shop.backend.po.image.Image;
import com.fh.shop.backend.po.product.Product;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service(value="imageService")
public class ImageServiceImpl implements IImageService {

    @Autowired
    private IImageMapper imageMapper;

    @Override
    public void addImage(List<Image> childImageList) {
        imageMapper.addImage(childImageList);
    }

    @Override
    public List<Image> findProductImageList(Integer id) {
        return imageMapper.findProductImageList(id);
    }

    @Override
    public void delProductImage(List<Integer> idsList) {
        imageMapper.delProductImage(idsList);
    }

    @Override
    public void delImgByProductId(Integer id) {
        imageMapper.delImgByProductId(id);
    }

    @Override
    public List<Image> findBatchProductImage(String ids) {
        List<Integer> idsList = new ArrayList<>();
        if (StringUtils.isNotEmpty(ids)){
            String[] split = ids.split(",");
            for (String s : split) {
                idsList.add(Integer.valueOf(s));
            }
        }
        return imageMapper.findBatchProductImage(idsList);
    }

    @Override
    public void delImageBatch(String ids) {
        List<Integer> idsList = new ArrayList<>();
        if (StringUtils.isNotEmpty(ids)){
            String[] split = ids.split(",");
            for (String s : split) {
                idsList.add(Integer.valueOf(s));
            }
        }
        imageMapper.delImageBatch(idsList);
    }

}
