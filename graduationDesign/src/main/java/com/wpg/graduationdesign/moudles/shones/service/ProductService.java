package com.wpg.graduationdesign.moudles.shones.service;

import com.github.pagehelper.PageInfo;
import com.wpg.graduationdesign.moudles.shones.entity.Product;
import com.wpg.graduationdesign.vo.SearchVo;

public interface ProductService {
    //查询所有商品信息
    PageInfo<Product> getProdcutsBySearchVo(SearchVo searchVo);
}
