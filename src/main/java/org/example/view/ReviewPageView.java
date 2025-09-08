package org.example.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.example.common.ReviewContext;
import org.example.controller.MovieController;
import org.example.controller.ReviewController;
import org.example.controller.UserController;
import org.example.model.dto.Genre;
import org.example.model.dto.Movie;
import org.example.model.dto.Review;
import org.example.model.dto.User;

/*
 * 1. 번호 입력시 상세보기
 * 2. 다음 페이지
 * 3. 뒤로
 * TODO: refactor
 * */
public class ReviewPageView {
    private static final Scanner sc = new Scanner(System.in);
    private static int page = 1;

    private static List<List<Object>> infoStorage;

    public static void menu(User user, ReviewContext type, int no) {
        while (true) {
            try {
                ReviewController.getReviewsPreview(type, no, page);
                outputPrintReviewsPreviewFoot();
                int num = choiceNumber();
                switch (num) {
                    case 1:
                        ReviewController.getReviewsPreview(type, no, page);
                        outputPrintReviewsPreviewChoiceFoot();
                        int c = choiceNumber();
                        int index = c - 1 - ((page - 1) * 3);
                        List<Object> objects = infoStorage.get(index);
                        User objectuser = (User)objects.get(0);
                        Movie movie = (Movie)objects.get(1);
                        Review objectreview = (Review)objects.get(2);
                        ReviewController.findReviewByReviewNo(objectuser.getUserNo(), objectreview.getReviewNo());
                        if (user.getUserNo() == objectuser.getUserNo()) {
                            outputUsersReviewFoot();
                            int cn = choiceNumber();
                            if (cn == 1) {
                                ReviewController.findReviewByReviewNo(objectuser.getUserNo(), objectreview.getReviewNo());
                                int rating = setRating();
                                String review = setReview();
                                ReviewController.updateReview(new Review(
                                        rating,
                                        review,
                                        user.getUserNo(),
                                        movie.getMovieNo()
                                ));
                                menu(user, type, no);
                            } else if(cn == 2) {
                                ReviewController.deleteReview(objectreview.getReviewNo());
                                menu(user, type, no);
                            } else {
                                menu(user, type, no);
                            }
                        } else {
                            //TODO: 팔로우 언팔로우 나누기, 좋아요 하기/취소 나누기
                            //할꺼면 controller(2개)에 먼저 보내서 확인하고 그에 맞는 view로 올려야 함
                            //view는 4개
                        	ReviewController.isLiking(user.getUserNo(), objectreview.getReviewNo());
                        	UserController.isFollowing(user.getUserNo(), objectuser.getUserNo());
                            outputReviewFoot();
                            int cn = choiceNumber();
                            if(cn == 1){
                                ReviewController.createLike(user.getUserNo(), objectreview.getReviewNo());
                            } else if (cn == 2) {
                                UserController.followUser(user.getUserNo(), objectuser.getUserNo());
                            } else {
                                menu(user, type, no);
                            }
                        }
                        page = 1;
                        return;
                    case 2:
                        ++page;
                        menu(user, type, no);
                        return;
                    case 3:
                        page = 1;
                        if(type == ReviewContext.MOVIE){
                            MoviePageView.menu(user);
                        } else if (type == ReviewContext.LIKE) {
                            MyPageView.menu(user);
                        } else if (type == ReviewContext.FOLLOW) {
                            MainPageView.menu(user);
                        } else if (type == ReviewContext.USER) {
                            MyPageView.menu(user);
                        }
                        MainPageView.menu(user);
                        return;
                    default:
                        System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
                }
            } catch (NumberFormatException e) {
                System.out.println("메뉴는 숫자만 가능합니다.");
            }
        }
    }

    public static void createReview(User user, Movie movie) {
        outputCreateReviewHead();
        outputCreateReviewInfo(movie);
        int rating = setRating();
        String review = setReview();
        ReviewController.insertReview(new Review(
                rating,
                review,
                user.getUserNo(),
                movie.getMovieNo()
        ));
    }

    public static void searchReview(User user) {
        outputSearchReviewHead();
        String movieName = choiceString();
        MovieController.createReviewByMovieName(user, movieName);
    }

    public static void printReviewsPreview(ReviewContext type, List<List<Object>> infos) {
        infoStorage = new ArrayList<>();
        System.out.println("┌─────────────────────────────────────────────────┐");
        printReviewsPreviewTitle(type);
        System.out.println("├─────────────────────────────────────────────────┤");

        for (List<Object> info : infos) {
            infoStorage.add(info);
            User user = (User)info.get(0);
            Movie movie = (Movie)info.get(1);
            Review review = (Review)info.get(2);

            if (type != ReviewContext.USER)
                System.out.printf("│  📍 %s님의 리뷰 %24s│%n", user.getName(), "");
            if (type != ReviewContext.MOVIE)
                System.out.printf("│  🎬 %s %s (%d.0)%20s│%n", movie.getMovieName(), "★★★★★", review.getRating(), "");
            System.out.printf("│  👍 %d  💭 \"%s\"%25s│%n", review.getLikeCnt(), review.getContentPreviw(), "");
            System.out.println("│  ────────────────────────────────────────────   │");
        }

    }

    private static void outputPrintReviewsPreviewFoot() {
        System.out.println("├─────────────────────────────────────────────────┤");
        System.out.println("│  1.번호 입력시 상세보기 | 2: 다음페이지   | 3: 뒤로    	  │");
        System.out.println("└─────────────────────────────────────────────────┘");
    }

