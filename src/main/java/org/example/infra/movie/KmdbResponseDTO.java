package org.example.infra.movie;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

// Java 객체에는 없는 속성을 무시
@JsonIgnoreProperties(ignoreUnknown = true)
public class KmdbResponseDTO {
    @JsonProperty("Data")
    private List<Data> data;

    public List<Data> getData() {
        return data;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Data{
        @JsonProperty("Result")
        private List<Movie> result;

        public List<Movie> getResult() {
            return result;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Movie{
        @JsonProperty("plots")
        private Plots plots;

        public Plots getPlots() {
            return plots;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Plots{
        @JsonProperty("plot")
        private List<Plot> plot;

        public List<Plot> getPlot() {
            return plot;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Plot{
        @JsonProperty("plotText")
        private String plotText;

        public String getPlotText() {
            return plotText;
        }
    }


}