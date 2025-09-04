package org.example.controller;

import java.sql.SQLException;

import org.example.model.service.MovieService;
import org.example.model.service.MovieServiceImpl;

public class MovieController {
    private static MovieService movieService = MovieServiceImpl.getInstance();

    public static void fetchMovieWithGenre(String targetDate) {
        try {
            movieService.fetchMovieWithGenre(targetDate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
