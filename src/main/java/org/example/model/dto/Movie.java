package org.example.model.dto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Movie {
    private Long movieNo;
    private String movieName;
    private String director;
    private String plot;
    private int audiCnt;
    private LocalDate openDate;
    private List<Genre> genreDTOS;

    public Movie(String movieName, String director, String plot, int audiCnt, LocalDate openDate,
            List<Genre> genreDTOS) {
        this.movieName = movieName;
        this.director = director;
        this.plot = plot;
        this.audiCnt = audiCnt;
        this.openDate = openDate;
        this.genreDTOS = genreDTOS;
    }

    public Long getMovieNo() {
        return movieNo;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getDirector() {
        return director;
    }

    public String getPlot() {
        return plot;
    }

    public int getAudiCnt() {
        return audiCnt;
    }

    public LocalDate getOpenDate() {
        return openDate;
    }

    public List<Genre> getGenreDTOS() {
        return genreDTOS;
    }
}
