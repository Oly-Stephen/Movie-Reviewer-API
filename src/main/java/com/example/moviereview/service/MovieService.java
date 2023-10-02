package com.example.moviereview.service;

import com.example.moviereview.dto.MovieDto;
import com.example.moviereview.dto.MovieResponse;
import com.example.moviereview.entity.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    MovieDto createMovie(MovieDto movieDto);
    MovieResponse getAllMovies(int pageNo, int pageSize, String sortBy, String sortDir);
    MovieDto getMovieById(long id);
}
