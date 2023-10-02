package com.example.moviereview.service.impl;

import com.example.moviereview.dto.MovieDto;
import com.example.moviereview.dto.MovieResponse;
import com.example.moviereview.entity.Movie;
import com.example.moviereview.exception.ResourceNotFoundException;
import com.example.moviereview.repository.MovieRepository;
import com.example.moviereview.service.MovieService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public MovieResponse getAllMovies(int pageNo, int pageSize, String  sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Movie> movies = movieRepository.findAll(pageable);

        // get content for page object
        List<Movie> listOfMovies = movies.getContent();

        List<MovieDto> content = listOfMovies.stream().map(movie -> mapToDTO(movie)).collect(Collectors.toList());
        MovieResponse movieResponse = new MovieResponse();
        movieResponse.setContent(content);
        movieResponse.setPageNo(movies.getNumber());
        movieResponse.setPageSize((movies.getSize()));
        movieResponse.setTotalElements(movies.getTotalElements());
        movieResponse.setTotalPages(movies.getTotalPages());
        movieResponse.setLast(movies.isLast());
        return movieResponse;
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
