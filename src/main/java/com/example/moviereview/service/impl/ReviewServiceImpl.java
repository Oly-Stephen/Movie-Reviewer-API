package com.example.moviereview.service.impl;

import com.example.moviereview.dto.ReviewDto;
import com.example.moviereview.entity.Movie;
import com.example.moviereview.entity.Review;
import com.example.moviereview.exception.ResourceNotFoundException;
import com.example.moviereview.repository.MovieRepository;
import com.example.moviereview.repository.ReviewRepository;
import com.example.moviereview.service.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;
    private ModelMapper modelMapper;

    public ReviewServiceImpl(ReviewRepository reviewRepository,
                             MovieRepository movieRepository,
                             ModelMapper modelMapper) {
        this.reviewRepository = reviewRepository;
        this.movieRepository = movieRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ReviewDto createReview(Long movieId, ReviewDto reviewDto) {
        Review review = mapToEntity(reviewDto);

        // retrieve movie by id
        Movie movie = movieRepository.findById(movieId).orElseThrow(
                () -> new ResourceNotFoundException("Movie", "id", movieId));

        // set post to comment entity
        review.setBody(review.getBody());
        review.setCreated(LocalDateTime.now());
        review.setUpdated(LocalDateTime.now());

        // save comment entity in DB
        Review newReview = reviewRepository.save(review);
        return mapToDto(newReview);
    }

    @Override
    public ReviewDto getReviewById(long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Review", "id", id));
        return mapToDto(review);
    }

    // Convert entity to DTO
    private ReviewDto mapToDto(Review review){
        ReviewDto reviewDto = modelMapper.map(review, ReviewDto.class);
        return reviewDto;
    }

    // convert DTO to entity
    private Review mapToEntity(ReviewDto reviewDto){
        Review review = modelMapper.map(reviewDto, Review.class);
        return review;
    }
}
