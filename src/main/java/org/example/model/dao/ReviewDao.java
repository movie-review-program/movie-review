package org.example.model.dao;

import java.util.List;

import org.example.exception.NotFoundException;
import org.example.model.dto.Review;

public interface ReviewDao {
	/**
	 * 리뷰 번호를 이용해 리뷰 조회
	 * @param reviewNo
	 * @return
	 * @throws Exception
	 */
	Review findReviewByReviewNo(int reviewNo) throws Exception;
	
	/**
	 * 영화의 리뷰를 조회
	 * @param movieNo
	 * @return
	 * @throws Exception
	 */
	List<Review> findReviewsByMovieNo(int movieNo) throws Exception;
	
	/**
	 * 현재 사용자(유저)가 작성한 리뷰 조회
	 * @param userNo
	 * @return
	 * @throws Exception
	 */
	List<Review> findReviewsByUserNo(int userNo) throws Exception;
	
	/**
	 * 현재 사용자(유저)가 좋아요 한 리뷰 조회
	 * 유저번호를 조건으로 likes 테이블에서 리뷰번호를 가져온 후, 리뷰번호에 해당하는 리뷰들을 리턴한다.
	 * @param likeNo
	 * @return
	 * @throws Exception
	 */
	List<Review> findReviewsByLike(int userNo) throws Exception;
	
	/**
	 * 해당 영화의 리뷰 개수를 조회
	 * @param movieNo
	 * @return
	 * @throws Exception
	 */
	int getReviewCount(int movieNo) throws Exception;
	
	/**
	 * 해당 영화의 평균 별점(평점)을 조회
	 * @param movieNo
	 * @return
	 * @throws Exception
	 */
	double getAverageRating(int movieNo) throws Exception;
	
	/**
	 * 리뷰 등록
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
