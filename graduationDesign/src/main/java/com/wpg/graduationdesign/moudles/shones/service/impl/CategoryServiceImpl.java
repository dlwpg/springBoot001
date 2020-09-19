package com.wpg.graduationdesign.moudles.shones.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wpg.graduationdesign.moudles.shones.dao.CategoryDao;
import com.wpg.graduationdesign.moudles.shones.entity.Category;
import com.wpg.graduationdesign.moudles.shones.service.CategoryService;
import com.wpg.graduationdesign.vo.Result;
import com.wpg.graduationdesign.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDao categoryDao;

    @Override
    @Transactional
    public PageInfo<Category> getCategoriesBySearchVo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        List<Category> categoryList = categoryDao.getCategoriesBySearchVo(searchVo);
        PageInfo<Category> pageInfo = new PageInfo<Category>(Optional.ofNullable(categoryList).orElse(Collections.emptyList()));
        return pageInfo;
    }

    @Override
    @Transactional
    public Result<Category> insertCategoryByCategory(Category category) {
        Category select_category = categoryDao.getCategoryByName(category.getName());
        if (select_category != null) {
            return new Result<>(Result.Resultstatus.FAILD.status,"this name is repeat.",category);
        } else {
            categoryDao.insertCategoryByName(category);
            return new Result<>(Result.Resultstatus.SUCCESS.status,"insert success.",category);
        }
    }
}
