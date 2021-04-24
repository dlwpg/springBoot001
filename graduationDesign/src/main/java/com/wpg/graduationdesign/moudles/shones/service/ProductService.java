package com.wpg.graduationdesign.moudles.shones.service;

import com.github.pagehelper.PageInfo;
import com.wpg.graduationdesign.moudles.shones.entity.Product;
import com.wpg.graduationdesign.moudles.shones.entity.ProductImage;
import com.wpg.graduationdesign.vo.Result;
import com.wpg.graduationdesign.vo.SearchVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    Result<String> addProductOtherImg(int productId, String fu_img1, String fu_img2, String fu_img3);

    /**
     * 通过种类获取商品信息
     *
     * @param categoryId id
     * @param keywords   k
     * @return list
     */
    Result<List<Product>> getProductList(Integer categoryId, String keywords);

    /**
     * 通过商品id获取商品信息
     *
     * @param productId id
     * @return list
     */
    Result<Product> getProductInfo(Integer productId);

    /**
     * 通过商品id获取商品其他图片
     *
     * @param productId id
     * @return list
     */
    Result<List<ProductImage>> getProductImages(Integer productId);


    /**
     * 库存减少
     * @param pId p
     * @param num n
     * @return b
     */
    Boolean stockReduce(Integer pId,Integer num);
    /**
     * 库存+
     * @param pId p
     * @param num n
     * @return b
     */
    Boolean stockAdd(Integer pId, Integer num);

    Result<List<Product>> getProductsFor6();
}
