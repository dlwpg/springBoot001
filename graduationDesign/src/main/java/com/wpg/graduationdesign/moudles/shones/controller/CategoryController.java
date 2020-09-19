package com.wpg.graduationdesign.moudles.shones.controller;

import com.github.pagehelper.PageInfo;
import com.wpg.graduationdesign.moudles.shones.entity.Brand;
import com.wpg.graduationdesign.moudles.shones.entity.Category;
import com.wpg.graduationdesign.moudles.shones.service.CategoryService;
import com.wpg.graduationdesign.vo.Result;
import com.wpg.graduationdesign.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shone")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    /**查询所哟
     * 127.0.0.1/shone/category/categories   ===post
     * {xxx}
     */
    @PostMapping(value = "/category/categories",consumes = "application/json",produces = "application/json")
    public PageInfo<Category> getCategoriesBySearchVo(@RequestBody SearchVo searchVo){
        return categoryService.getCategoriesBySearchVo(searchVo);
    }


    /**插入
     * 127.0.0.1/shone/category/category  ===post
     * {xxx}
     */
    @PostMapping(value = "/category/category",consumes = "application/json")
    public Result<Category> insertCategoryByCategory(@RequestBody Category category){
        return categoryService.insertCategoryByCategory(category);
    }
}
