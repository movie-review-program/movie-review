package org.example.controller;

import java.time.LocalDate;
import java.util.List;

import org.example.model.dto.Genre;
import org.example.model.dto.Movie;
import org.example.model.dto.Review;
import org.example.model.service.ReviewService;
import org.example.model.service.ReviewServiceImpl;
import org.example.view.TestViewMJ;

public class ReviewController {
	private static ReviewService reviewService = ReviewServiceImpl.getInstance();
	
	/**
	 * 리뷰 상세 보기 선택 시 호출
	 * 리뷰 번호를 이용해 리뷰를 조회하고, 영화와 유저의 정보를 조회한 후 View의 리뷰 상세 보기 메소드를 호출해서 보여준다.
	 * @param userNo : 세션 연결 확인을 위해 필요
	 * @param reviewNo
	 */
	public static void findReviewByReviewNo(int userNo, int reviewNo) {
		try {
			Review review = reviewService.getReviewByReviewNo(reviewNo);
			Movie movie = new Movie("테스트영화제목", "테스트감독", null, null, 100, null);
			String userName = "유저1";
			
			TestViewMJ.printReview(userName, review, movie);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			//FailView.errorMessage(e.getMessage());
		}
		
		/*// UserService 와 MovieService가 만들어지면
		try {
			Review review = reviewService.findReviewByReviewNo(reviewNo);
			Movie movie = movieService.findMovieByMovieNo(review.getMovieNo());
			String userName = userService.findUserByUserNo(userNo);
			
			TestViewMJ.printReviewFromUser(userName, review, movie);
		}
		 */
	}
	
	/**
	 * 해당 영화의 모든 리뷰를 조회
	 */
	public static void findReviewsByMovieNo(int movieNo) {
		
	}
	
	/**
	 * 현재 사용자(유저)가 작성한 모든 리뷰 조회
	 */
	public static void findReviewsByUserNo(int userNo) {
		
	}
	
	/**
	 * 현재 사용자(유저)가 좋아요 한 모든 리뷰 조회
	 */
	public static void findReviewsByLike(int likeNo) {
		
	}
	
	/**
	 * 리뷰 등록
	 */
	public static void insertReview(Review review) {
		
	}
	
	/**
	 * 현재 사용자(유저)가 작성한 리뷰 수정
	 */
	public static void updateReview(Review review) {
		
	}
	
	/**
	 * 현재 사용자(유저)가 선택한 리뷰 삭제
	 */
	public static void deleteReview(int reviewNo) {
		
	}
}
