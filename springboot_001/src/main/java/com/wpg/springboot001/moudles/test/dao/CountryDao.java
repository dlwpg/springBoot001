package com.wpg.springboot001.moudles.test.dao;

import com.wpg.springboot001.moudles.test.pojo.Country;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CountryDao {


    @Select("select * from m_country where country_id=#{countryId}")
    //使用了id 下面用@ResultMap(value = "country_cities")就可以访问
    @Results(id = "country_cities",value = {
            //使用了country_id接受cities后本身被占用，二次映射本身
            @Result(column = "country_id",property = "countryId"),
            //用country_id字段装cities集合，实体类Country里有cities属性
            @Result(column = "country_id",property = "cities",
                    javaType = List.class,
                    //cites映射的包名.类名.方法名
                    many = @Many(select = "com.wpg.springboot001.moudles.test.dao.CityDao.getCitiesByCountryId"))
    })
    Country getCountryByCountryId(int countryId);

    @Select("select * from m_country where country_name=#{countryName}")
    @ResultMap(value = "country_cities")
    Country getCountryByCountryName(String  countryName);
}
