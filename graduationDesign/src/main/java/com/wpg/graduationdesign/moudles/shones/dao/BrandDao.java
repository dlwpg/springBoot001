package com.wpg.graduationdesign.moudles.shones.dao;

import com.wpg.graduationdesign.moudles.shones.entity.Brand;
import com.wpg.graduationdesign.vo.SearchVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BrandDao {
    @Select("select * from brand where brand_id=#{brandId}")
    Brand getBrandByBandId(int brandId);

    @Select("select * from brand where brand_name=#{brandName}")
    Brand getBrandBybrandName(String brandName);

    @Insert("insert into brand (brand_name) values(#{brandName})" )
    @Options(useGeneratedKeys = true,keyColumn = "brand_id",keyProperty = "brandId")
    int insertBrandBybrandName(Brand brand);

    @Select("<script>" +
            "select * from brand "
            + "<where>"
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and (brand_name like '%${keyWord}%') "
            + "</if>"
            + "</where>"
            + "<choose>"
            + "<when test= 'orderBy != \"\" and orderBy != null' >"
            + "order by ${orderBy} ${sort}"
            + "</when>"
            + "<otherwise> "
            + " order by brand_id desc "
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    List<Brand> getBrandsBySearchVo(SearchVo searchVo);
}
