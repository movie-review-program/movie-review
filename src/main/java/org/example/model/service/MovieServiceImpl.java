package org.example.model.service;

import java.time.LocalDate;
import java.util.List;

import org.example.model.dao.MovieDao;
import org.example.model.dao.MovieDaoImpl;
import org.example.exception.NotFoundException;
import org.example.infra.movie.KmdbResponseDTO;
import org.example.infra.movie.KobisDailyResponseDTO;
import org.example.infra.movie.KobisDetailResponseDTO;
import org.example.infra.movie.MovieClient;

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
    public void fetchMovieWithGenre(String targetDate) {
        KobisDailyResponseDTO kobisDaily = client.fetchKobisDaily(targetDate);
        String movieCd = findFirstMovieCdByKobisDaily(kobisDaily);
        String audiCnt = findFirstAudiCntByKobisDaily(kobisDaily);

        KobisDetailResponseDTO kobisDetail = client.fetchKobisDetail(movieCd);
        String movieName = findMovieNameByKobisDetail(kobisDetail);
        LocalDate openDate = findOpenDateByKobisDetail(kobisDetail);
        List<String> genres = findGenresByKobisDetail(kobisDetail);
        String director = findDirectorByKobisDetail(kobisDetail);

        KmdbResponseDTO kmdb = client.fetchKmdb(movieName, director);
        String plot = findPlot(kmdb);

        int result = dao.insertMovie(movieName, director, audiCnt, openDate, genres, plot);
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

    private List<String> findGenresByKobisDetail(KobisDetailResponseDTO dto) {
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
