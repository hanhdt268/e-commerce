package com.example.graduationbe.service.impl;

import com.example.graduationbe.config.JwtAuthenticationFilter;
import com.example.graduationbe.dto.ReviewDto;
import com.example.graduationbe.entities.User;
import com.example.graduationbe.entities.commerce.Product;
import com.example.graduationbe.entities.commerce.Review;
import com.example.graduationbe.repository.ProductRepository;
import com.example.graduationbe.repository.ReviewRepository;
import com.example.graduationbe.repository.UserRepository;
import com.example.graduationbe.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Override
    @CacheEvict(value = {"review", "product"}, allEntries = true)
    public ReviewDto createReview(ReviewDto review, Long pid) {
        String currentUser = JwtAuthenticationFilter.USER_CURRENT;
        User user = this.userRepository.findByUsername(currentUser);
        Product product = this.productRepository.findById(pid).orElse(null);
        Review review1 = this.modelMapper.map(review, Review.class);
        review1.setDateCreate(new Date());
        review1.setUser(user);
        review1.setProduct(product);
        Review save = this.reviewRepository.save(review1);
        return this.modelMapper.map(save, ReviewDto.class);
    }
}
