package com.example.moviereview.controller;

import com.example.moviereview.dto.MovieDto;
import com.example.moviereview.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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

    // Build Get All Movies REST API
    @GetMapping
    public ResponseEntity<List<MovieDto>> getAllMovies(){
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    // get movie by id
    @GetMapping("/movieId/{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(movieService.getMovieById(id));
    }

    // get movie by imdbID
    @GetMapping("/{imdbId}")
    public ResponseEntity<Optional<MovieDto>> getMovieByImdbId(@PathVariable(name = "imdbId") String imdbId){
        return ResponseEntity.ok(movieService.getMovieByImdbId(imdbId));
    }


}
