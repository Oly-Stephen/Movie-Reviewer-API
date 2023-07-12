package com.example.moviereview.service;

import com.example.moviereview.dto.MovieDto;
import com.example.moviereview.entity.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    MovieDto createMovie(MovieDto movieDto);
    List<MovieDto> getAllMovies();
    Optional<MovieDto> getMovieByImdbId(String imdbId);

    MovieDto getMovieById(long id);

//    PostDto createPost(PostDto postDto);
//
//    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
//
//    PostDto getPostById(long id);
//
//    PostDto updatePost(PostDto postDto, long id);
//
//    void deletePostById(long id);
//
//    List<PostDto> getPostsByCategory(Long categoryId);

}
