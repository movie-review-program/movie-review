package org.example.view;

import java.util.List;
import java.util.Scanner;

import org.example.model.dto.Movie;
import org.example.controller.ReviewController;
import org.example.model.dao.ReviewDao;
import org.example.model.dao.ReviewDaoImpl;
import org.example.model.dto.Review;

public class TestViewMJ {
	ReviewDao rd = ReviewDaoImpl.getInstance();
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		tempMenu();
	}
	
	////////////////////// 임시 Menu View //////////////////////////////
	public static void tempMenu() {
		// 영화 상세 정보 화면 -> 리뷰 보기 클릭 시
		
		// 리뷰 상세 보기 (영화정보, 유저정보 둘 다 필요)
		ReviewController.findReviewByReviewNo(1, 17);
		
		/*
		// 이러면 controller 의 메소드들이 리턴값이 있어야 함 -> X
		Review review = ReviewController.findReviewByReviewNo(reviewNo);
		Movie movie = movieService.findMovieByMovieNo(review.getMovieNo());
		User user = userService.findUserByUserNo(userNo);
		printReviewFromUser(user.getUserName(), review, movie);
		 */
	}
	
	public static void printReviewsPreview(List<Review> reviewList) {
		
	}
	
	public static void printReview(String username, Review review, Movie movie) {
		System.out.println("┌─────────────────────────────────────────────────┐");
        System.out.println("│                💬 리뷰 상세보기                	  │");
        System.out.println("├─────────────────────────────────────────────────┤");
        System.out.printf("│  👤 작성자: %s %33s│%n", username, "");
        System.out.printf("│  📅 작성일: %s%19s│%n", review.getRegDate(), "");
        System.out.println("│                                             	  │");
        System.out.printf("│  🎬 영화: %s (%d)%23s│%n", movie.getMovieName(), movie.getOpenDate(), "");
        System.out.printf("│  ⭐ 별점: ★★★★★ (%d.0/5.0)%24s│%n", review.getRating(), "");
        System.out.printf("│  👍 좋아요: %d%34s│%n", 2025, "");
        System.out.println("│                                            	  │");
        System.out.println("│  📝 리뷰 내용:                                 	  │");
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
	
	public static void uploadReview(long userNo) {
		System.out.println("----- 리뷰 작성 -----");
		System.out.print("리뷰를 작성할 영화를 입력하세요: ");
		String title = sc.nextLine();
//		MovieController.findMovieByTitle(userNo, title);
	}
	
	///////////////// End View ////////////////////
	public static void printMovieInfo(long userNo, Movie movie) {
		System.out.print("별점: ");
		int rating = Integer.parseInt(sc.nextLine());
		System.out.println("리뷰: ");
		String content = sc.nextLine();
		
//		Review review = new Review(rating, content, movie.getMovieNo(), userNo);
	}
	
	
	/////////////////// ReviewController 테스트 ///////////////////
	public void testController() {
		
	}
	
	
	////////////// MovieController /////////////////
	public static void findMovieByName(long userNo, String title) {
	try {
//	Movie movie = MovieService.findMovieByTitle(title);	// throw 사용자정의예외
//	EndView.printMovieInfo(userNo, movie);
	//EndView.printMovieInfo(movie);
	} catch (Exception e) {
	// 해당 영화가 없습니다.
//	FailView.errorMessage(e.getMessage());	
	}
	}
	

	
	public void testDao() {
		try {
			System.out.println("----- 사용자 리뷰 목록 -----");
			List<Review> list = rd.findReviewsByUserNo(1);
			for (Review r : list) {
				System.out.println(r);
			}
			
			System.out.println("----- 좋아요 리뷰 목록 -----");
			list = rd.findReviewsByLike(1);
			for (Review r : list) {
				System.out.println(r);
			}
			
			System.out.println("----- 영화의 리뷰 개수 -----");
			System.out.println(rd.getReviewCount(1));
			
			System.out.println("----- 영화의 평점 -----");
			System.out.println(rd.getAverageRating(1));
			
			System.out.println("----- 영화의 리뷰 조회 -----");
			System.out.println(rd.findReviewsByMovieNo(1));
			
			System.out.println("----- 유저의 리뷰 조회 -----");
			System.out.println(rd.findReviewsByUserNo(1));
			
			System.out.println("----- 리뷰 등록 -----");
			Review review = new Review(5, "좋아요", 3, 2);
			rd.insertReview(review);
			System.out.println(rd.findReviewsByUserNo(3));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
