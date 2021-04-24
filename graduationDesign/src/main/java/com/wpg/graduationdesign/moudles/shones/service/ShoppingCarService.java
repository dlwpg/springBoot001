package com.wpg.graduationdesign.moudles.shones.service;

import com.github.pagehelper.PageInfo;
import com.wpg.graduationdesign.moudles.shones.entity.ShoppingCar;
import com.wpg.graduationdesign.vo.Result;
import com.wpg.graduationdesign.vo.SearchVo;

import java.util.List;

/**
 * @author Administrator
 */
public interface ShoppingCarService {
    /**
     * 添加购物车
     * @param shoppingCar car
     * @return b
     */
    Result<Boolean> addShoppingCarInfo(ShoppingCar shoppingCar);

    /**
     * 获取购物车信息
     * @param userId
     * @return
     */
    Result<List<ShoppingCar>> getShoppingCarInfoByUserId(String userId);

    /**
     * 删除购物车
     * @param shoppingCar id
     * @return b
     */
    Result<Boolean> delShoppingCarInfo(ShoppingCar shoppingCar);

    /**
     * 批量删除购物车
     * @param carsList c
     */
    void delShoppingCars(List<Integer> carsList);
}
