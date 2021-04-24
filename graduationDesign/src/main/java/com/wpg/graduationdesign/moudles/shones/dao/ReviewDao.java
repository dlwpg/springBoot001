package com.wpg.graduationdesign.moudles.shones.dao;

import com.wpg.graduationdesign.moudles.shones.entity.*;
import com.wpg.graduationdesign.vo.SearchVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ReviewDao {
    @Select("<script>" +
            "select * from review "
            + "<where>"
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and (order_number like '%${keyWord}%') "
            + "</if>"
            + "</where>"
            + "<choose>"
            + "<when test= 'orderBy != \"\" and orderBy != null' >"
            + "order by ${orderBy} ${sort}"
            + "</when>"
            + "<otherwise> "
            + " order by review_id desc "
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    @Results( value = {
            //用uid做参数查找
            @Result(column = "uid", property = "user",
                    javaType = User.class,
                    //映射的包名.类名.方法名
                    many = @Many(select = "com.wpg.graduationdesign.moudles.shones.dao.UserDao.selectUserByUserId")),

            @Result(column = "pid", property = "product",
                    javaType = Product.class,
                    //映射的包名.类名.方法名
                    many = @Many(select = "com.wpg.graduationdesign.moudles.shones.dao.ProductDao.selectProdcutByProductId")),

            @Result(column = "order_number", property = "order",
                    javaType = Order.class,
                    //映射的包名.类名.方法名
                    many = @Many(select = "com.wpg.graduationdesign.moudles.shones.dao.OrderDao.getOrderByOderNumber")),
    })
    List<Review> getReviewsBySearchVo(SearchVo searchVo);

    @Insert("insert into review (order_number,uid,pid,content,content_level,createtime)" +
            "values (#{orderNumber},#{uid},#{pid},#{content},#{contentLevel},#{createtime})")
    Boolean saveReview(Review review);
}
