package com.wpg.graduationdesign.moudles.shones.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wpg.graduationdesign.moudles.shones.dao.ReceiveAddressDao;
import com.wpg.graduationdesign.moudles.shones.entity.ReceiveAddress;
import com.wpg.graduationdesign.moudles.shones.entity.User;
import com.wpg.graduationdesign.moudles.shones.service.ReceiveAddressService;
import com.wpg.graduationdesign.vo.Result;
import com.wpg.graduationdesign.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author wpg
 */
@Service
public class ReceiveAddressServiceImpl implements ReceiveAddressService {
    @Autowired
    private ReceiveAddressDao receiveAddressDao;

    @Override
    public PageInfo<ReceiveAddress> getReceiveAddressByUserId(SearchVo searchVo, String thisUserId) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        PageInfo<ReceiveAddress> userAddressPage = new PageInfo<>(Optional.ofNullable(receiveAddressDao.getReceiveAddressByUserId(searchVo, Integer.valueOf(thisUserId)))
                .orElse(Collections.emptyList()));
        return userAddressPage;
    }

    @Override
    public Result<ReceiveAddress> getReceiveAddressByAddressId(String addressId) {
        return new Result<>(Result.Resultstatus.SUCCESS.status
                , "获取信息成功"
                , receiveAddressDao.getReceiveAddressByAddressId(Integer.valueOf(addressId)));
    }

    @Override
    public Result<Boolean> updateReceiveAddressByAddressId(ReceiveAddress receiveAddress) {
        return new Result<>(Result.Resultstatus.SUCCESS.status
                , "修改成功"
                , receiveAddressDao.updateReceiveAddressByAddressId(receiveAddress));
    }

    @Override
    public Result<Boolean> saveReceiveAddress(ReceiveAddress receiveAddress) {
        return new Result<>(Result.Resultstatus.SUCCESS.status,"新增成功"
                ,receiveAddressDao.saveReceiveAddress(receiveAddress));
    }

    @Override
    public Result<Boolean> delReceiveAddress(String addressId) {
        return new Result<>(Result.Resultstatus.SUCCESS.status,"删除成功"
                ,receiveAddressDao.delReceiveAddress(Integer.valueOf(addressId)));
    }

    @Override
    public Result<List<ReceiveAddress>> receiveAddressList(Integer userId) {
        return new Result<>(Result.Resultstatus.SUCCESS.status,"获取地址结合成功"
                ,receiveAddressDao.receiveAddressList(userId));
    }
}
