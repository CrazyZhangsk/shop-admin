package com.fh.shop.backend.controller.image;

import com.fh.shop.backend.biz.image.IImageService;
import com.fh.shop.backend.po.image.Image;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/image")
public class ImageController {
    @Resource(name="imageService")
    private IImageService imageService;

    @RequestMapping("findProductImage/{id}")
    public String findProductImage(@PathVariable Integer id, ModelMap retMap){
       List<Image> imageList = imageService.findProductImageList(id);
       retMap.put("imageList",imageList);
       return "product/image";
    }
}
