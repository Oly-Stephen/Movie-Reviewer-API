package com.example.moviereview.controller;

import com.example.moviereview.dto.ReviewDto;
import com.example.moviereview.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/movie-review")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

//    @PostMapping("/{id}")
//    public ResponseEntity<ReviewDto> createReview(@PathVariable(value = "id") Long movieId,
//                                                    @RequestBody ReviewDto reviewDto){
//        return new ResponseEntity<>(reviewService.createReview(movieId, reviewDto), HttpStatus.CREATED);
//    }

    @PostMapping("/{movieId}/reviews")
    public ResponseEntity<ReviewDto> createComment(@PathVariable(value = "movieId") Long movieId,
                                                    @RequestBody ReviewDto reviewDto){
        return new ResponseEntity<>(reviewService.createReview(movieId, reviewDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable(value = "id") long id){
        return ResponseEntity.ok(reviewService.getReviewById(id));
    }
}
