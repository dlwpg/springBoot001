package com.wpg.graduationdesign.moudles.shones.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wpg.graduationdesign.moudles.shones.dao.ReviewDao;
import com.wpg.graduationdesign.moudles.shones.entity.Review;
import com.wpg.graduationdesign.moudles.shones.service.ReviewService;
import com.wpg.graduationdesign.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewDao reviewDao;
    @Override
    @Transactional
    public PageInfo<Review> getReviewsBySearchVo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(),searchVo.getPageSize());
        List<Review>  reviewList= Optional.ofNullable(reviewDao.getReviewsBySearchVo(searchVo)).orElse(Collections.emptyList());
        PageInfo<Review> pageInfo=new PageInfo<>(reviewList);
        return pageInfo;
    }
}
