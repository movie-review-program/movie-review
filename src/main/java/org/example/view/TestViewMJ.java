package org.example.view;

import java.util.List;
import java.util.Scanner;

import org.example.common.ReviewContext;
import org.example.model.dto.Movie;
import org.example.model.dto.Review;
import org.example.model.dto.User;
import org.example.controller.ReviewController;
import org.example.model.dao.ReviewDao;
import org.example.model.dao.ReviewDaoImpl;

public class TestViewMJ {
	ReviewDao rd = ReviewDaoImpl.getInstance();
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		new TestViewMJ().testDao();
		
		tempMenu();
	}
	
	////////////////////// 임시 Menu View //////////////////////////////
	public static void tempMenu() {
		// 리뷰 상세 보기 (영화정보, 유저정보 둘 다 필요)
		ReviewController.findReviewByReviewNo(1, 1);
		
		// 내 리뷰 보기
		ReviewController.getReviewsPreview(ReviewContext.USER, 1, 1);
		
		// 영화 리뷰 보기
		ReviewController.getReviewsPreview(ReviewContext.MOVIE, 2, 1);
		
		// 팔로우 리뷰 보기
		ReviewController.getReviewsPreview(ReviewContext.FOLLOW, 1, 1);
		
		// 좋아요 리뷰 보기
		ReviewController.getReviewsPreview(ReviewContext.LIKE, 1, 1);
		
		// 리뷰 등록
		//inputReview(3,2);
	}
	
	
	public static void printReviewsPreview(ReviewContext type, List<List<Object>> infos) {
		System.out.println("┌─────────────────────────────────────────────────┐");
		printReviewsPreviewTitle(type);
        System.out.println("├─────────────────────────────────────────────────┤");

        for (List<Object> info : infos) {
        	User user = (User) info.get(0);
        	Movie movie = (Movie) info.get(1);
        	Review review = (Review) info.get(2);
        	
        	if (type != ReviewContext.USER)
        		System.out.printf("│  📍 %s님의 리뷰 %24s│%n", user.getName(), "");
        	if (type != ReviewContext.MOVIE)
        		System.out.printf("│  🎬 %s %s (%d.0)%20s│%n", movie.getMovieName(), "★★★★★", review.getRating(), "");
            System.out.printf("│  👍 %d  💭 \"%s\"%25s│%n", review.getLikeCnt(), review.getContentPreviw(), "");
            System.out.println("│  ────────────────────────────────────────────   │");
        }

        System.out.println("├─────────────────────────────────────────────────┤");
        System.out.println("│  1.번호 입력시 상세보기 | R: 다음으로  | 0: 뒤로   │");
        System.out.println("│  선택하세요: _                                  │");
        System.out.println("└─────────────────────────────────────────────────┘");
 
	}
	
	public static void printReviewsPreviewTitle(ReviewContext type) {
		switch(type) {
			case MOVIE -> System.out.println("│                🎬 영화 리뷰 보기                	  │");
			case USER -> System.out.println("│                📚 내 리뷰 목록                	  │");
			case LIKE -> System.out.println("│                 👥 좋아요 한 리뷰                	  │");
			case FOLLOW -> System.out.println("│                👥 팔로워 리뷰 피드                	  │");
		}
	}
	
	public static void inputReview(int movieNo, int userNo) {
		System.out.print("영화에 대한 별점을 남겨주세요. (0~5점): ");
		int rating = Integer.parseInt(sc.nextLine());
		System.out.println("리뷰를 작성해주세요. (Enter 입력 시 작성 끝)");
		String content = sc.nextLine();
		System.out.print("리뷰를 남기시겠습니까? (yes/no): ");
		String check = sc.nextLine();
		
		if ("yes".equals(check))
			ReviewController.insertReview(new Review(rating, content, movieNo, userNo));
	}
	
	public static void printMyReview(Review review) {
        System.out.println("┌─────────────────────────────────────────────────┐");
        System.out.println("│                📝 리뷰 작성                     │");
        System.out.println("├─────────────────────────────────────────────────┤");
        System.out.printf("│  🎬 영화: %s (%s)%s%n", "title", "openDate", spaces(48 - ("title".length() + String.valueOf("year").length())));
        System.out.printf("│  📅 감독: %s | 장르: %s%10s%n", "director", "genre", "");
        System.out.println("├─────────────────────────────────────────────────┤");
        System.out.println("│                                                 │");
        System.out.printf("│  ⭐ 별점 (1-5점): [%s] %d점%s%n", "★★★★★", review.getRating(), spaces(28 - String.valueOf(review.getRating()).length()));
        System.out.println("│                                                 │");
        System.out.println("│  💭 한줄평을 작성해주세요:                      │");
        System.out.println("│  ┌─────────────────────────────────────────┐   │");

        int limit = 30;
        for (int i = 0; i < review.getContent().length(); i+=limit) {
            System.out.printf("│  │ %-32s │    │%n", review.getContent().substring(i, Math.min(i + limit, review.getContent().length())));
        }

        System.out.println("│  │ _                                       │   │");
        System.out.println("│  └─────────────────────────────────────────┘   │");
        System.out.println("│                                                 │");
        System.out.println("├─────────────────────────────────────────────────┤");
        System.out.println("│  [ENTER] 리뷰 등록 | [ESC] 취소                │");
        System.out.println("└─────────────────────────────────────────────────┘");
	}
	
    // 공백 채우기용 유틸 메서드
    private static String spaces(int count) {
        if (count <= 0) return "";
        return " ".repeat(count);
    }
	
	public static void printReview(String username, Review review, Movie movie) {
		System.out.println("┌─────────────────────────────────────────────────┐");
        System.out.println("│                💬 리뷰 상세보기                	  │");
        System.out.println("├─────────────────────────────────────────────────┤");
        System.out.printf("│  👤 작성자: %s %33s│%n", username, "");
        System.out.printf("│  📅 작성일: %s%19s│%n", review.getRegDate(), "");
        System.out.println("│                                             	  │");
        System.out.printf("│  🎬 영화: %s (%s)%23s│%n", movie.getMovieName(), movie.getOpenDate(), "");
        System.out.printf("│  ⭐ 별점: ★★★★★ (%d.0/5.0)%24s│%n", review.getRating(), "");
        System.out.printf("│  👍 좋아요: %d%34s│%n", review.getLikeCnt(), "");
        System.out.println("│                                            	  │");
        System.out.println("│  📝 리뷰 내용:                                	  │");
        System.out.println("│  ┌─────────────────────────────────────────┐    │");
        int limit = 30;
        for (int i = 0; i < review.getContent().length(); i+=limit) {
            System.out.printf("│  │ %-32s │    │%n", review.getContent().substring(i, Math.min(i + limit, review.getContent().length())));
        }
        System.out.println("│  └─────────────────────────────────────────┘    │");
        System.out.println("├─────────────────────────────────────────────────┤");
        System.out.println("│  1. 리뷰 수정 | 2. 리뷰 삭제 | 0                  	  │");
        System.out.println("└─────────────────────────────────────────────────┘");
		
	}
	
	public void testDao() {
		try {
			System.out.println("----- 사용자 리뷰 목록 -----");
			List<Review> list = rd.selectReviewsPage(ReviewContext.USER, 1, 1, 3);
			for (Review r : list) {
				System.out.println(r);
			}
			
			System.out.println("----- 좋아요 리뷰 목록 -----");
			list = rd.selectTwiceReviewsPage(ReviewContext.LIKE, 1, 1, 3);
			for (Review r : list) {
				System.out.println(r);
			}
			
			System.out.println("----- 영화의 리뷰 개수 -----");
			System.out.println(rd.getReviewCount(1));
			
			System.out.println("----- 영화의 평점 -----");
			System.out.println(rd.getAverageRating(1));
			
			System.out.println("----- 영화의 리뷰 조회 -----");
			System.out.println(rd.selectReviewsPage(ReviewContext.MOVIE, 1, 1, 3));
			
			System.out.println("----- 유저의 리뷰 조회 -----");
			System.out.println(rd.selectReviewsPage(ReviewContext.USER, 1, 1, 3));
			
			System.out.println("----- 좋아요 개수 -----");
			System.out.println(rd.getLikeCount(1));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
