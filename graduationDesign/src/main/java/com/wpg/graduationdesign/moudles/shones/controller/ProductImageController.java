package com.wpg.graduationdesign.moudles.shones.controller;

import com.github.pagehelper.PageInfo;
import com.wpg.graduationdesign.moudles.shones.entity.ProductImage;
import com.wpg.graduationdesign.moudles.shones.service.ProductImageService;
import com.wpg.graduationdesign.moudles.shones.service.ProductService;
import com.wpg.graduationdesign.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shone")
public class ProductImageController {
    @Autowired
    private ProductImageService productImageService;

    @PostMapping(value = "/product_other_image/product_other_images",consumes = "application/json",produces = "application/json")
    public PageInfo<ProductImage> getProductImageBySearchVo(@RequestBody SearchVo searchVo){
        return productImageService.getProductImageBySearchVo(searchVo);
    }
}
