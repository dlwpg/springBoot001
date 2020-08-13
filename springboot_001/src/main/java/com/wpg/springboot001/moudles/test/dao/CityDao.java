package com.wpg.springboot001.moudles.test.dao;

import com.wpg.springboot001.moudles.common.vo.SearchVo;
import com.wpg.springboot001.moudles.test.pojo.City;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CityDao {

    @Select("select * from m_city where country_id=#{countryId}")
    List<City> getCitiesByCountryId(int countryId);


    //封装好的searchvo的多条件查询
    @Select("<script>" +
            "select * from m_city "
            + "<where>"
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and (city_name like '%${keyWord}%' or local_city_name like '%${keyWord}%') "
            + "</if>"
            + "</where>"
            + "<choose>"
            + "<when test= 'orderBy != \"\" and orderBy != null' >"
            + "order by ${orderBy} ${sort}"
            + "</when>"
            + "<otherwise> "
            + " order by city_ id desc "
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    List<City> getCitiesBySearchVo(SearchVo searchVo);

    //插入操作
    @Insert("insert into m_city (city_name,country_id,district,date_created) values(#{cityName},#{countryId},#{district},#{dateCreated})")
    @Options(useGeneratedKeys = true,keyColumn = "city_id",keyProperty = "cityId")
    void insertcity(City city);

    //修改操作
    @Update("update m_city set city_name=#{cityName} where city_id=#{cityId}")
    void updateCity(City city);

    //删除操作
    @Delete("delete from m_city where city_id=#{cityId}")
    void deleteCity(City city);
}
