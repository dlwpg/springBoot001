package com.wpg.graduationdesign.moudles.shones.controller.qt;

import com.wpg.graduationdesign.moudles.shones.entity.Product;
import com.wpg.graduationdesign.moudles.shones.entity.ProductImage;
import com.wpg.graduationdesign.moudles.shones.service.ProductService;
import com.wpg.graduationdesign.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("/shone_qt/sale")
public class SaleQtController {

    @Autowired
    private ProductService productService;

    /**
     * 通过种类获取商品信息
     * @param categoryId id
     * @return list
     */
    @GetMapping("/shone")
    public Result<List<Product>> getProductList(@RequestParam("categoryId")  Integer categoryId,@RequestParam("keywords")  String keywords){
        return productService.getProductList(categoryId,keywords);
    }

    /**
     * 通过商品id获取商品信息
     * @param productId id
     * @return list
     */
    @GetMapping("/shone/info")
    public Result<Product> getProductInfo(@RequestParam("productId")  Integer productId){
        return productService.getProductInfo(productId);
    }

    /**
     * 通过商品id获取商品其他图片
     * @param productId id
     * @return list
     */
    @GetMapping("/shone/images")
    public Result<List<ProductImage>> getProductImages(@RequestParam("productId")  Integer productId){
        return productService.getProductImages(productId);
    }

    /**
     * 获取6条商品信息
     */
    @GetMapping("/shone/products")
    public Result<List<Product>> getProductsFor6(){
        return productService.getProductsFor6();
    }
}
