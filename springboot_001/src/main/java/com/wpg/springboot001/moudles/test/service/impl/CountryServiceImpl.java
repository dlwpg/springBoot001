package com.wpg.springboot001.moudles.test.service.impl;

import com.wpg.springboot001.moudles.test.dao.CountryDao;
import com.wpg.springboot001.moudles.test.pojo.Country;
import com.wpg.springboot001.moudles.test.service.CountryService;
import com.wpg.springboot001.moudles.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryDao countryDao;
    @Autowired
    private RedisUtils redisUtils;


    @Override
    @Transactional
    public Country getCountryByCountryId(int countryId) {
        return countryDao.getCountryByCountryId(countryId);
    }

    @Override
    @Transactional
    public Country getCountryByCountryName(String countryName) {
        return countryDao.getCountryByCountryName(countryName);
    }

    //redis  调用前必须开启redis数据库的服务器端---》e:redis/service.exe
    @Override
    @Transactional
    public Country getCountryByredis(int countryId) {

        //查询数据库
        Country country=countryDao.getCountryByCountryId(countryId);
        //格式化countryId
        String  countryKey=String.format("country%d",countryId);
        //将数据查询出来,存入redis
        redisUtils.set(countryKey,country);
        //返回redis中的内容
        Country countryandcities=(Country) redisUtils.get(countryKey);

        return countryandcities;
    }
}
