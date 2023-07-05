package com.example.moviereview.service.impl;

import com.example.moviereview.dto.MovieDto;
import com.example.moviereview.entity.Movie;
import com.example.moviereview.repository.MovieRepository;
import com.example.moviereview.service.MovieService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    private ModelMapper modelMapper;

    public MovieServiceImpl(MovieRepository movieRepository,
                            ModelMapper modelMapper) {
        this.movieRepository = movieRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<MovieDto> getAllMovies() {

        List<Movie> movies = movieRepository.findAll();
        return movies.stream().map((movie) -> modelMapper.map(movie, MovieDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public MovieDto createMovie(MovieDto movieDto) {
        Movie movie = modelMapper.map(movieDto, Movie.class);
        Movie savedMovie = movieRepository.save(movie);
        return modelMapper.map(savedMovie, MovieDto.class);
    }

//    @Override
//    public Optional<MovieDto> findMovieByImdbId(String imdbId) {
//        return movieRepository.findMovieByImdbId(imdbId);
//    }
}
