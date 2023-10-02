package com.example.moviereview.controller;

import com.example.moviereview.dto.MovieDto;
import com.example.moviereview.dto.MovieResponse;
import com.example.moviereview.service.MovieService;
import com.example.moviereview.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/movies")
public class
MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public ResponseEntity<MovieDto> addMovie(@RequestBody MovieDto movieDto){
        MovieDto savedMovie = movieService.createMovie(movieDto);
        return new ResponseEntity<>(savedMovie, HttpStatus.CREATED);
    }

    @GetMapping
    //http://localhost:8080/api/v1/movies?pageNo=0&pageSize=10&sortBy=releaseDate&sortDir=asc
    public MovieResponse getAllMovies(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIR, required = false) String sortDir
    ){
        return movieService.getAllMovies(pageNo, pageSize, sortBy, sortDir);
    }

    // get movie by id
    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(movieService.getMovieById(id));
    }
}
