package org.example.infra.movie;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.example.model.dto.Genre;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class KobisDetailResponseDTO {
    @JsonProperty("movieInfoResult")
    private MovieInfoResult movieInfoResult;

    public MovieInfoResult getMovieInfoResult() {
        return movieInfoResult;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MovieInfoResult {
        @JsonProperty("movieInfo")
        private MovieInfo movieInfo;

        public MovieInfo getMovieInfo() {
            return movieInfo;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MovieInfo {
        @JsonProperty("movieNm")
        private String movieName;

        @JsonProperty("openDt")
        @JsonFormat(pattern = "yyyyMMdd")
        private LocalDate openDate;

        @JsonProperty("genres")
        private List<Genre> genres;

        @JsonProperty("directors")
        private List<Director> directors;

        public String getMovieName() {
            return movieName;
        }

        public LocalDate getOpenDate() {
            return openDate;
        }

        //TODO: 리팩토링 진행 필요
        public List<org.example.model.dto.Genre> getGenres() {
            List<org.example.model.dto.Genre> genreToStringList = new ArrayList<>();
            for (Genre g : genres) {
                org.example.model.dto.Genre genre = new org.example.model.dto.Genre(g.genreName);
                genreToStringList.add(genre);
            }
            return genreToStringList;
        }

        public List<Director> getDirectors() {
            return directors;
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Genre {
            @JsonProperty("genreNm")
            private String genreName;

            public String getGenreName() {
                return genreName;
            }
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Director {
            @JsonProperty("peopleNm")
            private String peopleName;

            public String getPeopleName() {
                return peopleName;
            }
        }


    }
}
