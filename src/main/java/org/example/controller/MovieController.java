package org.example.controller;

import java.sql.SQLException;

import org.example.model.dto.User;
import org.example.model.service.MovieService;
import org.example.model.service.MovieServiceImpl;
import org.example.view.MoviePageView;
import org.example.view.ReviewPageView;

public class MovieController {
    private static final MovieService movieService = MovieServiceImpl.getInstance();

    public static void fetchMovieWithGenre(String targetDate) {
        try {
            movieService.fetchMovieWithGenre(targetDate);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //TODO: 위치 고려
    public static void createReviewByMovieName(User user, String movieName) {
        try {
            ReviewPageView.createReview(user, movieService.getMovieByMovieName(movieName));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void getMovieBasicInfo(int page, int size) {
        try {
            MoviePageView.outputMovieBasicInfo(movieService.getMovieBasicInfo(page, size));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void getMovieDetailInfo(int movieNo) {
        try {
            MoviePageView.outputMovieDetailInfo(
                    movieService.getMovieDetailInfo(movieNo));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
