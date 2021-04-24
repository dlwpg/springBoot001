package com.wpg.graduationdesign.moudles.shones.dao;

import com.github.pagehelper.Page;
import com.wpg.graduationdesign.moudles.shones.entity.ReceiveAddress;
import com.wpg.graduationdesign.vo.Result;
import com.wpg.graduationdesign.vo.SearchVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wpg
 */
@Repository
@Mapper
public interface ReceiveAddressDao {
    /**
     * 通过用户id获取收货地址
     *
     * @param searchVo   c
     * @param thisUserId id
     * @return
     */
    @Select("<script>" +
            "select * from receive_address "
            + "<where>"
            + "user_id=#{thisUserId}"
            + "<if test='s.keyWord != \"\" and s.keyWord != null'>"
            + " and (address like '%${s.keyWord}%') "
            + "</if>"
            + "</where>"
            + "<choose>"
            + "<when test= 's.orderBy != \"\" and s.orderBy != null' >"
            + "order by ${s.orderBy} ${s.sort}"
            + "</when>"
            + "<otherwise> "
            + " order by user_phone desc "
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    List<ReceiveAddress> getReceiveAddressByUserId(@Param("s") SearchVo searchVo, @Param("thisUserId") Integer thisUserId);

    /**
     * 根据用户id获取前台收货地址单条
     * @param addressId addressId
     * @return 单挑地址信息
     */
    @Select("select * from receive_address where address_id=#{addressId}")
    ReceiveAddress getReceiveAddressByAddressId(@Param("addressId") Integer addressId);

    /**
     * 修改收货地址
     * @param receiveAddress s
     * @return b
     */
    @Update("update receive_address set user_name=#{userName} , user_phone = #{userPhone} ,address = #{address} ," +
            "status = #{status} where address_id= #{addressId}")
    Boolean updateReceiveAddressByAddressId(ReceiveAddress receiveAddress);

    /**
     * 新增地址
     * @param receiveAddress r
     * @return b
     */
    @Insert("insert into receive_address (user_id,user_name,user_phone,address,status) values" +
            "(#{userId},#{userName},#{userPhone},#{address},#{status})")
    Boolean saveReceiveAddress(ReceiveAddress receiveAddress);


    /**
     * 删除地址
     * @param addressId id
     * @return b
     */
    @Delete("delete from receive_address where address_id = #{addressId}")
    Boolean delReceiveAddress(@Param("addressId") Integer addressId);

    @Select("select * from receive_address where user_id= #{userId}")
    List<ReceiveAddress> receiveAddressList(@Param("userId") Integer userId);
}
