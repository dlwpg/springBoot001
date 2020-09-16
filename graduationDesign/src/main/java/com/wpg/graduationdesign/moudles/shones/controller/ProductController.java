package com.wpg.graduationdesign.moudles.shones.controller;

import com.github.pagehelper.PageInfo;
import com.wpg.graduationdesign.moudles.shones.entity.Product;
import com.wpg.graduationdesign.moudles.shones.service.ProductService;
import com.wpg.graduationdesign.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shone")
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * 127.0.01./shone/product/products   ---post
     *  {"currentPage":"1","pageSize":"5","keyword":"","orderBy":"","sort":""}
     */
    @PostMapping(value = "/product/products",consumes = "application/json",produces = "application/json")
    public PageInfo<Product> getProdcutsBySearchVo(@RequestBody SearchVo searchVo){
        return productService.getProdcutsBySearchVo(searchVo);

    }
}
