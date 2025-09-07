package org.example.view;

import java.util.List;

import org.example.controller.MovieController;
import org.example.model.dto.Movie;

public class TestViewGJ {
    public static void test1(Movie movie) {
        System.out.println(movie.toString());
    }

    public static void test2(List<Movie> movieBasicInfo) {
        for (Movie m : movieBasicInfo) {
            System.out.println(m.toString());
        }
    }

    public static void test3(Movie movie) {
        System.out.println(movie.toString());
    }

    public static void main(String[] args) {
        MovieController.getMovieByMovieName("하얼빈");
        MovieController.getMovieBasicInfo(2, 3);
        MovieController.getMovieDetailInfo(1);
    }

}
