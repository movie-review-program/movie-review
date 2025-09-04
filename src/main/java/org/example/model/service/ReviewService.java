package org.example.model.service;

import java.util.List;

import org.example.exception.NotFoundException;
import org.example.model.dto.Review;

public interface ReviewService {
	/**
	 * 리뷰 번호를 이용해 리뷰 조회
	 */
	Review findReviewByReviewNo(int reviewNo) throws Exception;
	
	/**
	 * 해당 영화의 모든 리뷰를 조회
	 */
	List<Review> findReviewsByMovieNo(int movieNo) throws Exception;
	
	/**
	 * 현재 사용자(유저)가 작성한 모든 리뷰 조회
	 */
	List<Review> findReviewsByUserNo(int userNo) throws Exception;
	
	/**
	 * 현재 사용자(유저)가 좋아요 한 모든 리뷰 조회
	 */
	List<Review> findReviewsByLike(int userNo) throws Exception;
	
	/**
	 * 리뷰 등록
	 */
	void insertReview(Review review) throws Exception;
	
	/**
	 * 현재 사용자(유저)가 작성한 리뷰 수정
	 */
	void updateReview(Review review) throws Exception;
	
	/**
	 * 현재 사용자(유저)가 선택한 리뷰 삭제
	 */
	void deleteReview(int reviewNo) throws Exception;
}
