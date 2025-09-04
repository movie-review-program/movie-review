package org.example.model.dto;

public class Genre {
    private int genreNo;
    private String genreName;

    public Genre(String genreName) {
        this.genreName = genreName;
    }

    public Genre(int genreNo, String genreName) {
        this.genreNo = genreNo;
        this.genreName = genreName;
    }

    public int getGenreNo() {
        return genreNo;
    }

    public String getGenreName() {
        return genreName;
    }
}
