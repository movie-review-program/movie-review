package org.example.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.model.dto.Genre;
import org.example.model.dto.Movie;
import org.example.util.DBManager;
import org.example.util.DBManagerImpl;

public class MovieDaoImpl implements MovieDao {
    DBManager dbManager = new DBManagerImpl();
    ReviewDao reviewDao = ReviewDaoImpl.getInstance();

    @Override
    public int insertMovie(Movie movie) throws SQLException {
        int result = 0;
        String sql = """
                insert into movies(movie_name, director, open_date, plot, audi_cnt)
                values (?, ?, ?, ?, ?)
                """;
        try (Connection con = dbManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql
                     , PreparedStatement.RETURN_GENERATED_KEYS)
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
                int movieNo = 0;
                if (rs.next()) {
                    movieNo = rs.getInt(1);
                }

                //TODO: 분기 문제 해결 (null일 때 삽입하는 과정에서의 문제점)
                for (Genre gen : movie.getGenreDTOS()) {
                    Genre genre = selectGenreByGenreName(con, gen.getGenreName());
                    //장르가 없다면 삽입
                    if (genre == null) {
                        int insertGenre = insertGenres(con, gen);
                        if (insertGenre != 1) {
                            throw new SQLException("삽입에 실패하였습니다 ");
                        }
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

    @Override
    public Movie selectMovieName(String movieName) throws Exception {
        Movie movie = null;
        String sql = """
                select *
                from movies
                where movie_name = ?
                """;
        try (Connection con = dbManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, movieName);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int movieNo = rs.getInt(1);
                    List<Genre> genres = selectGenresByMovieNo(movieNo);
                    double ratings = reviewDao.getAverageRating(movieNo);
                    int reviewCnt = reviewDao.getReviewCount(movieNo);

                    movie = new Movie(
                            movieNo,
                            rs.getString(2),
                            rs.getString(3),
                            rs.getDate(4).toLocalDate(),
                            rs.getString(5),
                            rs.getInt(6),
                            genres,
                            ratings,
                            reviewCnt
                    );
                }
            }
        }
        return movie;
    }

    @Override
    public List<Movie> selectMovieBasicPage(int page, int size) throws Exception {
        List<Movie> movies = new ArrayList<>();
        String sql = """
                select *
                from movies
                order by open_date desc
                limit ? offset ?
                """;

        int offset = (page - 1) * size;
        try(Connection con = dbManager.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, size);
            ps.setInt(2, offset);

            try(ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int movieNo = rs.getInt(1);
                    List<Genre> genres = selectGenresByMovieNo(movieNo);
                    double ratings = reviewDao.getAverageRating(movieNo);
                    int reviewCnt = reviewDao.getReviewCount(movieNo);

                    movies.add(new Movie(
                            movieNo,
                            rs.getString(2),
                            rs.getString(3),
                            rs.getDate(4).toLocalDate(),
                            rs.getString(5),
                            rs.getInt(6),
                            genres,
                            ratings,
                            reviewCnt
                    ));
                }
            }
        }

        return movies;
    }

    @Override
    public Movie selectMovieBasic(int movieNo) throws Exception {
        Movie movie = null;
        String sql = """
                select *
                from movies
                where movie_no = ?
                """;
        try (Connection con = dbManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, movieNo);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int no = rs.getInt(1);
                    List<Genre> genres = selectGenresByMovieNo(no);
                    double ratings = reviewDao.getAverageRating(no);
                    int reviewCnt = reviewDao.getReviewCount(no);

                    movie = new Movie(
                            no,
                            rs.getString(2),
                            rs.getString(3),
                            rs.getDate(4).toLocalDate(),
                            rs.getString(5),
                            rs.getInt(6),
                            genres,
                            ratings,
                            reviewCnt
                    );
                }
            }
        }
        return movie;
    }

    @Override
    public Movie selectMovieDetail(int movieNo) throws Exception {
        Movie movie = null;
        String sql = """
                select *
                from movies
                where movie_no = ?
                """;
        try (Connection con = dbManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, movieNo);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int no = rs.getInt(1);
                    List<Genre> genres = selectGenresByMovieNo(no);
                    double ratings = reviewDao.getAverageRating(no);
                    int reviewCnt = reviewDao.getReviewCount(no);

                    movie = new Movie(
                            no,
                            rs.getString(2),
                            rs.getString(3),
                            rs.getDate(4).toLocalDate(),
                            rs.getString(5),
                            rs.getInt(6),
                            genres,
                            ratings,
                            reviewCnt
                    );
                }
            }
        }
        return movie;
    }

    /*
     * 영화 장르 select
     * */
    public List<Genre> selectGenresByMovieNo(int movieNo) throws SQLException {
        List<Genre> genres = new ArrayList<>();
        String sql = """
                select *
                from movies_genres
                where movie_no = ?
                """;
        try(Connection con = dbManager.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, movieNo);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Genre genre = selectGenreByGenreId(rs.getInt(3));
                    if(genre == null) throw new SQLException("장르가 존재하지 않습니다.");
                    genres.add(genre);
                }
            }
        }
        return genres;
    }

    /*
     * 장르 id을 통한 장르 단일 검색
     * */
    public Genre selectGenreByGenreId(int genreNo) throws SQLException {
        Genre genre = null;
        String sql = """
                select * from genres where genre_no = ?
                """;
        try(Connection con = dbManager.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, genreNo);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    genre = new Genre(
                            rs.getInt(1),
                            rs.getString(2)
                    );
                }
            }
        }
        return genre;
    }

    /*
     * 장르 이름을 통한 장르 단일 검색
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
                            rs.getInt(1),
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

    private int insertMoviesGenres(Connection ts, int movieNo, int genreNo) throws SQLException {
        String sql = """
                insert into movies_genres(movie_no, genre_no) 
                values (?, ?)
                """;
        try (PreparedStatement ps = ts.prepareStatement(sql)) {
            ps.setInt(1, movieNo);
            ps.setInt(2, genreNo);
            return ps.executeUpdate();
        }
    }

}
