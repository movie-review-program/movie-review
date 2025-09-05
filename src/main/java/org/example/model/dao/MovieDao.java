package org.example.model.dao;

import java.sql.SQLException;
import java.util.List;

import org.example.model.dto.Movie;

public interface MovieDao {
    /**
     * 영화 등록하기(transactional)
     * 1) movies 테이블에 존재하지 않으면 insert -> 있다면 종료
     * 2) genres 테이블에 장르가 존재하지 않는다면 insert -> 있다면 사용
     * 2) movies_genres 테이블에 insert
     */
    int insertMovie(Movie movie) throws SQLException;

    /**
     * 영화 이름을 통해 존재하는 영화이름인지 확인
     * */
    Movie selectMovieName(String movieName) throws Exception;

    /**
     * 영화 기본 정보 페이징 조회
     * 정렬 순서: 최신순
     * */
    List<Movie> selectMovieBasicPage(int page, int size) throws Exception;

    /**
    * 영화 기본 정보 조회
     * */
    Movie selectMovieBasic(int movieNo) throws Exception;

    /**
     * * 영화 상세 조회
     * */
    Movie selectMovieDetail(int movieNo) throws Exception;



}
