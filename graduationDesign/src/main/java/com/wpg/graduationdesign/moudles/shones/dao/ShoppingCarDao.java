package com.wpg.graduationdesign.moudles.shones.dao;

import com.wpg.graduationdesign.moudles.shones.entity.Brand;
import com.wpg.graduationdesign.moudles.shones.entity.Category;
import com.wpg.graduationdesign.moudles.shones.entity.Product;
import com.wpg.graduationdesign.moudles.shones.entity.ShoppingCar;
import com.wpg.graduationdesign.vo.SearchVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Administrator
 */
@Mapper
@Repository
public interface ShoppingCarDao {
    /**
     * 添加购物车
     *
     * @param shoppingCar s
     * @return b
     */
    @Insert("insert into shopping_car (u_id,p_id,p_name,num) values (#{uId},#{pId},#{pName},#{num})")
    Boolean addShoppingCarInfo(ShoppingCar shoppingCar);

    /**
     * 获取购物车信息
     *
     * @param userId   u
     * @return b
     */
    @Select("select * from shopping_car where u_id = #{userId}")
    @Results(id = "shoppingCarInfo", value = {
            //使用了p_id接受product后本身被占用，二次映射本身
            @Result(column = "p_id",property = "pId"),
            //用p_id做参数查找
            @Result(column = "p_id", property = "product",
                    javaType = Product.class,
                    //映射的包名.类名.方法名
                    many = @Many(select = "com.wpg.graduationdesign.moudles.shones.dao.ProductDao.selectProdcutByProductId")),
    })
    List<ShoppingCar> getShoppingCarInfoByUserId(@Param("userId") Integer userId);

    /**
     * 删除购物车
     * @param id id
     * @return b
     */
    @Delete("delete from shopping_car where id= #{id}")
    Boolean delShoppingCarInfo(@Param("id") Integer id);

    @Delete("<script> " +
            "delete from shopping_car "+
            "<where>"+
            "id in "+
            "<foreach collection=\"carsList\" index=\"index\" item=\"i\" separator=\",\" open=\"(\" close=\")\"> "
            +
            "#{i}"
            +
            "</foreach> " +
            "</where>"+
            "</script>")
    void delShoppingCars(@Param("carsList") List<Integer> carsList);
}
