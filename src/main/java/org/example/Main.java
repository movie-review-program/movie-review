package org.example;

import org.example.controller.MovieController;

public class Main {
    public static void main(String[] args) {
        /*
        * data
        * */
        MovieController movieController = new MovieController();
        movieController.fetchMovieWithGenre("20250830");
    }
}