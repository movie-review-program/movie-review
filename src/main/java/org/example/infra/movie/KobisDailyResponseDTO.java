package org.example.infra.movie;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class KobisDailyResponseDTO {
    @JsonProperty("boxOfficeResult")
    private BoxOfficeResult boxOfficeResult;

    public BoxOfficeResult getBoxOfficeResult() {
        return boxOfficeResult;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class BoxOfficeResult {
        @JsonProperty("dailyBoxOfficeList")
        private List<DailyBoxOffice> dailyBoxOfficeList;

        public List<DailyBoxOffice> getDailyBoxOfficeList() {
            return dailyBoxOfficeList;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DailyBoxOffice {
        @JsonProperty("movieCd")
        private String movieCd;

        @JsonProperty("audiAcc")
        private String audiCnt;

        public String getMovieCd() {
            return movieCd;
        }

        public String getAudiCnt() {
            return audiCnt;
        }
    }
}
