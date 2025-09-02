package org.example.model.dao;

import java.time.LocalDate;
import java.util.List;

public interface MovieDao {
    /*
     * 영화 등록하기
     * 1) genres 테이블에 장르가 존재하지 않는다면 insert
     * 2) movies 테이블에 insert
     * 2) movies_genres 테이블에 insert
     */
    int insertMovie(String movieName, String director, String audiCnt,
            LocalDate openDate, List<String> genres, String plot);
}
