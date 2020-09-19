package com.wpg.graduationdesign.moudles.shones.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wpg.graduationdesign.moudles.shones.dao.BrandDao;
import com.wpg.graduationdesign.moudles.shones.entity.Brand;
import com.wpg.graduationdesign.moudles.shones.service.BrandService;
import com.wpg.graduationdesign.vo.Result;
import com.wpg.graduationdesign.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandDao brandDao;


    @Override
    @Transactional
    public PageInfo<Brand> getBrandsBySearchVo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        List<Brand> brandList = brandDao.getBrandsBySearchVo(searchVo);
        PageInfo<Brand> pageInfo = new PageInfo<>(Optional.ofNullable(brandList).orElse(Collections.emptyList()));
        return pageInfo;
    }

    @Override
    @Transactional
    public Result<Brand> insertBrandsByBrand(Brand brand) {
        Brand select_brand = brandDao.getBrandBybrandName(brand.getBrandName());
        if (select_brand != null) {
            return new Result<>(Result.Resultstatus.FAILD.status,"this name is repeat.",brand);
        } else {
            brandDao.insertBrandBybrandName(brand);
        }

        return new Result<>(Result.Resultstatus.SUCCESS.status,"insert success.",brand);
    }
}
