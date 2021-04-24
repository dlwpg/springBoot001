package com.wpg.graduationdesign.moudles.shones.dao;

import com.wpg.graduationdesign.moudles.shones.entity.Brand;
import com.wpg.graduationdesign.moudles.shones.entity.Category;
import com.wpg.graduationdesign.moudles.shones.entity.Product;
import com.wpg.graduationdesign.moudles.shones.entity.ProductImage;
import com.wpg.graduationdesign.vo.SearchVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
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
    @Results(id = "products", value = {
            //用product_type做参数查找
            @Result(column = "product_type", property = "category",
                    javaType = Category.class,
                    //映射的包名.类名.方法名
                    many = @Many(select = "com.wpg.graduationdesign.moudles.shones.dao.CategoryDao.getCategoryByCategoryId")),

            @Result(column = "b_id", property = "brand",
                    javaType = Brand.class,
                    //映射的包名.类名.方法名
                    many = @Many(select = "com.wpg.graduationdesign.moudles.shones.dao.BrandDao.getBrandByBandId"))

    })
    List<Product> getProdcutsBySearchVo(SearchVo searchVo);

    //插入商品信息
    @Insert("insert into product (product_name,description,original_price,promote_price,stock,main_image,product_type" +
            ",product_status,b_id,createtime) values(#{productName},#{description},#{originalPrice},#{promotePrice},#{stock}" +
            ",#{mainImage},#{productType},#{productStatus},#{bId},#{createtime})")
    @Options(useGeneratedKeys = true, keyColumn = "product_id", keyProperty = "productId")
    int insertProductByProduct(Product product);

    //通过id获取所有信息
    @Select("select * from product where product_id=#{productId}")
    @ResultMap(value = "products")
    Product selectProdcutByProductId(int productId);

    //修改信息
    @Update("update product set product_name=#{productName}, description=#{description},original_price=#{originalPrice}," +
            "promote_price=#{promotePrice},stock=#{stock},main_image=#{mainImage},product_type=#{productType}," +
            "product_status=#{productStatus},b_id=#{bId} where product_id=#{productId}")
    void updateProdcutByProduct(Product product);

    //停售
    @Update("update product set product_status='停售' where product_id=#{productId}")
    void updateProdcutStatus0ByProductId(Integer productId);

    //在售
    @Update("update product set product_status='在售' where product_id=#{productId}")
    void updateProdcutStatus1ByProductId(Integer productId);

    @Select("select product_name from product where product_id=#{productId}")
    String selectProdcutNameByProductId(int productId);

    /**
     * 通过种类获取商品信息
     * @param categoryId id
     * @return list
     */
    @Select("select * from product where product_type = #{categoryId} and product_status ='在售' order By promote_price ASC")
    List<Product> getProductList(@Param("categoryId") Integer categoryId);

    /**
     * 通过种类获取商品信息
     * @param categoryId id
     * @param keywords k
     * @return list
     */
    @Select("select * from product where product_type = #{categoryId} and product_name like ('%${keywords}%') and product_status ='在售' order By promote_price ASC")
    List<Product> getProductLists(@Param("categoryId") Integer categoryId,@Param("keywords") String keywords);

    /**
     * 通过商品id获取商品信息
     * @param productId id
     * @return list
     */
    @Select("select * from product where product_id = #{productId} and product_status ='在售'")
    Product getProductInfo(@Param("productId") Integer productId);


    /**
     * 通过商品id获取商品其他图片
     *
     * @param pId id
     * @return list
     */
    @Select("select * from product_image where pid = #{pId}")
    List<ProductImage> getProductImages(@Param("pId")Integer pId);

    /**
     * 减少库存
     * @param pId p
     * @param stock s
     * @return b
     */
    @Update("update product set stock = #{stock} where product_id = #{pId}")
    Boolean stockReduce(@Param("pId") Integer pId,@Param("stock") Integer stock);

    /**
     * +
     * @param pId p
     * @param stock s
     * @return b
     */
    @Update("update product set stock = #{stock} where product_id = #{pId}")
    Boolean stockAdd(Integer pId, Integer stock);


    @Select("select * from product where product_status ='在售' limit 6")
    List<Product> getProductsFor6();
}
