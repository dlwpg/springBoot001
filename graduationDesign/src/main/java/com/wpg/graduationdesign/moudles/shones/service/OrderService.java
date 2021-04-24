package com.wpg.graduationdesign.moudles.shones.service;

import com.github.pagehelper.PageInfo;
import com.wpg.graduationdesign.moudles.shones.entity.Order;
import com.wpg.graduationdesign.moudles.shones.entity.ReceiveAddress;
import com.wpg.graduationdesign.moudles.shones.entity.Review;
import com.wpg.graduationdesign.vo.Result;
import com.wpg.graduationdesign.vo.SearchVo;

import java.util.List;

public interface OrderService {
    PageInfo<Order> getOrdersBySearchVo(SearchVo searchVo);

    PageInfo<Order> getOrdersPayment_1_BySearchVo(SearchVo searchVo);

    PageInfo<Order> getOrdersPayment_0_BySearchVo(SearchVo searchVo);

    PageInfo<Order> getOrdersSend_1_BySearchVo(SearchVo searchVo);

    PageInfo<Order> getOrdersSend_0_BySearchVo(SearchVo searchVo);

    PageInfo<Order> getOrdersBack_money_1_BySearchVo(SearchVo searchVo);

    PageInfo<Order> getOrdersBack_money_0_BySearchVo(SearchVo searchVo);

    PageInfo<Order> getOrdersConfirm_1_BySearchVo(SearchVo searchVo);

    PageInfo<Order> getOrdersConfirm_0_BySearchVo(SearchVo searchVo);

    PageInfo<Order> getOrders_status_0BySearchVo(SearchVo searchVo);

    PageInfo<Order> getOrders_status_1BySearchVo(SearchVo searchVo);

    Result<Order> getOrderByOderNumber(String orderNumber);

    Result<Order> updateOrderByOrder(Order order);

    PageInfo<Order> getOrders_review_0_BySearchVo(SearchVo searchVo);

    List<Order> getOrders_month();

    List<Order> getOrders_Provence();

    List<Order> getOrders_Types();

    Order getOrder_Today_Money();

    Order getOrder_Yesterday_Money();

    Order getOrder_This_Month_Money();

    Order getOrder_This_Year_Money();

    /**
     * 提交订单
     * @param orders o
     * @param carIds c
     * @return s
     */
    Result<String> addOrder(List<Order> orders, String carIds);


    Result<List<Order>> getOrdersByUserId(String userId);

    Result<List<Order>> getNotPayOrdersByUserId(String userId);

    Result<List<Order>> getNotSendOrdersByUserId(String userId);

    Result<List<Order>> getNotReceiveOrdersByUserId(String userId);

    Result<List<Order>> getNotReviewOrdersByUserId(String userId);

    Result<Boolean> delOrderByOrderId(Integer orderId);

    Result<Boolean> saveReview(Review review);

    Result<Boolean> receiveOrderByOrderId(Integer orderId);

    Result<Order> getOrderByOrderId(Integer orderId);

    Result<Boolean> pay(String receiveAddress,Integer orderId);

    Boolean sendGoods(String orderNumber);
}
