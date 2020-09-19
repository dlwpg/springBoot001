package com.wpg.graduationdesign.moudles.shones.service;

import com.github.pagehelper.PageInfo;
import com.wpg.graduationdesign.moudles.shones.entity.Product;
import com.wpg.graduationdesign.vo.Result;
import com.wpg.graduationdesign.vo.SearchVo;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {
    //查询所有商品信息
    PageInfo<Product> getProdcutsBySearchVo(SearchVo searchVo);

    Result<String> getProductMainImg(MultipartFile file);

    Result<Product> insertProductByProduct(Product product);

    Result<Product> selectProdcutByProductId(int productId);

    Result<Product> updateProdcutByProduct(Product product);

    //改为停售
    Result<Product> updateProdcutStatus0ByProductId(Integer productId);
    //改为在售
    Result<Product> updateProdcutStatus1ByProductId(Integer productId);

    //获取副图片路径
    Result<String> getProduct_fu_Img(MultipartFile file);

    //插入商品其他副图片
    Result<String> addProductOtherImg(int productId,String fu_img1, String fu_img2, String fu_img3);
}
