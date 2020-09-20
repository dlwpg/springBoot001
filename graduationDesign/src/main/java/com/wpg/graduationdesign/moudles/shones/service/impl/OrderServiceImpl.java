package com.wpg.graduationdesign.moudles.shones.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wpg.graduationdesign.moudles.shones.dao.OrderDao;
import com.wpg.graduationdesign.moudles.shones.entity.Order;
import com.wpg.graduationdesign.moudles.shones.service.OrderService;
import com.wpg.graduationdesign.vo.Result;
import com.wpg.graduationdesign.vo.SearchVo;
import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    //查所有
    @Override
    @Transactional
    public PageInfo<Order> getOrdersBySearchVo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        List<Order> orderList = Optional.ofNullable(orderDao.getOrdersBySearchVo(searchVo))
                .orElse(Collections.emptyList());
        return new PageInfo<>(orderList);
    }

    //查已支付
    @Override
    @Transactional
    public PageInfo<Order> getOrdersPayment_1_BySearchVo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        List<Order> orderList = Optional.ofNullable(orderDao.getOrdersPayment_1_BySearchVo(searchVo))
                .orElse(Collections.emptyList());
        return new PageInfo<>(orderList);
    }

    //查未支付
    @Override
    @Transactional
    public PageInfo<Order> getOrdersPayment_0_BySearchVo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        List<Order> orderList = Optional.ofNullable(orderDao.getOrdersPayment_0_BySearchVo(searchVo))
                .orElse(Collections.emptyList());
        return new PageInfo<>(orderList);
    }

    //已发货
    @Override
    @Transactional
    public PageInfo<Order> getOrdersSend_1_BySearchVo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        List<Order> orderList = Optional.ofNullable(orderDao.getOrdersSend_1_BySearchVo(searchVo))
                .orElse(Collections.emptyList());
        return new PageInfo<>(orderList);
    }

    //未发货
    @Override
    @Transactional
    public PageInfo<Order> getOrdersSend_0_BySearchVo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        List<Order> orderList = Optional.ofNullable(orderDao.getOrdersSend_0_BySearchVo(searchVo))
                .orElse(Collections.emptyList());
        return new PageInfo<>(orderList);
    }

    //已发起退款订单
    @Override
    @Transactional
    public PageInfo<Order> getOrdersBack_money_1_BySearchVo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        List<Order> orderList = Optional.ofNullable(orderDao.getOrdersBack_money_1_BySearchVo(searchVo))
                .orElse(Collections.emptyList());
        return new PageInfo<>(orderList);
    }

    //未退款
    @Override
    @Transactional
    public PageInfo<Order> getOrdersBack_money_0_BySearchVo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        List<Order> orderList = Optional.ofNullable(orderDao.getOrdersBack_money_0_BySearchVo(searchVo))
                .orElse(Collections.emptyList());
        return new PageInfo<>(orderList);
    }

    //已收货
    @Override
    @Transactional
    public PageInfo<Order> getOrdersConfirm_1_BySearchVo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        List<Order> orderList = Optional.ofNullable(orderDao.getOrdersConfirm_1_BySearchVo(searchVo))
                .orElse(Collections.emptyList());
        return new PageInfo<>(orderList);
    }

    //未收货
    @Override
    @Transactional
    public PageInfo<Order> getOrdersConfirm_0_BySearchVo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        List<Order> orderList = Optional.ofNullable(orderDao.getOrdersConfirm_0_BySearchVo(searchVo))
                .orElse(Collections.emptyList());
        return new PageInfo<>(orderList);
    }


    //未退款
    @Override
    @Transactional
    public PageInfo<Order> getOrders_status_0BySearchVo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        List<Order> orderList = Optional.ofNullable(orderDao.getOrders_status_0BySearchVo(searchVo))
                .orElse(Collections.emptyList());
        return new PageInfo<>(orderList);
    }

    //已退款
    @Override
    @Transactional
    public PageInfo<Order> getOrders_status_1BySearchVo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        List<Order> orderList = Optional.ofNullable(orderDao.getOrders_status_1BySearchVo(searchVo))
                .orElse(Collections.emptyList());
        return new PageInfo<>(orderList);
    }

    @Override
    @Transactional
    public PageInfo<Order> getOrders_review_0_BySearchVo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        List<Order> orderList = Optional.ofNullable(orderDao.getOrders_review_0_BySearchVo(searchVo))
                .orElse(Collections.emptyList());
        return new PageInfo<>(orderList);
    }

    //通过订单号查询订单信息
    @Override
    @Transactional
    public Result<Order> getOrderByOderNumber(String orderNumber) {
        Order order = orderDao.getOrderByOderNumber(orderNumber);
        return new Result<>(Result.Resultstatus.SUCCESS.status, "", order);
    }

    //修改订单信息
    @Override
    @Transactional
    public Result<Order> updateOrderByOrder(Order order) {
        order.setOrderProductTotalPrice(
                order.getOrderProductPrice() * order.getOrderProductNumbers()
        );
        orderDao.updateOrderByOrder(order);
        return new Result<>(Result.Resultstatus.SUCCESS.status, "");
    }
}
