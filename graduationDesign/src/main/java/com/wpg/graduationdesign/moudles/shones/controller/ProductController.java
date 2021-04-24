package com.wpg.graduationdesign.moudles.shones.controller;

import com.github.pagehelper.PageInfo;
import com.wpg.graduationdesign.moudles.shones.entity.Product;
import com.wpg.graduationdesign.moudles.shones.service.ProductService;
import com.wpg.graduationdesign.vo.Result;
import com.wpg.graduationdesign.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/shone")
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * 127.0.01./shone/product/products   ---post
     * {"currentPage":"1","pageSize":"5","keyword":"","orderBy":"","sort":""}
     */
    @PostMapping(value = "/product/products", consumes = "application/json", produces = "application/json")
    public PageInfo<Product> getProdcutsBySearchVo(@RequestBody SearchVo searchVo) {
        return productService.getProdcutsBySearchVo(searchVo);

    }

    /**
     * 添加信息
     * 127.0.0.1/shone/product/product   ---post
     */
    @PostMapping(value = "/product/product", consumes = "application/json", produces = "application/json")
    public Result<Product> insertProdcutByProduct(@RequestBody Product product) {
        return productService.insertProductByProduct(product);

    }


    /**
     * 获取用户信息
     * 127.0.0.1/shone/product/product/xxx   ---post
     */
    @PostMapping(value = "/product/product/{productId}", produces = "application/json")
    public Result<Product> selectProdcutByProductId(@PathVariable("productId") int productId) {
        return productService.selectProdcutByProductId(productId);

    }


    /**
     * 修改商品信息
     * 127.0.0.1/shone/product/product   ---put
     */
    @PutMapping(value = "/product/product", consumes = "application/json", produces = "application/json")
    public Result<Product> updateProdcutByProduct(@RequestBody Product product) {
        return productService.updateProdcutByProduct(product);

    }

    /**
     * 修改商品状态为停售
     * 127.0.0.1/shone/product/product_status ---put
     */
    @PutMapping(value = "/product/product_status")
    public Result<Product> updateProdcutStatusByProductId1(@RequestParam("productId") Integer productId,
                                                           @RequestParam("status") Integer status) {
        if (status == 1) {
            //改为停售
            return productService.updateProdcutStatus0ByProductId(productId);
        } else {
            //改为在售
            return productService.updateProdcutStatus1ByProductId(productId);
        }


    }

    /**
     * 获取路径
     *
     * @return
     */
    @PostMapping(value = "/product/product_main_img", consumes = "multipart/form-data", produces = "application/json")
    //文件名称一定要对称，和form中name的对应或者和append里的对应
    public Result<String> uploadFile(@RequestParam("file") MultipartFile file) {
        return productService.getProductMainImg(file);
    }


    /**
     * 获取附图路径
     */
    @PostMapping(value = "/product/product_fu_img", consumes = "multipart/form-data", produces = "application/json")
    //文件名称一定要对称，和form中name的对应或者和append里的对应
    public Result<String> upload_fu_File(@RequestParam("file") MultipartFile file) {
        return productService.getProduct_fu_Img(file);
    }


    /**
     * 添加其他附图片
     */
    @PostMapping(value = "/product/fu_img_up")
    public Result<String> addProductOtherImg(@RequestParam("productId") int productId,
                                             @RequestParam("fu_img1") String fu_img1,
                                             @RequestParam("fu_img2") String fu_img2,
                                             @RequestParam("fu_img3") String fu_img3) {
        return productService.addProductOtherImg(productId,fu_img1,fu_img2,fu_img3);
    }

}
