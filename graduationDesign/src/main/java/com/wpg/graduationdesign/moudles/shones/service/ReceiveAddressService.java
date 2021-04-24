package com.wpg.graduationdesign.moudles.shones.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.wpg.graduationdesign.moudles.shones.entity.ReceiveAddress;
import com.wpg.graduationdesign.vo.Result;
import com.wpg.graduationdesign.vo.SearchVo;

import java.util.List;

/**
 * @author Administrator
 */
public interface ReceiveAddressService {

    /**
     * 通过用户id获取收货地址
     * @param thisUserId 用户id
     * @param searchVo 查询对象
     * @return 收货地址分页
     */
    PageInfo<ReceiveAddress> getReceiveAddressByUserId(SearchVo searchVo, String thisUserId);

    /**
     * 根据用户id获取前台收货地址单条
     * @param addressId addressId
     * @return 单挑地址信息
     */
    Result<ReceiveAddress> getReceiveAddressByAddressId(String addressId);

    /**
     * 修改收货地址
     * @param receiveAddress 修改对象
     * @return 结果
     */
    Result<Boolean> updateReceiveAddressByAddressId(ReceiveAddress receiveAddress);

    /**
     * 新增收货地址
     * @param receiveAddress address
     * @return b
     */
    Result<Boolean> saveReceiveAddress(ReceiveAddress receiveAddress);

    /**
     * 删除收货地址
     * @param addressId id
     * @return b
     */
    Result<Boolean> delReceiveAddress(String addressId);

    Result<List<ReceiveAddress>> receiveAddressList(Integer userId);
}
