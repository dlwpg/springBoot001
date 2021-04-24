package com.wpg.graduationdesign.moudles.shones.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wpg.graduationdesign.moudles.shones.dao.ShoppingCarDao;
import com.wpg.graduationdesign.moudles.shones.entity.ReceiveAddress;
import com.wpg.graduationdesign.moudles.shones.entity.ShoppingCar;
import com.wpg.graduationdesign.moudles.shones.service.ProductService;
import com.wpg.graduationdesign.moudles.shones.service.ShoppingCarService;
import com.wpg.graduationdesign.vo.Result;
import com.wpg.graduationdesign.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Administrator
 */
@Service
public class ShoppingCarServiceImpl implements ShoppingCarService {
    @Autowired
    private ShoppingCarDao shoppingCarDao;
    @Autowired
    private ProductService productService;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<Boolean> addShoppingCarInfo(ShoppingCar shoppingCar) {
        shoppingCarDao.addShoppingCarInfo(shoppingCar);
        productService.stockReduce(shoppingCar.getpId(),shoppingCar.getNum());
        return new Result<>(Result.Resultstatus.SUCCESS.status
                ,"添加购物车成功",true);
    }

    @Override
    public Result<List<ShoppingCar>> getShoppingCarInfoByUserId(String userId) {
       return new Result<>(Result.Resultstatus.SUCCESS.status,"获取购物车信息成功"
       ,shoppingCarDao.getShoppingCarInfoByUserId(Integer.valueOf(userId)));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<Boolean> delShoppingCarInfo(ShoppingCar shoppingCar) {
        shoppingCarDao.delShoppingCarInfo(shoppingCar.getId());
        productService.stockAdd(shoppingCar.getpId(),shoppingCar.getNum());
        return new Result<>(Result.Resultstatus.SUCCESS.status
                ,"删除购物车成功",true);
    }

    @Override
    public void delShoppingCars(List<Integer> carsList) {
        shoppingCarDao.delShoppingCars(carsList);
    }
}
