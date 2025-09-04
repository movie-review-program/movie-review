package org.example.controller;

import java.sql.SQLException;
import java.util.List;

import org.example.model.dto.Movie;
import org.example.model.service.MovieService;
import org.example.model.service.MovieServiceImpl;
import org.example.view.TestViewGJ;

public class MovieController {
    private static MovieService movieService = MovieServiceImpl.getInstance();

    public static void fetchMovieWithGenre(String targetDate) {
        try {
            movieService.fetchMovieWithGenre(targetDate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getMovieByMovieName(String movieName) {
        try {
            TestViewGJ.test1(movieService.getMovieByMovieName(movieName));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getMovieBasicInfo(int page) {
        try {
            TestViewGJ.test2(movieService.getMovieBasicInfo(page));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getMovieDetailInfo(int movieNo) {
        try {
            TestViewGJ.test3(movieService.getMovieDetailInfo(movieNo));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
