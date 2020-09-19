package com.wpg.graduationdesign.moudles.shones.dao;

import com.wpg.graduationdesign.moudles.shones.entity.ProductImage;
import com.wpg.graduationdesign.vo.SearchVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
@Mapper
public interface ProductImageDao {

    @Select("select * from product_image where pid=#{productId}")
    List<ProductImage> getProductImageByProductId(int productId);

    @Insert("insert into product_image (pname,pid,otherimage) values(#{productName},#{productId},#{fu_img})")
//    @Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
    int addProductOtherImg(@RequestParam("productId") int productId,@RequestParam("productName") String productName,@RequestParam("fu_img") String fu_img);

    @Delete("delete from product_image where pid=#{productId}")
    int deleteProductImageByProductId(int productId);

    @Select("<script>" +
            "select * from product_image "
            + "<where>"
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and (pname like '%${keyWord}%') "
            + "</if>"
            + "</where>"
            + "<choose>"
            + "<when test= 'orderBy != \"\" and orderBy != null' >"
            + "order by ${orderBy} ${sort}"
            + "</when>"
            + "<otherwise> "
            + " order by id desc "
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    List<ProductImage> getProductImageBySearchVo(SearchVo searchVo);
}
