package org.example.service;

import org.example.infra.movie.KmdbResponseDTO;
import org.example.infra.movie.KobisDailyResponseDTO;
import org.example.infra.movie.KobisDetailResponseDTO;
import org.example.infra.movie.MovieClient;

public class MovieServiceImpl implements MovieService {
    private static MovieService instance = new MovieServiceImpl();

    private MovieServiceImpl() {
    }
    public static MovieService getInstance() {
        return instance;
    }

    @Override
    public void fetchMovieWithGenre(String targetDate) {
        MovieClient client = new MovieClient();
        KobisDailyResponseDTO kobisDailyResponseDTO = client.fetchKobisDaily(targetDate);

        String audiCnt = kobisDailyResponseDTO.getBoxOfficeResult().getDailyBoxOfficeList().get(0).getAudiCnt();
        System.out.println(audiCnt);

        String movieCd = kobisDailyResponseDTO.getBoxOfficeResult().getDailyBoxOfficeList().get(0).getMovieCd();
        System.out.println(movieCd);

        KobisDetailResponseDTO kobisDetailResponseDTO = client.fetchKobisDetail(movieCd);

        String title = kobisDetailResponseDTO.getMovieInfoResult().getMovieInfo().getMovieName();
        System.out.println(title);

        String director = kobisDetailResponseDTO.getMovieInfoResult().getMovieInfo().getDirectors().get(0).getPeopleName();
        System.out.println(director);

        KmdbResponseDTO kmdbResponseDTO = client.fetchKmdb(title, director);
        System.out.println(kmdbResponseDTO.getData().get(0).getResult().get(0).getPlots().getPlot().get(0).getPlotText());

    }
}
