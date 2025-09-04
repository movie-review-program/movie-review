package org.example.infra.movie;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Properties;

import org.example.util.DBManagerImpl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class MovieClient {
    /*
     * Java 객체에는 없는 속성을 무시
     * LocalDate 설정
     * */
    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private final HttpClient http = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10)).build();

    private static final String kmdbKey;
    private static final String kobisKey;

    private static final String KMDB_URL = "https://api.koreafilm.or.kr/openapi-data2/wisenut/search_api/search_json2.jsp?" +
            "collection=kmdb_new2&detail=N&nation=" + enc("대한민국");
    private static final String KOBIS_DAILY_URL = "https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?" +
            "itemPerPage=10&multiMovieYn=N&repNationCd=K";
    private static final String KOBIS_DETAIL_URL = "https://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json?";

    static {
        Properties p = new Properties();
        try (InputStream in =
                     DBManagerImpl.class.getResourceAsStream("/application.properties")) {
            p.load(in);

        } catch (Exception e) {
            throw new ExceptionInInitializerError("프로퍼티 값을 불러오는데 실패하였습니다.: " + e.getMessage());
        }

        kmdbKey    = p.getProperty("km_serviceKey");
        kobisKey   = p.getProperty("ko_serviceKey");


    }

    // responseDTO.getData().get(0).getResult().get(0).getPlots().getPlot().get(0).getPlotText();
    public KmdbResponseDTO fetchKmdb(String title, String director) {
        String uri = KMDB_URL +
                "&title=" + enc(title) +
                "&director=" + enc(director) +
                "&ServiceKey=" + kmdbKey;

        HttpRequest res = HttpRequest.newBuilder(toUri(uri))
                .header("Accept", "application/json")
                .timeout(Duration.ofSeconds(10))
                .GET()
                .build();

        try {
            HttpResponse<String> response = http.send(res, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
            return objectMapper.readValue(response.body(), KmdbResponseDTO.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * targetDate: 조회하고자 하는 날짜를 yyyymmdd 형식으로 입력
     * */
    public KobisDailyResponseDTO fetchKobisDaily(String targetDate) {
        String uri = KOBIS_DAILY_URL +
                "&targetDt=" + targetDate +
                "&key=" + kobisKey;

        HttpRequest res = HttpRequest.newBuilder(toUri(uri))
                .header("Accept", "application/json")
                .timeout(Duration.ofSeconds(10))
                .GET()
                .build();

        try {
            HttpResponse<String> response = http.send(res, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
            return objectMapper.readValue(response.body(), KobisDailyResponseDTO.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * movieCd: 영화코드를 지정
     * */
    public KobisDetailResponseDTO fetchKobisDetail(String movieCd) {

        String uri = KOBIS_DETAIL_URL +
                "&movieCd=" + movieCd +
                "&key=" + kobisKey;

        HttpRequest res = HttpRequest.newBuilder(toUri(uri))
                .header("Accept", "application/json")
                .timeout(Duration.ofSeconds(10))
                .GET()
                .build();

        try {
            HttpResponse<String> response = http.send(res, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
            return objectMapper.readValue(response.body(), KobisDetailResponseDTO.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private URI toUri(String url) {
        return URI.create(url);
    }

    private static String enc(String s) {
        return URLEncoder.encode(s, StandardCharsets.UTF_8); // 공백은 + 로, 한글/콜론 등 안전하게
    }
}
