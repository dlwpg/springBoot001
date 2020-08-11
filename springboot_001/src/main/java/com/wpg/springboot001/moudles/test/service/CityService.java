package com.wpg.springboot001.moudles.test.service;

import com.github.pagehelper.PageInfo;
import com.wpg.springboot001.moudles.common.vo.SearchVo;
import com.wpg.springboot001.moudles.test.pojo.City;

import java.util.List;

public interface CityService {
    //通过countryid查询城市
    List<City> getCitiesByCountryId(int countryId);

    //分页查询
    PageInfo<City> getCitiesBySearchVo(int countryId,SearchVo searchVo);
}
