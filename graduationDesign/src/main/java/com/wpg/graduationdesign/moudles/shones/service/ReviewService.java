package com.wpg.graduationdesign.moudles.shones.service;

import com.github.pagehelper.PageInfo;
import com.wpg.graduationdesign.moudles.shones.entity.Review;
import com.wpg.graduationdesign.vo.SearchVo;

public interface ReviewService {
    PageInfo<Review> getReviewsBySearchVo(SearchVo searchVo);

    void saveReview(Review review);
}
