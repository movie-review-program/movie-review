package org.example.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.example.common.ReviewContext;
import org.example.controller.MovieController;
import org.example.model.dao.ReviewDao;
import org.example.model.dto.Genre;
import org.example.model.dto.Movie;
import org.example.model.dto.User;

public class MoviePageView {
    private static final Scanner sc = new Scanner(System.in);
    private static int page= 1;
    private static final int size= 3;

    private static List<Movie> moviesStorage;

    /*
     * 1. 영화 선택
     * 2. 다음 페이지
     * 3. 뒤로 가기
     * */
    public static void menu(User user) {
        while (true) {
                int num = mainInfo(user);
                if (user.getEmail() == null) {
                    try {
                        switch (num) {
                            case 1:
                                ++page;
                                menu(user);
                                return;
                            case 2:
                                page = 1;
                                LoginPageView.menu();
                                return;
                            default:
                                System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
                        }
                    }catch (NumberFormatException e) {
                        System.out.println("메뉴는 숫자만 가능합니다.");
                    }
                }else {
                    try {
                        switch (num) {
                            case 1:
                                movieInfo(user);
                                page = 1;
                                return;
                            case 2:
                                ++page;
                                menu(user);
                                return;
                            case 3:
                                page = 1;
                                MainPageView.menu(user);
                                return;
                            default:
                                System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
                        }
                    }catch (NumberFormatException e) {
                        System.out.println("메뉴는 숫자만 가능합니다.");
                    }
                }
        }
    }

    static int mainInfo(User user) {
        outputMovieInfoHead();
        MovieController.getMovieBasicInfo(page, size);
        if (user.getEmail() == null) {
            outputNoneUserMovieInfoFoot();
        }else {
            outputMovieInfoFoot();
        }
        return choiceNumber();
    }

    /*
     * 1. 리뷰 작성하기
     * 2. 리뷰 보기
     * 3. 뒤로 가기
     * */
    static void movieInfo(User user) {

        //입력창 생성
        outputMovieInfoHead();
        MovieController.getMovieBasicInfo(page, size);
        outputMovieChoiceFoot();

        int c = choiceNumber();
        int index = c - 1 - ((page - 1) * size);
        Movie movie = moviesStorage.get(index);

        outputMovieDetailInfoHead();
        MovieController.getMovieDetailInfo(movie.getMovieNo());
        outputMovieDetailFoot();

        int num = choiceNumber();
        while (true) {
            try {
                switch (num) {
                    case 1:
                        ReviewPageView.createReview(user, movie);
                        movieInfo(user);
                        return;
                    case 2:
                        ReviewPageView.menu(user, ReviewContext.MOVIE, movie.getMovieNo());
                        return;
                    case 3:
                        menu(user);
                        return;
                    default:
                        System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
                }
            } catch (NumberFormatException e) {
                System.out.println("메뉴는 숫자만 가능합니다.");
            }
        }
    }


    private static void outputMovieInfoHead() {
        System.out.println("""
                ┌──────────────────────────────────────────────────┐
                │                🎭 영화 목록                        │
                ├──────────────────────────────────────────────────┤""");
    }

    private static void outputMovieDetailInfoHead() {
        System.out.println("""
                ┌──────────────────────────────────────────────────┐
                │                🎬 영화 상세 정보                    │
                ├──────────────────────────────────────────────────┤""");
    }

    public static void outputMovieBasicInfo(List<Movie> movies) {
        moviesStorage = new ArrayList<>();
        for (int i = 0; i < movies.size(); i++) {
            int num = (page - 1) * size;
            System.out.printf("│  [%d] %s (%d)%15s%n",
                    num + i + 1,
                    movies.get(i).getMovieName(),
                    movies.get(i).getOpenDate().getYear(),
                    ""
            );
            System.out.printf("│      감독: %s %15s%n",
                    movies.get(i).getDirector(),
                    ""
            );
            System.out.printf("│      ⭐ %.1f (리뷰 %d개)%15s%n",
                    movies.get(i).getRatings(),
                    movies.get(i).getReviewCnt(), "");
            moviesStorage.add(movies.get(i));
        }
    }

    public static void outputMovieInfoFoot() {
        System.out.println("""
                ├──────────────────────────────────────────────────┤
                │    1. 영화 선택                                    │
                │    2. 다음 페이지                                   │
                │    3. 뒤로 가기                                    │
                ├──────────────────────────────────────────────────┤""");
    }

    public static void outputNoneUserMovieInfoFoot() {
        System.out.println("""
                ├──────────────────────────────────────────────────┤
                │    1. 다음 페이지                                   │
                │    2. 뒤로 가기                                    │
                ├──────────────────────────────────────────────────┤""");
    }

    public static void outputMovieChoiceFoot() {
        System.out.println("""
                ├──────────────────────────────────────────────────┤
                │    1. 어떤 영화를 선택하시겠습니까? 번호를 입력해주세요      │
                ├──────────────────────────────────────────────────┤""");
    }

    public static void outputMovieDetailFoot() {
        System.out.println("""
                ├──────────────────────────────────────────────────┤
                │    1. 📝 리뷰 작성하기                              │
                │    2. 💬 리뷰 보기                                 │
                │    3. 🔙 뒤로가기                                  │
                ├──────────────────────────────────────────────────┤""");
    }


    public static void outputMovieDetailInfo(Movie movie) {
        System.out.printf("│    제목: %s %35s%n",
                movie.getMovieName(),
                ""
        );
        System.out.printf("│    감독: %s %35s%n",
                movie.getDirector(),
                ""
        );
        StringBuilder genres = new StringBuilder();
        for (Genre genre : movie.getGenreDTOS()) {
            genres.append(genre.getGenreName());
        }
        System.out.printf("│    장르: %s %35s%n",
                genres,
                ""
        );
        System.out.println("""
                ├──────────────────────────────────────────────────┤
                │    📖 줄거리                                      │""");
        String[] plots = movie.getPlot().split("[.,!\"]", 4);
        for (int i = 0; i < plots.length; i++) {
            String line = plots[i].trim();
            if (!line.isEmpty()) {
                System.out.println("│    " + line + (i == plots.length - 1 ? "..." : ""));
            }
        }
        System.out.println("├──────────────────────────────────────────────────┤");

        System.out.printf("│      ⭐ %.1f (리뷰 %d개)%15s%n",
                movie.getRatings(),
                movie.getReviewCnt(), "");
    }

    private static int choiceNumber() {
        System.out.print("│    입력 > ");
        return Integer.parseInt(sc.nextLine().trim());
    }

}
