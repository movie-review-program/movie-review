package org.example.model.dao;

import java.util.List;

import org.example.common.ReviewContext;
import org.example.model.dto.Review;

public interface ReviewDao {
	/**
	 * 리뷰 번호를 이용해 리뷰 조회
	 * @param reviewNo
	 * @return
	 * @throws Exception
	 */
	Review selectReviewByReviewNo(int reviewNo) throws Exception;
	
	
	/**
	 * 리뷰 요약 보기를 위한 리뷰 목록 조회
	 * 영화, 유저를 통한 리뷰를 size 개수만큼 반환
	 * @param entity 영화, 유저
	 * @param page 1부터 시작
	 * @param size
	 * @return
	 * @throws Exception
	 */
	List<Review> selectReviewsPage(ReviewContext type, int no, int page, int size) throws Exception;
	
	/**
	 * 좋아요, 팔로우 리뷰 요약 보기를 위한 리뷰 목록 조회
	 * 좋아요: 유저번호를 조건으로 likes 테이블에서 리뷰번호를 가져온 후, 리뷰번호에 해당하는 리뷰들을 리턴한다.
	 * 팔로우: followerNo를 조건으로 follows 테이블에서 followingNo 가져온 후, 이 유저들이 작성한 리뷰들을 리턴한다.
	 * @param entity
	 * @param no
	 * @param page 1부터 시작
	 * @param size
	 * @return
	 * @throws Exception
	 */
	List<Review> selectTwiceReviewsPage(ReviewContext type, int no, int page, int size) throws Exception;
	
	/**
	 * 해당 영화의 리뷰 개수를 조회
	 * @param movieNo
	 * @return
	 * @throws Exception
	 */
	int getReviewCount(int movieNo) throws Exception;

	int getReviewCountByUserNo(int userNo) throws Exception;
	
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
	int insertReview(Review review) throws Exception;
	
	/**
	 * 현재 사용자(유저)가 작성한 리뷰 수정
	 * @param review
	 * @return
	 */
	int updateReview(Review review) throws Exception;
	
	/**
	 * 현재 사용자(유저)가 선택한 리뷰 삭제
	 * @param reviewNo
	 * @return
	 */
	int deleteReview(int reviewNo) throws Exception;
	
	/**
	 * 리뷰에 좋아요 등록
	 * @param reviewNo
	 * @return
	 */
	int insertLike(int userNo, int reviewNo) throws Exception;
	
	/**
	 * 좋아요 삭제
	 * @param reviewNo
	 * @return
	 */
	int deleteLike(int userNo, int reviewNo) throws Exception;
	
	/**
	 * 해당 리뷰의 좋아요 개수를 조회
	 * @param reviewNo
	 * @return
	 * @throws Exception
	 */
	int getLikeCount(int reviewNo) throws Exception;
}
