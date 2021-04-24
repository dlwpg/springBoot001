package com.wpg.graduationdesign.moudles.shones.controller.qt;

import com.github.pagehelper.PageInfo;
import com.wpg.graduationdesign.moudles.shones.entity.*;
import com.wpg.graduationdesign.moudles.shones.service.OrderService;
import com.wpg.graduationdesign.moudles.shones.service.ReceiveAddressService;
import com.wpg.graduationdesign.moudles.shones.service.ShoppingCarService;
import com.wpg.graduationdesign.moudles.shones.service.UserService;
import com.wpg.graduationdesign.vo.Result;
import com.wpg.graduationdesign.vo.SearchVo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/shone_qt")
public class UserQtController {
    @Autowired
    private UserService userService;

    @Autowired
    private ReceiveAddressService receiveAddressService;

    @Autowired
    private ShoppingCarService shoppingCarService;

    @Autowired
    private OrderService orderService;

    /**
     * 前台登录
     * 127.0.0.1/shone_qt/login   ---get
     * {"userName":"wpg","password":"123456"}
     */
    @PostMapping(value = "/login", consumes = "application/json")
    public Result<User> loginByUser(@RequestBody User user) {
        return userService.loginByUser(user);
    }


    /**
     * 前台注册
     * 127.0.0.1/shone_qt/register   ---post
     * {"userName":"wpg","password":"123456","sex":"","email":"2389038599@qq.com"}
     */
    @PostMapping(value = "/register", consumes = "application/json")
    public Result<User> registerByUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    /**
     * 获取当前登录信息
     */
    @GetMapping("/login_info")
    public Result<User> getSessionInfo() {
        // 获取当前登录用户
        User user = (User) SecurityUtils.getSubject().getPrincipal();

        return new Result<>(Result.Resultstatus.SUCCESS.status,"获取登录信息成功",user);
    }

    /**
     * 前台匹配原密码
     */
    @PostMapping(value = "/check_old_password", consumes = "application/json")
    public Result<User> checkOldPassword(@RequestBody User user){
        return userService.checkOldPassword(user);
    }

    /**
     * 修改前台密码
     */
    @PutMapping(value = "/update_password_qt", consumes = "application/json")
    public Result<String> updatePasswordQt(@RequestBody User user){
        return userService.revisePassword(user.getUserId(),user.getPassWord());
    }

    /**
     * 修改前台其他信息
     */
    @PutMapping(value = "/update_setting", consumes = "application/json")
    public Result<User> updateSetting(@RequestBody User user){
        return userService.updateSetting(user);
    }

    /**
     * 根据用户id获取前台收货地址分页
     */
    @PostMapping(value = "/address_qt/{thisUserId}", produces = "application/json", consumes = "application/json")
    public PageInfo<ReceiveAddress> getReceiveAddressByUserId(@RequestBody SearchVo searchVo, @PathVariable("thisUserId") String thisUserId){
       return receiveAddressService.getReceiveAddressByUserId(searchVo,thisUserId);
    }

    /**
     * 根据用户id获取前台收货地址单条
     */
    @GetMapping(value = "/address_qt", produces = "application/json", consumes = "application/json")
    public Result<ReceiveAddress> getReceiveAddressByAddressId( @RequestParam("addressId") String addressId){
        return receiveAddressService.getReceiveAddressByAddressId(addressId);
    }

    /**
     * 修改收货地址
     */
    @PutMapping(value = "/address_qt", produces = "application/json", consumes = "application/json")
    public Result<Boolean> updateReceiveAddressByAddressId(@RequestBody ReceiveAddress receiveAddress){
        return receiveAddressService.updateReceiveAddressByAddressId(receiveAddress);
    }

    /**
     * 新增收货地址
     */
    @PostMapping(value = "/save_address_qt", produces = "application/json", consumes = "application/json")
    public Result<Boolean> saveReceiveAddress(@RequestBody ReceiveAddress receiveAddress){
        return receiveAddressService.saveReceiveAddress(receiveAddress);
    }

    /**
     * 删除收货地址
     */
    @DeleteMapping(value = "/del_address_qt/{addressId}", produces = "application/json", consumes = "application/json")
    public Result<Boolean> delReceiveAddress(@PathVariable("addressId") String addressId){
        return receiveAddressService.delReceiveAddress(addressId);
    }

