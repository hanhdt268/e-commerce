package com.example.graduationbe.service;

import com.example.graduationbe.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    ReviewDto createReview(ReviewDto review, Long pid);

    List<ReviewDto> getReviews();

    ReviewDto findById(Long reId);

    ReviewDto updateActive(Long reId);

    ReviewDto updateReview(Long reId);
}
