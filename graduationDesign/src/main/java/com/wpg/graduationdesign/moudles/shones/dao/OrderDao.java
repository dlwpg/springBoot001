package com.wpg.graduationdesign.moudles.shones.dao;

import com.wpg.graduationdesign.moudles.shones.entity.Brand;
import com.wpg.graduationdesign.moudles.shones.entity.Category;
import com.wpg.graduationdesign.moudles.shones.entity.Order;
import com.wpg.graduationdesign.vo.SearchVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OrderDao {
    //查所有
    @Select("<script>" +
            "select * from `order` "
            + "<where>"
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and ( order_product_name like '%${keyWord}%') "
            + "</if>"
            + "</where>"
            + "<choose>"
            + "<when test= 'orderBy != \"\" and orderBy != null' >"
            + "order by ${orderBy} ${sort}"
            + "</when>"
            + "<otherwise> "
            + " ORDER BY order_id desc "
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    @Results(id = "product_type", value = {
            //用order_product_type做参数查找,用category做映射
            @Result(column = "order_product_type", property = "category",
                    javaType = Category.class,
                    //映射的包名.类名.方法名
                    many = @Many(select = "com.wpg.graduationdesign.moudles.shones" +
                            ".dao.CategoryDao.getCategoryByCategoryId")),
    })
    List<Order> getOrdersBySearchVo(SearchVo searchVo);

    //查已支付
    @Select("<script>" +
            "select * from `order` "
            + "<where>"
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and ( order_product_name like '%${keyWord}%') "
            + "</if>"
            + " and ( order_payment_status = 1 )"
            + "</where>"
            + "<choose>"
            + "<when test= 'orderBy != \"\" and orderBy != null' >"
            + "order by ${orderBy} ${sort}"
            + "</when>"
            + "<otherwise> "
            + " ORDER BY order_id desc "
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    @ResultMap(value = "product_type")
    List<Order> getOrdersPayment_1_BySearchVo(SearchVo searchVo);

    //查未支付
    @Select("<script>" +
            "select * from `order` "
            + "<where>"
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and ( order_product_name like '%${keyWord}%') "
            + "</if>"
            + " and ( order_payment_status = 0 )"
            + "</where>"
            + "<choose>"
            + "<when test= 'orderBy != \"\" and orderBy != null' >"
            + "order by ${orderBy} ${sort}"
            + "</when>"
            + "<otherwise> "
            + " ORDER BY order_id desc "
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    @ResultMap(value = "product_type")
    List<Order> getOrdersPayment_0_BySearchVo(SearchVo searchVo);

    //查待发货
    @Select("<script>" +
            "select * from `order` "
            + "<where>"
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and ( order_product_name like '%${keyWord}%') "
            + "</if>"
            + " and ( order_send_status = 0 )"
            + "</where>"
            + "<choose>"
            + "<when test= 'orderBy != \"\" and orderBy != null' >"
            + "order by ${orderBy} ${sort}"
            + "</when>"
            + "<otherwise> "
            + " ORDER BY order_id desc "
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    @ResultMap(value = "product_type")
    List<Order> getOrdersSend_0_BySearchVo(SearchVo searchVo);

    //查已发货
    @Select("<script>" +
            "select * from `order` "
            + "<where>"
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and ( order_product_name like '%${keyWord}%') "
            + "</if>"
            + " and ( order_send_status = 1 )"
            + "</where>"
            + "<choose>"
            + "<when test= 'orderBy != \"\" and orderBy != null' >"
            + "order by ${orderBy} ${sort}"
            + "</when>"
            + "<otherwise> "
            + " ORDER BY order_id desc "
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    @ResultMap(value = "product_type")
    List<Order> getOrdersSend_1_BySearchVo(SearchVo searchVo);

    //查未发起退货订单
    @Select("<script>" +
            "select * from `order` "
            + "<where>"
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and ( order_product_name like '%${keyWord}%') "
            + "</if>"
            + " and ( order_back_money_status = 0 )"
            + "</where>"
            + "<choose>"
            + "<when test= 'orderBy != \"\" and orderBy != null' >"
            + "order by ${orderBy} ${sort}"
            + "</when>"
            + "<otherwise> "
            + " ORDER BY order_id desc "
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    @ResultMap(value = "product_type")
    List<Order> getOrdersBack_money_0_BySearchVo(SearchVo searchVo);

    //查已发起退款订单
    @Select("<script>" +
            "select * from `order` "
            + "<where>"
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and ( order_product_name like '%${keyWord}%') "
            + "</if>"
            + " and ( order_back_money_status = 1 )"
            + "</where>"
            + "<choose>"
            + "<when test= 'orderBy != \"\" and orderBy != null' >"
            + "order by ${orderBy} ${sort}"
            + "</when>"
            + "<otherwise> "
            + " ORDER BY order_id desc "
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    @ResultMap(value = "product_type")
    List<Order> getOrdersBack_money_1_BySearchVo(SearchVo searchVo);


    //查未收货
    @Select("<script>" +
            "select * from `order` "
            + "<where>"
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and ( order_product_name like '%${keyWord}%') "
            + "</if>"
            + " and ( order_confirm_status = 0 )"
            + "</where>"
            + "<choose>"
            + "<when test= 'orderBy != \"\" and orderBy != null' >"
            + "order by ${orderBy} ${sort}"
            + "</when>"
            + "<otherwise> "
            + " ORDER BY order_id desc "
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    @ResultMap(value = "product_type")
    List<Order> getOrdersConfirm_0_BySearchVo(SearchVo searchVo);

    //查已收货
    @Select("<script>" +
            "select * from `order` "
            + "<where>"
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and ( order_product_name like '%${keyWord}%') "
            + "</if>"
            + " and ( order_confirm_status = 1 )"
            + "</where>"
            + "<choose>"
            + "<when test= 'orderBy != \"\" and orderBy != null' >"
            + "order by ${orderBy} ${sort}"
            + "</when>"
            + "<otherwise> "
            + " ORDER BY order_id desc "
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    @ResultMap(value = "product_type")
    List<Order> getOrdersConfirm_1_BySearchVo(SearchVo searchVo);

    //查已经退款
    @Select("<script>" +
            "select * from `order` "
            + "<where>"
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and ( order_product_name like '%${keyWord}%') "
            + "</if>"
            + " and ( order_back_money_status = 1 )"
            + " and ( order_status = 1 )"
            + "</where>"
            + "<choose>"
            + "<when test= 'orderBy != \"\" and orderBy != null' >"
            + "order by ${orderBy} ${sort}"
            + "</when>"
            + "<otherwise> "
            + " ORDER BY order_id desc "
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    @ResultMap(value = "product_type")
    List<Order> getOrders_status_1BySearchVo(SearchVo searchVo);
    //查未退款
    @Select("<script>" +
            "select * from `order` "
            + "<where>"
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and ( order_product_name like '%${keyWord}%') "
            + "</if>"
            + " and ( order_back_money_status = 1 )"
            + " and ( order_status = 0 )"
            + "</where>"
            + "<choose>"
            + "<when test= 'orderBy != \"\" and orderBy != null' >"
            + "order by ${orderBy} ${sort}"
            + "</when>"
            + "<otherwise> "
            + " ORDER BY order_id desc "
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    @ResultMap(value = "product_type")
    List<Order> getOrders_status_0BySearchVo(SearchVo searchVo);

    //查询订单信息通过订单号
    @Select("select * from `order` where order_number=#{orderNumber} ")
    Order getOrderByOderNumber(String orderNumber);

    //修改订单信息
    @Update("update `order` set order_product_numbers=#{orderProductNumbers},order_product_total_price=#{orderProductTotalPrice}," +
            "order_address=#{orderAddress},user_phone=#{userPhone} where order_number=#{orderNumber}")
    void updateOrderByOrder(Order order);

    //查未评论
    @Select("<script>" +
            "select * from `order` "
            + "<where>"
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and ( order_product_name like '%${keyWord}%') "
            + "</if>"
            + " and ( order_confirm_status = 1 )"
            + " and ( order_comment_status = 0 )"
            + "</where>"
            + "<choose>"
            + "<when test= 'orderBy != \"\" and orderBy != null' >"
            + "order by ${orderBy} ${sort}"
            + "</when>"
            + "<otherwise> "
            + " ORDER BY order_id desc "
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    @ResultMap(value = "product_type")
    List<Order> getOrders_review_0_BySearchVo(SearchVo searchVo);
}