    private static void outputPrintReviewsPreviewChoiceFoot() {
        System.out.println("""
                ├──────────────────────────────────────────────────┤
                │    1. 어떤 리뷰를 선택하시겠습니까? 번호를 입력해주세요   	   │
                ├──────────────────────────────────────────────────┤""");
    }

    private static void printReviewsPreviewTitle(ReviewContext type) {
        switch (type) {
            case MOVIE -> System.out.println("│                🎬 영화 리뷰 보기                	  │");
            case USER -> System.out.println("│                📚 내 리뷰 목록                	  │");
            case LIKE -> System.out.println("│                 👥 좋아요 한 리뷰                	  │");
            case FOLLOW -> System.out.println("│                👥 팔로워 리뷰 피드                	  │");
        }
    }

    public static void printReview(String username, Review review, Movie movie) {
        System.out.println("┌─────────────────────────────────────────────────┐");
        System.out.println("│                💬 리뷰 상세보기                	  │");
        System.out.println("├─────────────────────────────────────────────────┤");
        System.out.printf("│  👤 작성자: %s %33s│%n", username, "");
        System.out.printf("│  📅 작성일: %s%19s│%n", review.getRegDate(), "");
        System.out.println("│                                             	  │");
        System.out.printf("│  🎬 영화: %s (%s)%23s│%n", movie.getMovieName(), movie.getOpenDate().getYear(), "");
        System.out.printf("│  ⭐ 별점: ★★★★★ (%d.0/5.0)%24s│%n", review.getRating(), "");
        System.out.printf("│  👍 좋아요: %d%34s│%n", review.getLikeCnt(), "");
        System.out.println("│                                            	  │");
        System.out.println("│  📝 리뷰 내용:                                	  │");
        System.out.println("│  ┌─────────────────────────────────────────┐    │");
        int limit = 30;
        for (int i = 0; i < review.getContent().length(); i += limit) {
            System.out.printf("│  │ %-32s │    │%n",
                    review.getContent().substring(i, Math.min(i + limit, review.getContent().length())));
        }
        System.out.println("│  └─────────────────────────────────────────┘    │");
        System.out.println("├─────────────────────────────────────────────────┤");
    }

    private static void outputSearchReviewHead() {
        System.out.println("""
                ┌─────────────────────────────────────────────────┐
                │              ✍️ 리뷰 작성하기                    	  │
                ├─────────────────────────────────────────────────┤
                │  🔍 영화 검색하기:                               	  │
                │                                                 │
                ├─────────────────────────────────────────────────┤
                │  보고 싶은 영화를 입력하세요.                        	  │
                """);
    }

    private static int choiceNumber() {
        System.out.print("│    입력 > ");
        return Integer.parseInt(sc.nextLine().trim());
    }

    static void outputCreateReviewHead() {
        System.out.println("""
                ┌──────────────────────────────────────────────────┐
                │                   📝 리뷰 작성                 	   │
                ├──────────────────────────────────────────────────┤""");
    }

    static void outputUsersReviewFoot() {
        System.out.println("""
                │    1. 리뷰 수정                                	   │
                │    2. 리뷰 삭제                                 	   │
                │    3. 뒤로 가기                                 	   │
                ├──────────────────────────────────────────────────┤""");
    }

    static void outputReviewFoot() {
        System.out.println("""
                │    3. 뒤로 가기                                	   │
                ├──────────────────────────────────────────────────┤""");
    }
    
    public static void outputReviewLikeFoot() {
        System.out.println("""
                │    1. 좋아요 하기                                	  │""");
    }
    
    public static void outputReviewUnlikeFoot() {
        System.out.println("""
                │    1. 좋아요 취소                                	  │""");
    }
    
    public static void outputReviewFollowFoot() {
        System.out.println("""
                │    2. 팔로우 하기                               	  │""");
    }
    
    public static void outputReviewUnfollowFoot() {
        System.out.println("""
                │    2. 언팔로우 하기                              	  │""");
    }

    static void outputCreateReviewInfo(Movie movie) {
        StringBuilder genres = new StringBuilder();
        for (Genre g : movie.getGenreDTOS()) {
            genres.append(g.getGenreName());
        }
        String gen = genres.toString();

        System.out.printf("│  🎬 영화: %s (%s)%n",
                movie.getMovieName(),
                movie.getOpenDate().getYear());
        System.out.printf("│  📅 감독: %s | 장르: %s%n",
                movie.getDirector(),
                gen);
        System.out.println("├──────────────────────────────────────────────────┤");
        System.out.println("""
                │                                                  │
                │  ⭐ 별점 (1-5점):                               	   │
                │                                                  │
                │  💭 리뷰를 작성해주세요:                            	   │
                │  ┌─────────────────────────────────────────┐     │
                │  │                                         │     │
                │  │                                         │     │
                │  └─────────────────────────────────────────┘     │
                │                                                  │
                ├──────────────────────────────────────────────────┤""");

    }

    //TODO: 1 ~ 5점 정수
    private static int setRating() {
        System.out.print("│    별점 매기기 (정수) > ");
        return Integer.parseInt(sc.nextLine().trim());
    }

    //TODO: 리뷰를 String이 아닌 text 형태를 위한 작업 필요
    private static String setReview() {
        System.out.print("│    리뷰를 작성해주세요. > ");
        return sc.nextLine().trim();
    }

    private static String choiceString() {
        System.out.print("│    입력 > ");
        return sc.nextLine().trim();
    }
}
