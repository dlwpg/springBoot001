package com.wpg.springboot001.moudles.test.dao;

import com.wpg.springboot001.moudles.test.pojo.City;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CityDao {

    @Select("select * from m_city where country_id=#{countryId}")

    List<City> getCitiesByCountryId(int countryId);
}
