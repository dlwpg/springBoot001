package com.wpg.graduationdesign.moudles.shones.service;

import com.github.pagehelper.PageInfo;
import com.wpg.graduationdesign.moudles.shones.entity.Category;
import com.wpg.graduationdesign.vo.Result;
import com.wpg.graduationdesign.vo.SearchVo;

public interface CategoryService {

    PageInfo<Category> getCategoriesBySearchVo(SearchVo searchVo);

    Result<Category> insertCategoryByCategory(Category category);
}
