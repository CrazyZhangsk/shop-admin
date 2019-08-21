package com.fh.shop.backend.api.product;

import com.fh.shop.backend.biz.product.IProductService;
import com.fh.shop.backend.common.ServerResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductAPI {

    @Resource(name = "productService")
    private IProductService productService;

    @RequestMapping(value="findProductList", method={RequestMethod.POST,RequestMethod.GET})
    public Object findProductList(String callback) {
        List<ProductVO> productList = productService.findProductList();
        if (StringUtils.isEmpty(callback)) {
            return ServerResponse.success(productList);
        }
        MappingJacksonValue jacksonValue = new MappingJacksonValue(ServerResponse.success(productList));
        jacksonValue.setJsonpFunction(callback);
        return jacksonValue;
    }



}
