package org.example.model.dto;

import java.time.LocalDate;
import java.util.List;

public class Movie {
    private int movieNo;
    private String movieName;
    private String director;
    private LocalDate openDate;
    private String plot;
    private int audiCnt;
    private List<Genre> genreDTOS;
    private double ratings;
    private int reviewCnt;

    private Movie() {
    }

    /*
     * 데이터 등록
     * */
    public Movie(String movieName, String director, LocalDate openDate, String plot, int audiCnt,
            List<Genre> genreDTOS) {
        super();
        this.movieName = movieName;
        this.director = director;
        this.openDate = openDate;
        this.plot = plot;
        this.audiCnt = audiCnt;
        this.genreDTOS = genreDTOS;
    }

    public Movie(int movieNo, String movieName, String director, LocalDate openDate, String plot, int audiCnt,
            List<Genre> genreDTOS, double ratings, int reviewCnt) {
        super();
        this.movieNo = movieNo;
        this.movieName = movieName;
        this.director = director;
        this.openDate = openDate;
        this.plot = plot;
        this.audiCnt = audiCnt;
        this.genreDTOS = genreDTOS;
        this.ratings = ratings;
        this.reviewCnt = reviewCnt;
    }

    public int getMovieNo() {
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

    public double getRatings() {
        return ratings;
    }

    public int getReviewCnt() {
        return reviewCnt;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Movie{");
        sb.append("movieNo=").append(movieNo);
        sb.append(", movieName='").append(movieName).append('\'');
        sb.append(", director='").append(director).append('\'');
        sb.append(", openDate=").append(openDate);
        sb.append(", plot='").append(plot).append('\'');
        sb.append(", audiCnt=").append(audiCnt);
        sb.append(", genreDTOS=").append(genreDTOS);
        sb.append(", ratings=").append(ratings);
        sb.append(", reviewCnt=").append(reviewCnt);
        sb.append('}');
        return sb.toString();
    }
}
