package org.example.infra.movie;

import java.time.LocalDate;
import java.util.List;

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

        public List<Genre> getGenres() {
            return genres;
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
