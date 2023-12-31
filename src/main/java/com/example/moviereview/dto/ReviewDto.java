package com.example.moviereview.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {

    private long id;

    private String body;

    private LocalDateTime created;

    private LocalDateTime updated;
}
