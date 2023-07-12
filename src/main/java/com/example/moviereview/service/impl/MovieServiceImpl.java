package com.example.moviereview.service.impl;

import com.example.moviereview.dto.MovieDto;
import com.example.moviereview.entity.Movie;
import com.example.moviereview.exception.ImbdException;
import com.example.moviereview.exception.ResourceNotFoundException;
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
    public MovieDto createMovie(MovieDto movieDto) {
        Movie movie = modelMapper.map(movieDto, Movie.class);
        Movie savedMovie = movieRepository.save(movie);
        return modelMapper.map(savedMovie, MovieDto.class);
    }

    @Override
    public List<MovieDto> getAllMovies() {

        List<Movie> movies = movieRepository.findAll();
        return movies.stream().map((movie) -> modelMapper.map(movie, MovieDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MovieDto> getMovieByImdbId(String imdbId) {
        Movie movie = movieRepository.findMovieByImdbId(imdbId)
                .orElseThrow(()-> new ImbdException("Movie", "imdbId", imdbId));
        return Optional.ofNullable(mapToDTO(movie));
    }

    @Override
    public MovieDto getMovieById(long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Movie", "id", id));
        return mapToDTO(movie);
    }


    // Convert entity to DTO
    private MovieDto mapToDTO(Movie movie){
        MovieDto movieDto = modelMapper.map(movie, MovieDto.class);
        return movieDto;
    }

    // convert DTO to entity
    private Movie mapToEntity(MovieDto movieDto){
        Movie movie = modelMapper.map(movieDto, Movie.class);
        return movie;
    }
}
