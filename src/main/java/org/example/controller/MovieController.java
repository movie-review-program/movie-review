package org.example.controller;

import java.sql.SQLException;

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getMovieBasicInfo(int page, int size) {
        try {
            TestViewGJ.test2(movieService.getMovieBasicInfo(page, size));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getMovieDetailInfo(int movieNo) {
        try {
            TestViewGJ.test3(movieService.getMovieDetailInfo(movieNo));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
