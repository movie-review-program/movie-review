package org.example.service;

public interface MovieService {

    /*
     * targetDate: 조회하고자 하는 날짜를 yyyymmdd 형식으로 입력
     * */
    void fetchMovieWithGenre(String targetDate);
}
