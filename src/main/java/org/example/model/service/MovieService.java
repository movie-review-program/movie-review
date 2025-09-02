package org.example.model.service;

public interface MovieService {

    /*
     * targetDate: 조회하고자 하는 날짜를 yyyymmdd 형식으로 입력
     * 가장 인기 많은 일별 박스오피스 API 데이터를 저장하기 위한 메서드
     * */
    void fetchMovieWithGenre(String targetDate);
}
