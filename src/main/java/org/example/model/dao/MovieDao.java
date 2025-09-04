package org.example.model.dao;

import java.sql.SQLException;

import org.example.model.dto.Movie;

public interface MovieDao {
    /*
     * 영화 등록하기(transactional)
     * 1) movies 테이블에 존재하지 않으면 insert -> 있다면 종료
     * 2) genres 테이블에 장르가 존재하지 않는다면 insert -> 있다면 사용
     * 2) movies_genres 테이블에 insert
     */
    int insertMovie(Movie movie) throws SQLException;
}
