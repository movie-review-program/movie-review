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
import org.example.view.ReviewPageView;

public class ReviewController {
	private static ReviewService reviewService = ReviewServiceImpl.getInstance();
	private static MovieService movieService = MovieServiceImpl.getInstance();
	private static UserService userService = UserServiceImpl.getInstance();
	
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
			
			ReviewPageView.printReview(userName, review, movie);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
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
			List<List<Object>> infos = new ArrayList<>();
			
			for (Review review : reviews) {
				Movie movie = movieService.getMovieDetailInfo(review.getMovieNo());
				User user = userService.getUserByUserNo(review.getUserNo());
	            
	            List<Object> info = new ArrayList<>();
	            info.add(user);
	            info.add(movie);
	            info.add(review);
	            infos.add(info);
	        }
	        
			ReviewPageView.printReviewsPreview(type, infos);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * 리뷰 등록
	 */
	public static void insertReview(Review review) {
		try {
			reviewService.insertReview(review);
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

	public static void createLike(int userNo, int reviewNo) {
		try {
			reviewService.insertLike(userNo, reviewNo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void deleteLike(int userNo, int reviewNo) {
		try {
			reviewService.deleteLike(userNo, reviewNo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