    /**
     * 添加购物车
     */
    @PostMapping(value = "/add_to_car", produces = "application/json", consumes = "application/json")
    public Result<Boolean> addShoppingCarInfo(@RequestBody ShoppingCar shoppingCar){
        return shoppingCarService.addShoppingCarInfo(shoppingCar);
    }

    /**
     * 购物车信息
     */
    @GetMapping(value = "/car_info/{userId}", produces = "application/json", consumes = "application/json")
    public Result<List<ShoppingCar>> getShoppingCarInfoByUserId(@PathVariable("userId") String userId){
        return shoppingCarService.getShoppingCarInfoByUserId(userId);
    }

    /**
     * 删除购物车
     */
    @DeleteMapping(value = "/del_to_car", produces = "application/json", consumes = "application/json")
    public Result<Boolean> delShoppingCarInfo(@RequestBody ShoppingCar shoppingCar){
        return shoppingCarService.delShoppingCarInfo(shoppingCar);
    }

    /**
     * 提交订单
     */
    @PostMapping(value = "/add_order", produces = "application/json", consumes = "application/json")
    public Result<String> addOrder(@RequestBody List<Order> orders,@RequestParam("carIds") String carIds){
        return orderService.addOrder(orders,carIds);
    }

    /**
     * 通过用户id查询所有订单-排序支付，发货，收货，评价
     */
    @GetMapping(value = "/orders", produces = "application/json", consumes = "application/json")
    public Result<List<Order>> getOrdersByUserId(@RequestParam("userId") String userId){
        return orderService.getOrdersByUserId(userId);
    }

    /**
     * 通过用户id查询所有订单-待付款
     */
    @GetMapping(value = "/not_pay_orders", produces = "application/json", consumes = "application/json")
    public Result<List<Order>> getNotPayOrdersByUserId(@RequestParam("userId") String userId){
        return orderService.getNotPayOrdersByUserId(userId);
    }

    /**
     * 通过用户id查询所有订单-待发货
     */
    @GetMapping(value = "/not_send_orders", produces = "application/json", consumes = "application/json")
    public Result<List<Order>> getNotSendOrdersByUserId(@RequestParam("userId") String userId){
        return orderService.getNotSendOrdersByUserId(userId);
    }
    /**
     * 通过用户id查询所有订单-待收货
     */
    @GetMapping(value = "/not_receive_orders", produces = "application/json", consumes = "application/json")
    public Result<List<Order>> getNotReceiveOrdersByUserId(@RequestParam("userId") String userId){
        return orderService.getNotReceiveOrdersByUserId(userId);
    }
    /**
     * 通过用户id查询所有订单-待评价
     */
    @GetMapping(value = "/not_review_orders", produces = "application/json", consumes = "application/json")
    public Result<List<Order>> getNotReviewOrdersByUserId(@RequestParam("userId") String userId){
        return orderService.getNotReviewOrdersByUserId(userId);
    }

    /**
     * 删除订单
     */
    @DeleteMapping(value = "/del_order", produces = "application/json", consumes = "application/json")
    public Result<Boolean> delOrderByOrderId(@RequestParam("orderId") Integer orderId){
        return orderService.delOrderByOrderId(orderId);
    }

    /**
     * 确认收货
     */
    @PutMapping(value = "/receive_order", produces = "application/json", consumes = "application/json")
    public Result<Boolean> receiveOrderByOrderId(@RequestParam("orderId") Integer orderId){
        return orderService.receiveOrderByOrderId(orderId);
    }

    /**
     * 保存评论
     */
    @PostMapping(value = "/save_review", produces = "application/json", consumes = "application/json")
    public Result<Boolean> saveReview(@RequestBody Review review){
        return orderService.saveReview(review);
    }

    /**
     * 获取收货地址集合
     */
    @GetMapping(value = "/receive_address_list", produces = "application/json", consumes = "application/json")
    public Result<List<ReceiveAddress>> receiveAddressList(@RequestParam("userId")Integer userId){
        return receiveAddressService.receiveAddressList(userId);
    }

    /**
     * 通过id获取订单信息
     */
    @GetMapping(value = "/order", produces = "application/json", consumes = "application/json")
    public Result<Order> getOrderByOrderId(@RequestParam("orderId")Integer orderId){
        return orderService.getOrderByOrderId(orderId);
    }

    /**
     * 支付
     */
    @PostMapping(value = "/pay", produces = "application/json", consumes = "application/json")
    public Result<Boolean> pay(@RequestBody Order  order){
        return orderService.pay(order.getOrderAddress(),order.getOrderId());
    }
}
