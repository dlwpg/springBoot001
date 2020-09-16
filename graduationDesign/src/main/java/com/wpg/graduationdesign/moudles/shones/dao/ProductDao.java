package com.wpg.graduationdesign.moudles.shones.dao;

import com.wpg.graduationdesign.moudles.shones.entity.Brand;
import com.wpg.graduationdesign.moudles.shones.entity.Category;
import com.wpg.graduationdesign.moudles.shones.entity.Product;
import com.wpg.graduationdesign.vo.SearchVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ProductDao {
    //
    @Select("<script>" +
            "select * from product "
            + "<where>"
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and (product_name like '%${keyWord}%') "
            + "</if>"
            + "</where>"
            + "<choose>"
            + "<when test= 'orderBy != \"\" and orderBy != null' >"
            + "order by ${orderBy} ${sort}"
            + "</when>"
            + "<otherwise> "
            + " order by product_id desc "
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    @Results(id = "products",value = {
            //用product_type做参数查找
            @Result(column = "product_type",property = "category",
                    javaType = Category.class,
                    //映射的包名.类名.方法名
                    many = @Many(select = "com.wpg.graduationdesign.moudles.shones.dao.CategoryDao.getCategoryByCategoryId")),

            @Result(column = "b_id",property = "brand",
                    javaType = Brand.class,
                    //映射的包名.类名.方法名
                    many = @Many(select = "com.wpg.graduationdesign.moudles.shones.dao.BrandDao.getBrandByBandId"))

    })
    List<Product> getProdcutsBySearchVo(SearchVo searchVo);
}
