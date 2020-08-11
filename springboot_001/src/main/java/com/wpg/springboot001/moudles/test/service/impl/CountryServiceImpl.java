package com.wpg.springboot001.moudles.test.service.impl;

import com.wpg.springboot001.moudles.test.dao.CountryDao;
import com.wpg.springboot001.moudles.test.pojo.Country;
import com.wpg.springboot001.moudles.test.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryDao countryDao;
    @Override
    public Country getCountryByCountryId(int countryId) {
        return countryDao.getCountryByCountryId(countryId);
    }

    @Override
    public Country getCountryByCountryName(String countryName) {
        return countryDao.getCountryByCountryName(countryName);
    }
}
