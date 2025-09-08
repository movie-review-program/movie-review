package org.example.model.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.example.model.dao.MovieDao;
import org.example.model.dao.MovieDaoImpl;
import org.example.infra.movie.KmdbResponseDTO;
import org.example.infra.movie.KobisDailyResponseDTO;
import org.example.infra.movie.KobisDetailResponseDTO;
import org.example.infra.movie.MovieClient;
import org.example.model.dto.Genre;
import org.example.model.dto.Movie;

public class MovieServiceImpl implements MovieService {
    private static final MovieService instance = new MovieServiceImpl();
    private static final MovieClient client = new MovieClient();
    private static final MovieDao dao = new MovieDaoImpl();

    private MovieServiceImpl() {
    }
    public static MovieService getInstance() {
        return instance;
    }

    @Override
    public void fetchMovieWithGenre(String targetDate) throws SQLException {
        KobisDailyResponseDTO kobisDaily = client.fetchKobisDaily(targetDate);
        String movieCd = findFirstMovieCdByKobisDaily(kobisDaily);
        String audiCnt = findFirstAudiCntByKobisDaily(kobisDaily);

        KobisDetailResponseDTO kobisDetail = client.fetchKobisDetail(movieCd);
        String movieName = findMovieNameByKobisDetail(kobisDetail);
        LocalDate openDate = findOpenDateByKobisDetail(kobisDetail);
        List<Genre> genres = findGenresByKobisDetail(kobisDetail);
        String director = findDirectorByKobisDetail(kobisDetail);

        KmdbResponseDTO kmdb = client.fetchKmdb(movieName, director);
        String plot = findPlot(kmdb);

        int result = dao.insertMovie(
                new Movie(
                        movieName,
                        director,
                        openDate,
                        plot,
                        Integer.parseInt(audiCnt),
                        genres));

        if (result == 0) throw new SQLException("영화 등록에 실패하였습니다");
    }

    @Override
    public Movie getMovieByMovieName(String movieName) throws Exception {
        Movie movie = dao.selectMovieName(movieName.trim());
        if(movie == null)
            throw new SQLException("맞는 이름을 가진 영화가 존재하지 않습니다.");
        return dao.selectMovieName(movieName.trim());
    }

    @Override
    public List<Movie> getMovieBasicInfo(int page, int size) throws Exception {
        List<Movie> movies = dao.selectMovieBasicPage(page, size);
        if (movies.size() != size)
            throw new SQLException("페이징된 정보 5개를 가져오지 못했습니다.");
        return movies;
    }

    @Override
    public Movie getMovieDetailInfo(int movieNo) throws Exception {
        Movie movie = dao.selectMovieDetail(movieNo);
        if (movie == null) {
            throw new SQLException("NO에 맞는 영화가 존재하지 않습니다");
        }
        return movie;
    }

    private String findFirstAudiCntByKobisDaily(KobisDailyResponseDTO dto) {
        return dto
                .getBoxOfficeResult()
                .getDailyBoxOfficeList()
                .get(0)
                .getAudiCnt();
    }

    private String findFirstMovieCdByKobisDaily(KobisDailyResponseDTO dto) {
        return dto
                .getBoxOfficeResult()
                .getDailyBoxOfficeList()
                .get(0)
                .getMovieCd();
    }

    private String findMovieNameByKobisDetail(KobisDetailResponseDTO dto) {
        return dto
                .getMovieInfoResult()
                .getMovieInfo()
                .getMovieName();
    }

    private LocalDate findOpenDateByKobisDetail(KobisDetailResponseDTO dto) {
        return dto
                .getMovieInfoResult()
                .getMovieInfo()
                .getOpenDate();
    }

    private List<Genre> findGenresByKobisDetail(KobisDetailResponseDTO dto) {
        return dto
                .getMovieInfoResult()
                .getMovieInfo()
                .getGenres();
    }

    private String findDirectorByKobisDetail(KobisDetailResponseDTO dto) {
        return dto
                .getMovieInfoResult()
                .getMovieInfo()
                .getDirectors()
                .get(0)
                .getPeopleName();
    }

    private String findPlot(KmdbResponseDTO kmdb) {
        return kmdb
                .getData()
                .get(0)
                .getResult()
                .get(0)
                .getPlots()
                .getPlot()
                .get(0)
                .getPlotText();
    }
}
