package com.example.moviereview.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imdbId;

    private String title;

    private String releaseDate;

    private String trailerLink;

    private String poster;

    private List<String> backdrops;

    private List<String> genres;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true) //orphanRemover remove child if parent is removed
    // used set here instead of list because list allows duplicate but set does not
    private Set<Review> reviews = new HashSet<>();
}
