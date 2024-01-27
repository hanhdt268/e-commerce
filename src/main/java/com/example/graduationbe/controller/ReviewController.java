package com.example.graduationbe.controller;

import com.example.graduationbe.dto.ReviewDto;
import com.example.graduationbe.repository.ReviewRepository;
import com.example.graduationbe.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    private final ReviewRepository reviewRepository;

    @PostMapping("/{pid}/{orderDetailId}")
    public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewDto reviewDto,
                                                  @PathVariable("pid") Long pid,
                                                  @PathVariable("orderDetailId") Long orderDetailId) {
        return ResponseEntity.ok(this.reviewService.createReview(reviewDto, pid, orderDetailId));
    }

    @GetMapping("/stars/{pId}")
    public Float getStars(@PathVariable("pId") Long pId) {
        return this.reviewRepository.countBy(pId);
    }

    @GetMapping("/count/{pId}")
    public Long getCountByUser(@PathVariable("pId") Long pId) {
        return this.reviewRepository.countAllBy(pId);
    }

    @GetMapping("/")
    public List<ReviewDto> getReviews() {
        return this.reviewService.getReviews();
    }

    @GetMapping("/{reId}")
    public ReviewDto getReviewById(@PathVariable("reId") Long reId) {
        return this.reviewService.findById(reId);
    }

    @GetMapping("/active/{reId}")
    private ReviewDto updateReview(@PathVariable("reId") Long reId) {
        return this.reviewService.updateActive(reId);
    }

    @GetMapping("/inActive/{reId}")
    private ReviewDto update(@PathVariable("reId") Long reId) {
        return this.reviewService.updateReview(reId);
    }

}
