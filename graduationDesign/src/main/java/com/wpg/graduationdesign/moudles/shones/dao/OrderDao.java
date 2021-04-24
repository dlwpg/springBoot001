package com.wpg.graduationdesign.moudles.shones.dao;

import com.wpg.graduationdesign.moudles.shones.entity.Brand;
import com.wpg.graduationdesign.moudles.shones.entity.Category;
import com.wpg.graduationdesign.moudles.shones.entity.Order;
import com.wpg.graduationdesign.vo.SearchVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

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
            @Result(column = "order_product_type", property = "orderProductType"),
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
            + " and ( order_payment_status = '1' )"
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
            + " and ( order_payment_status = '0' )"
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
            + " and ( order_number like '%${keyWord}%') "
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

    //年度销售额情况
    @Select("SELECT order_trading_time_detail ,COUNT(*) order_product_numbers ,SUM(order_product_total_price) order_product_total_price " +
            "FROM `order` WHERE order_trading_time_year =#{now_year} and order_payment_status=1 " +
            " GROUP BY SUBSTRING_INDEX(SUBSTRING_INDEX(order_trading_time_detail,'-',-2),'-',1),order_trading_time_detail ")
    List<Order> getOrders_month(String now_year);

    //年度省份订单分布
    @Select("SELECT order_province,COUNT(*) order_id FROM `order`  " +
            "where order_trading_time_year=#{now_year} and order_payment_status=1 GROUP BY order_province")
    List<Order> getOrders_Provence(String now_year);


    //年度版型销售情况
    @Select("SELECT order_product_type,COUNT(*) order_id FROM `order`  where order_trading_time_year=#{now_year}" +
            "and order_payment_status=1 GROUP BY order_product_type")
    @ResultMap(value = "product_type")
    List<Order> getOrders_Types(String now_year);

    //当天销售额
    @Select("SELECT SUM(order_product_total_price) order_product_total_price FROM `order`  " +
            "where order_trading_time_detail=#{today} and order_payment_status='1'")
    Order getOrder_Today_Money(String today);

    //前一个月的销售额
    @Select("SELECT SUM(order_product_total_price) order_product_total_price FROM `order`  " +
            "where order_trading_time_detail like '%${thisMonth}%'  and order_payment_status='1'")
    Order getOrder_This_Month_Money(@RequestParam("thisMonth") String thisMonth);


    //本年的销售额
    @Select("SELECT SUM(order_product_total_price) order_product_total_price FROM `order`  " +
            "where order_trading_time_year = #{year} and order_payment_status='1'")
    Order getOrder_This_Year_Money(@RequestParam("year") String year);

    /**
     * 批量插入订单
     *
     * @param orders o
     */
    @Insert("<script> " +
            "insert into `order` " +
            "(order_product_id,order_product_numbers,order_product_name,order_product_total_price," +
            "order_user_name,order_trading_time_detail,order_product_image,order_product_price," +
            "order_trading_time_year,user_phone,user_id,order_product_type,order_number) " +
            "values " +
            "<foreach collection=\"orders\" index=\"index\" item=\"i\" separator=\",\"> "
            +
            "(#{i.orderProductId},#{i.orderProductNumbers},#{i.orderProductName},#{i.orderProductTotalPrice}," +
            "#{i.orderUserName},#{i.orderTradingTimeDetail},#{i.orderProductImage},#{i.orderProductPrice}," +
            "#{i.orderTradingTimeYear},#{i.userPhone},#{i.userId},#{i.orderProductType},#{i.orderNumber})"
            +
            "</foreach> " +
            "</script>")
    void addOrders(@Param("orders") List<Order> orders);

    @Select("select * from `order` where user_id = #{userId} " +
            "order by order_payment_status,order_send_status,order_confirm_status,order_comment_status")
    List<Order> getOrdersByUserId(@Param("userId") Integer userId);

    @Select("select * from `order` where user_id = #{userId} and order_payment_status= '0' " +
            "order by order_trading_time_detail")
    List<Order> getNotPayOrdersByUserId(@Param("userId") Integer userId);

    @Select("select * from `order` where user_id = #{userId} and order_payment_status= '1' and order_send_status= 0 " +
            "order by order_trading_time_detail")
    List<Order> getNotSendOrdersByUserId(@Param("userId") Integer userId);

    @Select("select * from `order` where user_id = #{userId} and order_payment_status= '1' and order_send_status= 1 and order_confirm_status= 0 " +
            "order by order_trading_time_detail")
    List<Order> getNotReceiveOrdersByUserId(@Param("userId") Integer userId);

    @Select("select * from `order` where user_id = #{userId} and order_payment_status= '1' and order_send_status= 1 and order_confirm_status= 1 and order_comment_status= 0 " +
            "order by order_trading_time_detail")
    List<Order> getNotReviewOrdersByUserId(@Param("userId") Integer userId);

    @Delete("delete from `order` where order_id = #{orderId}")
    Boolean delOrderByOrderId(@Param("orderId") Integer orderId);

    @Update("update `order` set order_confirm_status = 1 where order_id = #{orderId}")
    Boolean receiveOrderByOrderId(@Param("orderId") Integer orderId);

    @Select("select * from `order` where order_id = #{orderId}")
    Order getOrderByOrderId(@Param("orderId") Integer orderId);

    @Update("update `order` set courier_services_company = #{kd} , order_province = #{provence} ,order_address = #{receiveAddress}, order_payment_status = '1' where order_id = #{orderId}")
    Boolean pay(@Param("receiveAddress") String receiveAddress, @Param("provence") String provence, @Param("kd") String kd, @Param("orderId") Integer orderId);

    @Update("update `order` set order_comment_status = 1 where order_number = #{orderNumber}")
    Boolean setOrderReviewIsTrue(@Param("orderNumber") String orderNumber);

    @Update("update `order` set order_send_status = 1 where order_number = #{orderNumber}")
    Boolean sendGoods(@Param("orderNumber") String orderNumber);
}
