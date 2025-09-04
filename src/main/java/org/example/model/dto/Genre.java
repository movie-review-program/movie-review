package org.example.model.dto;

public class Genre {
    private Long genreNo;
    private String genreName;

    public Genre(String genreName) {
        this.genreName = genreName;
    }

    public Genre(Long genreNo, String genreName) {
        this.genreNo = genreNo;
        this.genreName = genreName;
    }

    public Long getGenreNo() {
        return genreNo;
    }

    public String getGenreName() {
        return genreName;
    }
}
