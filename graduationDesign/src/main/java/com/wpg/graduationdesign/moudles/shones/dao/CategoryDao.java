package com.wpg.graduationdesign.moudles.shones.dao;

import com.wpg.graduationdesign.moudles.shones.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CategoryDao {
    @Select("select * from category where category_id=#{categoryId}")
    Category getCategoryByCategoryId(int categoryId);
}
