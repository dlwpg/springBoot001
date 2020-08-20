package com.wpg.springboot001.moudles.test.service;

import com.github.pagehelper.PageInfo;
import com.wpg.springboot001.vo.Result;
import com.wpg.springboot001.vo.SearchVo;
import com.wpg.springboot001.moudles.test.pojo.City;

import java.util.List;

public interface CityService {
    //通过countryid查询城市
    List<City> getCitiesByCountryId(int countryId);

    //分页查询
    PageInfo<City> getCitiesBySearchVo(int countryId,SearchVo searchVo);

    //多条件查询
    PageInfo<City> getCitiesBySearchVo(SearchVo searchVo);

    //插入操作
    Result<City> insertcity(City city);

    //修改操作
    Result<City> updateCity(City city);

    //删除操作
    Result<City> deleteCity(Integer cityId);
}
