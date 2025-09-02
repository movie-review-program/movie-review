package org.example.controller;

import org.example.model.service.MovieService;
import org.example.model.service.MovieServiceImpl;

public class MovieController {
    private static MovieService movieService = MovieServiceImpl.getInstance();

    public static void fetchMovieWithGenre(String targetDate) {
        movieService.fetchMovieWithGenre(targetDate);
    }
}
