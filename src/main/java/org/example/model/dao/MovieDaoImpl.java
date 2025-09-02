package org.example.model.dao;

import java.time.LocalDate;
import java.util.List;

public class MovieDaoImpl implements MovieDao {

    //TODO: dao sql문 작성
    @Override
    public int insertMovie(String movieName, String director, String audiCnt,
            LocalDate openDate, List<String> genres, String plot) {
        return 0;
    }
}
