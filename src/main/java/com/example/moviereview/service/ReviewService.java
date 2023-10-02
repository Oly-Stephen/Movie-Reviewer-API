package com.example.moviereview.service;

import com.example.moviereview.dto.MovieDto;
import com.example.moviereview.dto.ReviewDto;

public interface ReviewService {

    ReviewDto createReview(Long movieId, ReviewDto reviewDto);

    ReviewDto getReviewById(long id);
}
