package com.wpg.graduationdesign.moudles.shones.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wpg.graduationdesign.moudles.shones.dao.ProductImageDao;
import com.wpg.graduationdesign.moudles.shones.entity.ProductImage;
import com.wpg.graduationdesign.moudles.shones.service.ProductImageService;
import com.wpg.graduationdesign.moudles.shones.service.ProductService;
import com.wpg.graduationdesign.vo.Result;
import com.wpg.graduationdesign.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProductImageServiceImpl implements ProductImageService {
    @Autowired
    private ProductImageDao productImageDao;
    @Override
    @Transactional
    public PageInfo<ProductImage> getProductImageBySearchVo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(),searchVo.getPageSize());
        List<ProductImage> productImageList= Optional.ofNullable(productImageDao
                .getProductImageBySearchVo(searchVo)).orElse(Collections.emptyList());
        PageInfo<ProductImage> pageinfo=new PageInfo<>(productImageList);
        return pageinfo;
    }
}
