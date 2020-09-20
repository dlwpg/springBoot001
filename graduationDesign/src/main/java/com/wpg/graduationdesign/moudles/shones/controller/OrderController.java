package com.wpg.graduationdesign.moudles.shones.controller;

import com.github.pagehelper.PageInfo;
import com.wpg.graduationdesign.moudles.shones.entity.Order;
import com.wpg.graduationdesign.moudles.shones.service.OrderService;
import com.wpg.graduationdesign.vo.Result;
import com.wpg.graduationdesign.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shone")
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**查所有订单
     * 127.0.0.1/shone/order/orders  ==post
     * {xxx}
     */
    @PostMapping(value = "/order/orders",consumes = "application/json")
    public PageInfo<Order> getOrdersBySearchVo(@RequestBody SearchVo searchVo){
        return orderService.getOrdersBySearchVo(searchVo);
    }
    /**查已支付
     * 127.0.0.1/shone/order/orders_payment_1  ==post
     * {xxx}
     */
    @PostMapping(value = "/order/orders_payment_1",consumes = "application/json")
    public PageInfo<Order> getOrdersPayment_1_BySearchVo(@RequestBody SearchVo searchVo){
        return orderService.getOrdersPayment_1_BySearchVo(searchVo);
    }

    /**查未支付
     * 127.0.0.1/shone/order/orders_payment_0  ==post
     * {xxx}
     */
    @PostMapping(value = "/order/orders_payment_0",consumes = "application/json")
    public PageInfo<Order> getOrdersPayment_0_BySearchVo(@RequestBody SearchVo searchVo){
        return orderService.getOrdersPayment_0_BySearchVo(searchVo);
    }

    /**查已发货
     * 127.0.0.1/shone/order/orders_Send_1  ==post
     * {xxx}
     */
    @PostMapping(value = "/order/orders_send_1",consumes = "application/json")
    public PageInfo<Order> getOrdersSend_1_BySearchVo(@RequestBody SearchVo searchVo){
        return orderService.getOrdersSend_1_BySearchVo(searchVo);
    }

    /**查未发货
     * 127.0.0.1/shone/order/orders_Send_0  ==post
     * {xxx}
     */
    @PostMapping(value = "/order/orders_send_0",consumes = "application/json")
    public PageInfo<Order> getOrdersSend_0_BySearchVo(@RequestBody SearchVo searchVo){
        return orderService.getOrdersSend_0_BySearchVo(searchVo);
    }

    /**查未退款
     * 127.0.0.1/shone/order/orders_status_0  ==post
     * {xxx}
     */
    @PostMapping(value = "/order/orders_status_0",consumes = "application/json")
    public PageInfo<Order> getOrders_status_0BySearchVo(@RequestBody SearchVo searchVo){
        return orderService.getOrders_status_0BySearchVo(searchVo);
    }
    /**查已退款
     * 127.0.0.1/shone/order/orders_status_1  ==post
     * {xxx}
     */
    @PostMapping(value = "/order/orders_status_1",consumes = "application/json")
    public PageInfo<Order> getOrders_status_1BySearchVo(@RequestBody SearchVo searchVo){
        return orderService.getOrders_status_1BySearchVo(searchVo);
    }

    /**查发起退款订单
     * 127.0.0.1/shone/order/orders_back_money_1  ==post
     * {xxx}
     */
    @PostMapping(value = "/order/orders_back_money_1",consumes = "application/json")
    public PageInfo<Order> getOrdersBack_money_1_BySearchVo(@RequestBody SearchVo searchVo){
        return orderService.getOrdersBack_money_1_BySearchVo(searchVo);
    }

    /**查已收货
     * 127.0.0.1/shone/order/orders_Confirm_1  ==post
     * {xxx}
     */
    @PostMapping(value = "/order/orders_confirm_1",consumes = "application/json")
    public PageInfo<Order> getOrdersConfirm_1_BySearchVo(@RequestBody SearchVo searchVo){
        return orderService.getOrdersConfirm_1_BySearchVo(searchVo);
    }

    /**查未收货
     * 127.0.0.1/shone/order/orders_Confirm_0  ==post
     * {xxx}
     */
    @PostMapping(value = "/order/orders_confirm_0",consumes = "application/json")
    public PageInfo<Order> getOrdersConfirm_0_BySearchVo(@RequestBody SearchVo searchVo){
        return orderService.getOrdersConfirm_0_BySearchVo(searchVo);
    }

    /**查未评论
     * 127.0.0.1/shone/order/orders_review_0  ==post
     * {xxx}
     */
    @PostMapping(value = "/order/orders_review_0",consumes = "application/json")
    public PageInfo<Order> getOrders_review_0_BySearchVo(@RequestBody SearchVo searchVo){
        return orderService.getOrders_review_0_BySearchVo(searchVo);
    }

    /**通过订单号查询订单信息
     * 127.0.0.1/shone/order/order/x  ==post
     */
    @PostMapping("/order/order")
    public Result<Order> getOrderByOderNumber(@RequestParam String  orderNumber){
        return orderService.getOrderByOderNumber(orderNumber);
    }


    //修改订单信息
    @PutMapping(value = "/order/order",consumes = "application/json",produces = "application/json")
    public Result<Order> updateOrderByOrder(@RequestBody Order order){
        return orderService.updateOrderByOrder(order);
    }
}
