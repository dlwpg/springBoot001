package com.wpg.graduationdesign.moudles.shones.controller.qt;

import com.wpg.graduationdesign.moudles.shones.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/nxzmqt")
public class AccoutntQtController {

    /**
     * 前台登录
     * 127.0.0.1:80/nxzmqt/user/login   ==get
     */
    @GetMapping("/user/login")
    public String login() {
        return "indexsample_qt";
    }

    /**
     * 前台注册
     * 127.0.0.1:80/nxzmqt/user/register   ==get
     */
    @GetMapping("/user/register")
    public String register() {
        return "indexsample_qt";
    }

    /**
     * 前台注册用户协议
     * 127.0.0.1:80/nxzmqt/temp_article/xyw_yonghuxieyi  ==get
     */
    @GetMapping("/temp_article/xyw_yonghuxieyi")
    public String article3() {
        return "index_qt";
    }

    /**
     * 前台主页
     * 127.0.0.1/nxzmqt/mainpge   ==get
     */
    @GetMapping("/mainpage")
    public String mainPage() {
        return "index_qt";
    }

    /**
     * 个人资料
     * 127.0.0.1:80/nxzmqt/user/setting   ==get
     */
    @GetMapping("/user/setting")
    public String setting() {
        return "index_qt";
    }

    /**
     * 收货地址
     * 127.0.0.1:80/nxzmqt/user/shouhuodizhi  ==get
     */
    @GetMapping("/user/shouhuodizhi")
    public String address() {
        return "index_qt";
    }

    /**
     * 修改密码
     * 127.0.0.1:80/nxzmqt/user/xiugaimima1   ==get
     */
    @GetMapping("/user/xiugaimima1")
    public String updatePassword1() {
        return "index_qt";
    }

    /**
     * 修改密码
     * 127.0.0.1:80/nxzmqt/user/xiugaimima2   ==get
     */
    @GetMapping("/user/xiugaimima2")
    public String updatePassword2() {
        return "index_qt";
    }

    /**
     * 修改密码
     * 127.0.0.1:80/nxzmqt/user/xiugaimima3   ==get
     */
    @GetMapping("/user/xiugaimima3")
    public String updatePassword3() {
        return "index_qt";
    }

    /**
     * 订单
     * 127.0.0.1:80/nxzmqt/user/wodedingdan  ==get
     */
    @GetMapping("/user/wodedingdan")
    public String order() {
        return "index_qt";
    }

    /**
     * 退货
     * 127.0.0.1:80/nxzmqt/user/tuihuo  ==get
     */
    @GetMapping("/user/tuihuo")
    public String tuihuo() {
        return "index_qt";
    }

    /**
     * 物流查询
     * 127.0.0.1:80/nxzmqt/user/wuliuchaxun  ==get
     */
    @GetMapping("/user/wuliuchaxun")
    public String wuliuchaxun() {
        return "index_qt";
    }

    /**
     * 企业简介
     * 127.0.0.1:80/nxzmqt/temp_article/xyw_qiyejianjie   ==get
     */
    @GetMapping("/temp_article/xyw_qiyejianjie")
    public String article10() {
        return "index_qt";
    }

    /**
     * 加入我们
     * 127.0.0.1:80/nxzmqt/temp_article/xyw_jiaruwomen   ==get
     */
    @GetMapping("/temp_article/xyw_jiaruwomen")
    public String article11() {
        return "index_qt";
    }

    /**
     * 隐私说明
     * 127.0.0.1:80/nxzmqt/temp_article/xyw_yinsishuoming   ==get
     */
    @GetMapping("/temp_article/xyw_yinsishuoming")
    public String article12() {
        return "index_qt";
    }

    /**
     * 售后服务
     * 127.0.0.1:80/nxzmqt/temp_article/xyw_shouhoufuwu   ==get
     */
    @GetMapping("/temp_article/xyw_shouhoufuwu")
    public String article1() {
        return "index_qt";
    }

    /**
     * 配送服务
     * 127.0.0.1:80/nxzmqt/temp_article/xyw_peisongfuwu   ==get
     */
    @GetMapping("/temp_article/xyw_peisongfuwu")
    public String article2() {
        return "index_qt";
    }

    /**
     * 常见问题
     * 127.0.0.1:80/nxzmqt/temp_article/xyw_changjianwenti   ==get
     */
    @GetMapping("/temp_article/xyw_changjianwenti")
    public String article4() {
        return "index_qt";
    }

    /**
     * 期待
     * 127.0.0.1:80/nxzm_qt/qidaipage   ==get
     */
    @GetMapping("/qidaipage")
    public String qiDai() {
        return "index_qt";
    }

    /**
     * 反馈
     * 127.0.0.1:80/nxzm_qt/user/fankui   ==get
     */
    @GetMapping("/user/fankui")
    public String fanKui() {
        return "index_qt";
    }

    /**
     * 购物车
     * 127.0.0.1:80/nxzm_qt/user/gouwuche   ==get
     */
    @GetMapping("/user/gouwuche")
    public String gouWuChe() {
        return "index_qt";
    }

    /**
     * 支付
     * 127.0.0.1:80/nxzm_qt/user/zhifu   ==get
     */
    @GetMapping("/user/zhifu")
    public String zhiFu() {
        return "index_qt";
    }


    /**
     * 男鞋鞋界面
     * 127.0.0.1:80/nxzmqt/sale/shone   ==get
     */
    @GetMapping("/sale/shone")
    public String ydx() {
        return "index_qt";
    }

    /**
     * 男鞋展示界面
     * 127.0.0.1:80/nxzmqt/sale/shone/show   ==get
     */
    @GetMapping("/sale/shone_show")
    public String show() {
        return "index_qt";
    }
}
