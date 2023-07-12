package com.example.moviereview.controller;

import com.example.moviereview.dto.ReviewDto;
import com.example.moviereview.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/movies")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/{movieId}/reviews")
    public ResponseEntity<ReviewDto> createReview(@PathVariable(value = "movieId") Long movieId,
                                                    @RequestBody ReviewDto reviewDto){
        return new ResponseEntity<>(reviewService.createReview(movieId, reviewDto), HttpStatus.CREATED);
    }
}
