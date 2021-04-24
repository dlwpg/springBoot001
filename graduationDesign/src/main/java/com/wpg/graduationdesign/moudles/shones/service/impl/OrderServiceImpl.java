package com.wpg.graduationdesign.moudles.shones.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wpg.graduationdesign.moudles.shones.dao.OrderDao;
import com.wpg.graduationdesign.moudles.shones.dao.ReviewDao;
import com.wpg.graduationdesign.moudles.shones.entity.Order;
import com.wpg.graduationdesign.moudles.shones.entity.ReceiveAddress;
import com.wpg.graduationdesign.moudles.shones.entity.Review;
import com.wpg.graduationdesign.moudles.shones.service.OrderService;
import com.wpg.graduationdesign.moudles.shones.service.ReviewService;
import com.wpg.graduationdesign.moudles.shones.service.ShoppingCarService;
import com.wpg.graduationdesign.vo.Result;
import com.wpg.graduationdesign.vo.SearchVo;
import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ShoppingCarService shoppingCarService;
    @Autowired
    private ReviewService reviewService;

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

    //每月销售额
    @Override
    @Transactional
    public List<Order> getOrders_month() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
        String now_year=sdf.format(new Date());
        return Optional.ofNullable(orderDao.getOrders_month(now_year)).orElse(Collections.emptyList());
    }

    //省份订单
    @Override
    @Transactional
    public List<Order> getOrders_Provence() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
        String now_year=sdf.format(new Date());
        return Optional.ofNullable(orderDao.getOrders_Provence(now_year)).orElse(Collections.emptyList());
    }

    //版型订单
    @Override
    @Transactional
    public List<Order> getOrders_Types() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
        String now_year=sdf.format(new Date());
        return Optional.ofNullable(orderDao.getOrders_Types(now_year)).orElse(Collections.emptyList());
    }

    //今日订单金额
    @Override
    @Transactional
    public Order getOrder_Today_Money() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String today=sdf.format(new Date());
        Order select_order= orderDao.getOrder_Today_Money(today);
        if (select_order==null){
            Order order=new Order();
            order.setOrderProductTotalPrice(0.0);
            return order;
        }
        return select_order ;
    }

    //昨日订单金额
    @Override
    @Transactional
    public Order getOrder_Yesterday_Money() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -1);
        Date start = c.getTime();
        String yesterday= sdf.format(start);
        Order select_order= orderDao.getOrder_Today_Money(yesterday);
        if (select_order==null){
            Order order=new Order();
            order.setOrderProductTotalPrice(0.0);
            return order;
        }
        return select_order ;
    }


    //近一月销售额
    @Override
    @Transactional
    public Order getOrder_This_Month_Money() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today=sdf.format(new Date());
        String thisMonth=today.substring(0,7);
//        Calendar c = Calendar.getInstance();
//        c.setTime(new Date());
//        c.add(Calendar.MONTH, -1);
//        Date m = c.getTime();
//        String mon = sdf.format(m);
//        System.out.println(mon);
        Order select_order= orderDao.getOrder_This_Month_Money(thisMonth);
        if (select_order==null){
            Order order=new Order();
            order.setOrderProductTotalPrice(0.0);
            return order;
        }
        return select_order ;
    }



    @Override
    @Transactional
    public Order getOrder_This_Year_Money() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String today=sdf.format(new Date());
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.YEAR,0);
        Date y = c.getTime();
        String year = sdf.format(y);
        Order select_order= orderDao.getOrder_This_Year_Money(year);
        if (select_order==null){
            Order order=new Order();
            order.setOrderProductTotalPrice(0.0);
            return order;
        }
        return select_order ;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<String> addOrder(List<Order> orders, String carIds) {
        //获取当前时间
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String format = formatter.format(new Date());
        for (Order or:orders
             ) {
            or.setOrderTradingTimeYear(String.valueOf(year));
            or.setOrderTradingTimeDetail(format);
        }
        //批量插入订单
        orderDao.addOrders(orders);
        //批量删除对应购物车
        List<String> carsList = Arrays.asList(carIds.split(","));
        List<Integer> cars=new ArrayList<>();
        for (String s:carsList
             ) {
            cars.add(Integer.valueOf(s));
        }
        shoppingCarService.delShoppingCars(cars);
        return new Result<>(Result.Resultstatus.SUCCESS.status,"提交订单成功","ok");
    }

    @Override
    public Result<List<Order>> getOrdersByUserId(String userId) {
        return new Result<>(Result.Resultstatus.SUCCESS.status
                ,"订单获取成功"
                ,orderDao.getOrdersByUserId(Integer.valueOf(userId)));
    }

    @Override
    public Result<List<Order>> getNotPayOrdersByUserId(String userId) {
        return new Result<>(Result.Resultstatus.SUCCESS.status
                ,"订单获取成功"
                ,orderDao.getNotPayOrdersByUserId(Integer.valueOf(userId)));
    }

    @Override
    public Result<List<Order>> getNotSendOrdersByUserId(String userId) {
        return new Result<>(Result.Resultstatus.SUCCESS.status
                ,"订单获取成功"
                ,orderDao.getNotSendOrdersByUserId(Integer.valueOf(userId)));
    }

    @Override
    public Result<List<Order>> getNotReceiveOrdersByUserId(String userId) {
        return new Result<>(Result.Resultstatus.SUCCESS.status
                ,"订单获取成功"
                ,orderDao.getNotReceiveOrdersByUserId(Integer.valueOf(userId)));
    }

    @Override
    public Result<List<Order>> getNotReviewOrdersByUserId(String userId) {
        return new Result<>(Result.Resultstatus.SUCCESS.status
                ,"订单获取成功"
                ,orderDao.getNotReviewOrdersByUserId(Integer.valueOf(userId)));
    }

    @Override
    public Result<Boolean> delOrderByOrderId(Integer orderId) {
        return new Result<>(Result.Resultstatus.SUCCESS.status
                ,"订单删除成功"
                ,orderDao.delOrderByOrderId(orderId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<Boolean> saveReview(Review review) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = formatter.format(new Date());
        review.setCreatetime(format);

        //设置以评论
        orderDao.setOrderReviewIsTrue(review.getOrderNumber());

        reviewService.saveReview(review);
        return new Result<>(Result.Resultstatus.SUCCESS.status
                ,"评价成功，店小二会根据你的评价鞭挞自己的！"
                ,true);
    }

    @Override
    public Result<Boolean> receiveOrderByOrderId(Integer orderId) {
        return new Result<>(Result.Resultstatus.SUCCESS.status
                ,"确认收货成功"
                ,orderDao.receiveOrderByOrderId(orderId));
    }

    @Override
    public Result<Order> getOrderByOrderId(Integer orderId) {
        return new Result<>(Result.Resultstatus.SUCCESS.status
                ,"获取订单信息成功"
                ,orderDao.getOrderByOrderId(orderId));
    }

    @Override
    public Result<Boolean> pay(String receiveAddress,Integer orderId) {
        String trim = receiveAddress.trim().split(" ")[0];
        String provence= trim.split("-")[0];
       String kd="小吴配送";
        return new Result<>(Result.Resultstatus.SUCCESS.status
                ,"支付成功"
                ,orderDao.pay(trim,provence,kd,orderId));
    }

    @Override
    public Boolean sendGoods(String orderNumber) {
        return orderDao.sendGoods(orderNumber);
    }
}
