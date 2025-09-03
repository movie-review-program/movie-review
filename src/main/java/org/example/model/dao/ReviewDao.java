package org.example.model.dao;

import java.util.List;

import org.example.exception.NotFoundException;
import org.example.model.dto.Review;

public interface ReviewDao {
	/**
	 * 영화의 리뷰를 조회
	 * @param movieNo
	 * @return
	 * @throws NotFoundException
	 */
	List<Review> findReviewsByMovieNo(int movieNo) throws NotFoundException;
	
	/**
	 * 현재 사용자(유저)가 작성한 리뷰 조회
	 * @param userNo
	 * @return
	 * @throws NotFoundException
	 */
	List<Review> findReviewsByUserNo(int userNo) throws NotFoundException;
	
	/**
	 * 현재 사용자(유저)가 좋아요 한 리뷰 조회
	 * 유저번호를 조건으로 likes 테이블에서 리뷰번호를 가져온 후, 리뷰번호에 해당하는 리뷰들을 리턴한다.
	 * @param likeNo
	 * @return
	 * @throws NotFoundException
	 */
	List<Review> findReviewsByLike(int likeNo) throws NotFoundException;
	
	/**
	 * 리뷰 작성
	 * @param review
	 * @return
	 */
	int insertReview(Review review);
	
	/**
	 * 현재 사용자(유저)가 작성한 리뷰 수정
	 * @param review
	 * @return
	 */
	int updateReview(Review review);
	
	/**
	 * 현재 사용자(유저)가 선택한 리뷰 삭제
	 * @param reviewNo
	 * @return
	 */
	int deleteReview(int reviewNo);
}
