package org.example.controller;

import org.example.service.MovieService;
import org.example.service.MovieServiceImpl;

public class MovieController {
    private static MovieService movieService = MovieServiceImpl.getInstance();

    public void fetchMovieWithGenre(String targetDate) {
        movieService.fetchMovieWithGenre(targetDate);
    }
}
