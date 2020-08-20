package com.wpg.springboot001.moudles.test.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wpg.springboot001.vo.Result;
import com.wpg.springboot001.vo.SearchVo;
import com.wpg.springboot001.moudles.test.dao.CityDao;
import com.wpg.springboot001.moudles.test.pojo.City;
import com.wpg.springboot001.moudles.test.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {


    @Autowired
    private CityDao cityDao;

    @Override
    public List<City> getCitiesByCountryId(int countryId) {
        return Optional
                //不为空查询
                .ofNullable(cityDao.getCitiesByCountryId(countryId))
                //为空返回空list没有null
                .orElse(Collections.emptyList());
    }

    //分页查询
    @Override
    public PageInfo<City> getCitiesBySearchVo(int countryId, SearchVo searchVo) {
        //如果前端没有传值初始化默认值
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        return new PageInfo<City>(Optional
                .ofNullable(cityDao.getCitiesByCountryId(countryId))
                .orElse(Collections.emptyList()));
    }

    //多条件查询
    @Override
    public PageInfo<City> getCitiesBySearchVo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        return new PageInfo<City>(Optional
                .ofNullable(cityDao.getCitiesBySearchVo(searchVo))
                .orElse(Collections.emptyList()));
    }

    //插入操作

    @Override
    @Transactional
    public Result<City> insertcity(City city) {
        city.setDateCreated(new Date());
        cityDao.insertcity(city);
        //构造一个Result
        return new Result<City>(Result.Resultstatus.SUCCESS.status,"insert success!",city);
    }

    //修改操作

    @Override
    @Transactional
    public Result<City> updateCity(City city) {
        cityDao.updateCity(city);
        return  new Result<City>(Result.Resultstatus.SUCCESS.status,"update success!",city);
    }

    //删除

    @Override
    @Transactional
    public Result<City> deleteCity(Integer cityId) {
        City city=new City();
        city.setCityId(cityId);
        cityDao.deleteCity(city);
        return new Result<City>(Result.Resultstatus.SUCCESS.status,"delete success!",city);
    }
}
