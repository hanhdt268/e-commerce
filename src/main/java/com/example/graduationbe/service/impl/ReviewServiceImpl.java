package com.example.graduationbe.service.impl;

import com.example.graduationbe.config.JwtAuthenticationFilter;
import com.example.graduationbe.dto.ReviewDto;
import com.example.graduationbe.entities.User;
import com.example.graduationbe.entities.commerce.OrderDetails;
import com.example.graduationbe.entities.commerce.Product;
import com.example.graduationbe.entities.commerce.Review;
import com.example.graduationbe.repository.OrderDetailsRepository;
import com.example.graduationbe.repository.ProductRepository;
import com.example.graduationbe.repository.ReviewRepository;
import com.example.graduationbe.repository.UserRepository;
import com.example.graduationbe.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;
    private final OrderDetailsRepository orderDetailsRepository;

    @Override
//    @CacheEvict(value = {"review", "product"}, allEntries = true)
    public ReviewDto createReview(ReviewDto review, Long pid, Long orderDetailId) {
        String currentUser = JwtAuthenticationFilter.USER_CURRENT;
        User user = this.userRepository.findByUsername(currentUser);
        Product product = this.productRepository.findById(pid).orElse(null);
        OrderDetails orderDetails = this.orderDetailsRepository.findById(orderDetailId).get();
        orderDetails.setReviewed(true);
        Review review1 = this.modelMapper.map(review, Review.class);
        review1.setDateCreate(new Date());
        review1.setUser(user);
        review1.setProduct(product);
        Review save = this.reviewRepository.save(review1);
        return this.modelMapper.map(save, ReviewDto.class);
    }

    @Override
    public List<ReviewDto> getReviews() {
        List<Review> reviews = this.reviewRepository.findAll();
        return reviews.stream().map(review -> this.modelMapper.map(review, ReviewDto.class)).collect(Collectors.toList());
    }

    @Override
    public ReviewDto findById(Long reId) {
        Review review = this.reviewRepository.findById(reId).get();
        return this.modelMapper.map(review, ReviewDto.class);
    }

    @Override
    public ReviewDto updateActive(Long reId) {
        Review review = this.reviewRepository.findById(reId).get();
        review.setActive(true);
        return this.modelMapper.map(this.reviewRepository.save(review), ReviewDto.class);
    }

    @Override
    public ReviewDto updateReview(Long reId) {
        Review review = this.reviewRepository.findById(reId).get();
        review.setActive(false);
        return this.modelMapper.map(this.reviewRepository.save(review), ReviewDto.class);
    }

}
