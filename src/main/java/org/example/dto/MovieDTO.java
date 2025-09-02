package org.example.dto;

import java.util.Date;
import java.util.List;

public class MovieDTO {
    private Long movieNo;
    private String movieName;
    private String director;
    private String plot;
    private int audiCnt;
    private Date openDate;
    private List<GenreDTO> genreDTOS;

    MovieDTO(String movieName, String director, String plot, int audiCnt) {
        this.movieName = movieName;
        this.director = director;
        this.plot = plot;
        this.audiCnt = audiCnt;
    }



}
