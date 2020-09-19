package com.wpg.graduationdesign.moudles.shones.service;

import com.github.pagehelper.PageInfo;
import com.wpg.graduationdesign.moudles.shones.entity.ProductImage;
import com.wpg.graduationdesign.vo.SearchVo;

public interface ProductImageService {

    PageInfo<ProductImage> getProductImageBySearchVo(SearchVo searchVo);
}
