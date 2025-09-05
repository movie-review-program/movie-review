package org.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.example.common.ReviewContext;
import org.example.model.dto.Movie;
import org.example.model.dto.Review;
import org.example.model.dto.User;
import org.example.model.service.MovieService;
import org.example.model.service.MovieServiceImpl;
import org.example.model.service.ReviewService;
import org.example.model.service.ReviewServiceImpl;
import org.example.model.service.UserService;
import org.example.model.service.UserServiceImpl;
import org.example.view.TestViewMJ;

public class ReviewController {
	private static ReviewService reviewService = ReviewServiceImpl.getInstance();
	private static MovieService movieService = MovieServiceImpl.getInstance();
	private static UserService userService = new UserServiceImpl();
	
	/**
	 * 리뷰 상세 보기 선택 시 호출
	 * 리뷰 번호를 이용해 리뷰를 조회하고, 영화와 유저의 정보를 조회한 후 View의 리뷰 상세 보기 메소드를 호출해서 보여준다.
	 * @param userNo : 세션 연결 확인을 위해 필요
	 * @param reviewNo
	 */
	public static void findReviewByReviewNo(int userNo, int reviewNo) {
		try {
			Review review = reviewService.getReviewByReviewNo(reviewNo);
			Movie movie = movieService.getMovieDetailInfo(review.getMovieNo());
			String userName = userService.getUserByUserNo(userNo).getName();
			
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
	 * 리뷰 요약 보기
	 * @param type 영화, 유저, 좋아요, 팔로우
	 * @param no : 
	 * 	영화-"어떤 영화"의 리뷰를 볼 건지 / 유저-"어떤 유저"의 리뷰를 볼 건지 / 
	 * 	좋아요-"현재 유저"가 좋아요 한 리뷰 / 팔로우-"현재 유저"가 팔로우한 유저들의 리뷰
	 * @param page
	 */
	public static void getReviewsPreview(ReviewContext type, int no, int page) {
		try {
			List<Review> reviews = reviewService.getReviewsPage(type, no, page);
			
			// 유저, 영화, 리뷰를 담는 이중 리스트
			List<List<Object>> infos = new ArrayList<List<Object>>();
			
			for (Review review : reviews) {
				Movie movie = movieService.getMovieDetailInfo(review.getMovieNo());
				User user = userService.getUserByUserNo(review.getUserNo());
	            
				System.out.println(review);
				System.out.println(movie);
				System.out.println(user);
				System.out.println();
				
	            List<Object> info = new ArrayList<Object>();
	            info.add(user);
	            info.add(movie);
	            info.add(review);
	            infos.add(info);
	        }
	        
			TestViewMJ.printReviewsPreview(type, infos);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
//	/**
//	 * 해당 영화의 모든 리뷰를 조회
//	 */
//	public static void findReviewsByMovieNo(int movieNo, int page) {
//		try {
//			List<Review> list = reviewService.getReviewsPage(ReviewContext.MOVIE, movieNo, page);
//			TestViewMJ.printReviewsPreview(ReviewContext.MOVIE, list);
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//	}
//	
//	/**
//	 * 현재 사용자(유저)가 작성한 모든 리뷰 조회
//	 * 필요 정보(4.2참고): 영화제목, 개봉년도 / 별점, 작성일, 글내용간략 / 좋아요수
//	 */
//	public static void findReviewsByUserNo(int userNo, int page) {
//		try {
//			List<Review> list = reviewService.getReviewsPage(ReviewContext.USER, userNo, page);
//			TestViewMJ.printReviewsPreview(ReviewContext.USER, list);
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//	}
//	
//	/**
//	 * 현재 사용자(유저)가 좋아요 한 모든 리뷰 조회
//	 */
//	public static void findReviewsByLike(int userNo, int page) {
//		try {
//			List<Review> list = reviewService.getReviewsPage(ReviewContext.LIKE, userNo, page);
//			TestViewMJ.printReviewsPreview(ReviewContext.LIKE, list);
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//	}
//	
//	/**
//	 * 현재 사용자(유저)가 팔로우 한 유저들의 리뷰 조회
//	 */
//	public static void findReviewsByFollow(int userNo, int page) {
//		try {
//			List<Review> list = reviewService.getReviewsPage(ReviewContext.FOLLOW, userNo, page);
//			TestViewMJ.printReviewsPreview(ReviewContext.FOLLOW, list);
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//	}
	
	/**
	 * 리뷰 등록
	 */
	public static void insertReview(Review review) {
		try {
			reviewService.insertReview(review);
			TestViewMJ.printMyReview(review);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			//FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * 현재 사용자(유저)가 작성한 리뷰 수정
	 */
	public static void updateReview(Review review) {
		try {
			reviewService.updateReview(review);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * 현재 사용자(유저)가 선택한 리뷰 삭제
	 */
	public static void deleteReview(int reviewNo) {
		try {
			reviewService.deleteReview(reviewNo);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
