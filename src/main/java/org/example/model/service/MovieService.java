package org.example.model.service;

import java.sql.SQLException;
import java.util.List;

import org.example.model.dto.Movie;

public interface MovieService {

    /**
     * targetDate: 조회하고자 하는 날짜를 yyyymmdd 형식으로 입력
     * 가장 인기 많은 일별 박스오피스 API 데이터를 저장하기 위한 메서드
     * */
    void fetchMovieWithGenre(String targetDate) throws SQLException;

    /**
    * 영화가 존재하는지 확인
    * */
    Movie getMovieByMovieName(String movieName) throws SQLException;

    /**
     * 영화 기본 정보 제공: 영화 번호, 제목, 개봉년도, 감독, 장르, 평점
     * */
    List<Movie> getMovieBasicInfo(int page) throws SQLException;

    /**
     * 영화 상세 정보 제공
     * 영화 상세 정보 제공: 영화 번호, 제목, 개봉년도, 감독, 장르, 줄거리, 평점, 리뷰개수
     * */
    Movie getMovieDetailInfo(int movieNo) throws SQLException;

}
