package com.wpg.springboot001.moudles.test.service;

import com.wpg.springboot001.moudles.test.pojo.Country;

public interface CountryService {
    //通过国家id查找国家信息
    Country getCountryByCountryId(int countryId);

    //通过国家名称查找国家信息
    Country getCountryByCountryName(String  countryName);
}
