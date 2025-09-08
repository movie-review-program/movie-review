package org.example.model.service;

import java.util.List;

import org.example.common.ReviewContext;
import org.example.model.dto.Review;

public interface ReviewService {
	/**
	 * 리뷰 번호를 이용해 리뷰 조회
	 * (리뷰 상세 보기에서 사용)
	 */
	Review getReviewByReviewNo(int reviewNo) throws Exception;
	
	/**
	 * 영화, 유저, 좋아요, 팔로우를 통한 리뷰 목록 조회
	 * (리뷰 요약 보기에서 사용)
	 */
	List<Review> getReviewsPage(ReviewContext type, int no, int page) throws Exception;
	
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
	
	/**
	 * 리뷰에 좋아요 등록
	 */
	void insertLike(int userNo, int reviewNo) throws Exception;
	
	/**
	 * 좋아요 삭제(취소)
	 */
	void deleteLike(int userNo, int reviewNo) throws Exception;
	
	/**
	 * 사용자가 현재 리뷰를 좋아요 했는지 확인
	 */
	boolean isLiking(int userNo, int reviewNo) throws Exception;
}
