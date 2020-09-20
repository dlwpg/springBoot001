package com.wpg.graduationdesign.moudles.shones.service;

import com.github.pagehelper.PageInfo;
import com.wpg.graduationdesign.moudles.shones.entity.Order;
import com.wpg.graduationdesign.vo.Result;
import com.wpg.graduationdesign.vo.SearchVo;

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
}
