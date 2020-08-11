package com.wpg.springboot001.moudles.test.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wpg.springboot001.moudles.common.vo.SearchVo;
import com.wpg.springboot001.moudles.test.dao.CityDao;
import com.wpg.springboot001.moudles.test.pojo.City;
import com.wpg.springboot001.moudles.test.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
    public PageInfo<City> getCitiesBySearchVo(int countryId,SearchVo searchVo) {
        PageHelper.startPage(searchVo.getCurrentPage(),searchVo.getPageSize());
        return new PageInfo<City>(Optional.ofNullable(cityDao.getCitiesByCountryId(countryId)).orElse(Collections.emptyList()));
    }
}
