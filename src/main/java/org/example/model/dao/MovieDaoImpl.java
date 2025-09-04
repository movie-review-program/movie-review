package org.example.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.example.model.dto.Genre;
import org.example.model.dto.Movie;
import org.example.util.DBManager;
import org.example.util.DBManagerImpl;

public class MovieDaoImpl implements MovieDao {
    DBManager dbManager = new DBManagerImpl();

    @Override
    public int insertMovie(Movie movie) throws SQLException {
        int result = 0;
        String sql = """
                insert into movies(movie_name, director, open_date, plot, audi_cnt)
                values (?, ?, ?, ?, ?)
                """;
        try(Connection con = dbManager.getConnection();
                PreparedStatement ps = con.prepareStatement(sql
                        ,PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            //start
            con.setAutoCommit(false);
            ps.setString(1, movie.getMovieName());
            ps.setString(2, movie.getDirector());
            ps.setDate(3, Date.valueOf(movie.getOpenDate()));
            ps.setString(4, movie.getPlot());
            ps.setInt(5, movie.getAudiCnt());

            //영화 삽입 후 삽입된 내용이 존재하지 않으면 종료
            int inserted = ps.executeUpdate();
            if (inserted != 1) {
                con.rollback();
                throw new SQLException("이미 존재하는 영화입니다");
            } else {
                ResultSet rs = ps.getGeneratedKeys();

                //movie_no
                //rs를 사용할 때는 무조건 if나 for 문을 통해서 사용
                long movieNo = 0;
                if (rs.next()) {
                    movieNo = rs.getLong(1);
                }

                //TODO: 분기 문제 해결 (null일 때 삽입하는 과정에서의 문제점)
                for (Genre gen : movie.getGenreDTOS()) {
                    Genre genre = selectGenreByGenreName(con, gen.getGenreName());
                    //장르가 없다면 삽입
                    if (genre == null) {
                        insertGenres(con, gen);
                        genre = selectGenreByGenreName(con, gen.getGenreName());
                    }
                    result = insertMoviesGenres(con, movieNo, genre.getGenreNo());

                    if (result != 1) {
                        con.rollback();
                        throw new SQLException("이미 존재하는 영화입니다");
                    }
                }
            }

            //end
            con.commit();
            return result;
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }


    /*
     * 장르가 존재 여부
     * */
    private Genre selectGenreByGenreName(Connection ts, String genreName) throws SQLException {
        Genre genre = null;
        String sql = """
                select * from genres where genre_name = ?
                """;
        try (PreparedStatement ps = ts.prepareStatement(sql)) {
            ps.setString(1, genreName);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    genre = new Genre(
                            rs.getLong(1),
                            rs.getString(2)
                    );
                }
            }

            return genre;
        }
    }

    /*
     * 하나의 장르를 insert
     * */
    private int insertGenres(Connection ts, Genre genre) throws SQLException {
        String sql = """
                insert into genres(genre_name)
                values (?)
                """;
        try (PreparedStatement ps = ts.prepareStatement(sql)) {
            ps.setString(1, genre.getGenreName());

            return ps.executeUpdate();
        }
    }

    private int insertMoviesGenres(Connection ts, long movieNo, long genreNo) throws SQLException {
        String sql = """
                insert into movies_genres(movie_no, genre_no) 
                values (?, ?)
                """;
        try(PreparedStatement ps = ts.prepareStatement(sql)) {
            ps.setLong(1, movieNo);
            ps.setLong(2, genreNo);
            return ps.executeUpdate();
        }
    }

}
