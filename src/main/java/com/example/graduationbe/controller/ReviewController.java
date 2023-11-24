package com.example.graduationbe.controller;

import com.example.graduationbe.dto.ReviewDto;
import com.example.graduationbe.repository.ReviewRepository;
import com.example.graduationbe.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    private final ReviewRepository reviewRepository;

    @PostMapping("/{pid}")
    public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewDto reviewDto,
                                                  @PathVariable("pid") Long pid) {
        return ResponseEntity.ok(this.reviewService.createReview(reviewDto, pid));
    }

    @GetMapping("/stars/{pId}")
    public Float getStars(@PathVariable("pId") Long pId) {
        return this.reviewRepository.countBy(pId);
    }

    @GetMapping("/count/{pId}")
    public Long getCountByUser(@PathVariable("pId") Long pId) {
        return this.reviewRepository.countAllBy(pId);
    }
}
