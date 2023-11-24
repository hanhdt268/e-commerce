package com.example.graduationbe.service;

import com.example.graduationbe.dto.ReviewDto;

public interface ReviewService {
    ReviewDto createReview(ReviewDto review, Long pid);
}
