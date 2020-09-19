package com.wpg.graduationdesign.moudles.shones.controller;

import com.github.pagehelper.PageInfo;
import com.wpg.graduationdesign.moudles.shones.entity.Brand;
import com.wpg.graduationdesign.moudles.shones.service.BrandService;
import com.wpg.graduationdesign.vo.Result;
import com.wpg.graduationdesign.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shone")
public class BrandController {
    @Autowired
    private BrandService brandService;

    /**查询所哟
     * 127.0.0.1/shone/brand/brands   ===post
     * {xxx}
     */
    @PostMapping(value = "/brand/brands",consumes = "application/json",produces = "application/json")
    public PageInfo<Brand> getBrandsBySearchVo(@RequestBody SearchVo searchVo){
        return brandService.getBrandsBySearchVo(searchVo);
    }


    /**插入
     * 127.0.0.1/shone/brand/brand  ===post
     * {xxx}
     */
    @PostMapping(value = "/brand/brand",consumes = "application/json")
    public Result<Brand> insertBrandsByBrand(@RequestBody Brand brand){
        return brandService.insertBrandsByBrand(brand);
    }
}
