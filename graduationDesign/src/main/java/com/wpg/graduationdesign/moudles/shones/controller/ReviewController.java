package com.wpg.graduationdesign.moudles.shones.controller;

import com.github.pagehelper.PageInfo;
import com.wpg.graduationdesign.moudles.shones.entity.Review;
import com.wpg.graduationdesign.moudles.shones.service.ReviewService;
import com.wpg.graduationdesign.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shone")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    /**
     * 127.0.0.1/shone/review/reviews  ===post
     * {xxxx}
     */
    @PostMapping("/review/reviews")
    public PageInfo<Review> getReviewsBySearchVo(@RequestBody SearchVo searchVo){
        return reviewService.getReviewsBySearchVo(searchVo);
    }
}
