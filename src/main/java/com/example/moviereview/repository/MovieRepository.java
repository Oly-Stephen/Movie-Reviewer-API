package com.example.moviereview.repository;

import com.example.moviereview.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {

//    Optional<Movie> findMovieByImdbId(String imdbId);
}
