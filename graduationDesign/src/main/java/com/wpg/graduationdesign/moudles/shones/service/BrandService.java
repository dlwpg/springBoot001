package com.wpg.graduationdesign.moudles.shones.service;

import com.github.pagehelper.PageInfo;
import com.wpg.graduationdesign.moudles.shones.entity.Brand;
import com.wpg.graduationdesign.vo.Result;
import com.wpg.graduationdesign.vo.SearchVo;

public interface BrandService {

    PageInfo<Brand> getBrandsBySearchVo(SearchVo searchVo);

    Result<Brand> insertBrandsByBrand(Brand brand);
}
