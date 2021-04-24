package com.wpg.graduationdesign.moudles.shones.controller;

import com.wpg.graduationdesign.moudles.shones.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/nxzm")
public class AccoutntController {

    @Autowired
    private UserService userService;

    /**
     * 127.0.0.1/nxzm/user/login   ==get
     */
    @GetMapping("/user/login")
    public String login() {
        return "indexsample";
    }

    /**
     * 127.0.0.1/nxzm/user/mainpage   ==get
     */
    @GetMapping("/user/mainpage")
    public String index() {
        return "index";
    }

    /**
     * 127.0.0.1/nxzm/user/register   ==get
     */
    @GetMapping("/user/register")
    public String register() {
        return "indexsample";
    }

    /**
     * 127.0.0.1/nxzm/user/register   ==get
     */
    @GetMapping("/user/activation/{userId}")
    @ResponseBody
    public String activestatus(@PathVariable int userId) {
        userService.activeStatus(userId);
        return "激活成功！";
    }


    /**
     * 127.0.0.1/nxzm/user/profile   ==get
     */
    @GetMapping("/user/profile")
    public String profile() {
        return "index";
    }

    /**
     * 127.0.0.1/nxzm/user/edituserpassword   ==get
     */
    @GetMapping("/user/edituserpassword")
    public String editUserPassword() {
        return "index";
    }

    /**
     * 127.0.0.1/nxzm/user/revisepassword   ==get
     */
    @GetMapping("/user/revisepassword")
    @ResponseBody
    public String revisePassword(@RequestParam("userId") int userId,
                                 @RequestParam("newPassword") String newPassword) {
        userService.revisePassword(userId, newPassword);
        return "密码修改成功！";
    }

//    /**
//     * 127.0.0.1/nxzm/user/users   ==get
//     */
//    @GetMapping("/user/users")
//    public String users(HttpServletRequest request) {
//        String template = (String) request.getAttribute("template");
//        return "index::"+template;
//    }


    /**
     * http://127.0.0.1/nxzm/user/users   ==get
     */
    @GetMapping("/user/users")
    public String users() {
        return "index";
    }

    /**
     * 127.0.0.1/nxzm/role/roles   ==get
     */
    @GetMapping("/role/roles")
    public String roles() {
        return "index";
    }

//    ==================product===============

    /**
     * 127.0.0.1/nxzm/product/products   ==get
     */
    @GetMapping("/product/products")
    public String products() {
        return "index";
    }

    //    ==================brand===============

    /**
     * 127.0.0.1/nxzm/brand/brands   ==get
     */
    @GetMapping("/brand/brands")
    public String brands() {
        return "index";
    }


    //    ==================category===============

    /**
     * 127.0.0.1/nxzm/category/categories   ==get
     */
    @GetMapping("/category/categories")
    public String categories() {
        return "index";
    }

    //    ==================productimages===============
    /**
     * 127.0.0.1/nxzm/product_other_image/product_other_images   ==get
     */
    @GetMapping("/product_other_image/product_other_images")
    public String otherimages() {
        return "index";
    }



    //    ==================orders===============
    /**
     * 127.0.0.1/nxzm/order/orders   ==get
     */
    @GetMapping("/order/orders")
    public String orders() {
        return "index";
    }

    /**未支付
     * 127.0.0.1/nxzm/order/orders_payment_0   ==get
     */
    @GetMapping("/order/orders_payment_0")
    public String pay_0() {
        return "index";
    }
    /**已支付
     * 127.0.0.1/nxzm/order/orders_payment_0   ==get
     */
    @GetMapping("/order/orders_payment_1")
    public String pay_1() {
        return "index";
    }
    /**未发送
     * 127.0.0.1/nxzm/order/orders_Send_0   ==get
     */
    @GetMapping("/order/orders_send_0")
    public String send_0() {
        return "index";
    }
    /**已发送
     * 127.0.0.1/nxzm/order/orders_send_1   ==get
     */
    @GetMapping("/order/orders_send_1")
    public String send_1() {
        return "index";
    }

    /**已发起退货
     * 127.0.0.1/nxzm/order/orders_back_money_1   ==get
     */
    @GetMapping("/order/orders_back_money_1")
    public String backmoney_1() {
        return "index";
    }

    /**未退款
     * 127.0.0.1/nxzm/order/orders_status_0   ==get
     */
    @GetMapping("/order/orders_status_0")
    public String back0() {
        return "index";
    }


    /**已退款
     * 127.0.0.1/nxzm/order/orders_status_1   ==get
     */
    @GetMapping("/order/orders_status_1")
    public String back1() {
        return "index";
    }

    /**未确认收货
     * 127.0.0.1/nxzm/order/orders_Confirm_0   ==get
     */
    @GetMapping("/order/orders_confirm_0")
    public String confirm_0() {
        return "index";
    }
    /**已确认收货
     * 127.0.0.1/nxzm/order/orders_Confirm_1   ==get
     */
    @GetMapping("/order/orders_confirm_1")
    public String confirm_1() {
        return "index";
    }

    /**未评论订单
     * 127.0.0.1/nxzm/order/orders_review_0   ==get
     */
    @GetMapping("/order/orders_review_0")
    public String reviews_0() {
        return "index";
    }

    //=============reviews====================

    /**已评论订单
     * 127.0.0.1/nxzm/review/reviews  ==get
     */
    @GetMapping("/review/reviews")
    public String reviews() {
        return "index";
    }


}
