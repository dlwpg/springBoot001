package com.wpg.graduationdesign.moudles.shones.dao;

import com.wpg.graduationdesign.moudles.shones.entity.Category;
import com.wpg.graduationdesign.vo.SearchVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CategoryDao {
    @Select("select * from category where category_id=#{categoryId}")
    Category getCategoryByCategoryId(int categoryId);

    @Select("select * from category where name=#{name}")
    Category getCategoryByName(String name);

    @Insert("insert into category (name) values(#{name})")
    @Options(useGeneratedKeys = true, keyColumn = "category_id", keyProperty = "categoryId")
    int insertCategoryByName(Category category);

    @Select("<script>" +
            "select * from category "
            + "<where>"
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and (name like '%${keyWord}%') "
            + "</if>"
            + "</where>"
            + "<choose>"
            + "<when test= 'orderBy != \"\" and orderBy != null' >"
            + "order by ${orderBy} ${sort}"
            + "</when>"
            + "<otherwise> "
            + " order by category_id desc "
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    List<Category> getCategoriesBySearchVo(SearchVo searchVo);
}
