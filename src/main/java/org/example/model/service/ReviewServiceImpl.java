package org.example.model.service;

import java.util.List;

import org.example.common.ReviewContext;
import org.example.model.dao.ReviewDao;
import org.example.model.dao.ReviewDaoImpl;
import org.example.model.dto.Review;

public class ReviewServiceImpl implements ReviewService {
	private static ReviewService instance = new ReviewServiceImpl();
	private ReviewDao reviewDao = ReviewDaoImpl.getInstance();
	
	private ReviewServiceImpl() {}
	
	public static ReviewService getInstance() {
		return instance;
	}

	@Override
	public Review getReviewByReviewNo(int reviewNo) throws Exception {
		Review review = reviewDao.selectReviewByReviewNo(reviewNo);
		
		if (review == null)
			throw new Exception("해당 리뷰가 존재하지 않습니다.");
		
		return review;
	}
	
	@Override
	public List<Review> getReviewsPage(ReviewContext type, int no, int page) throws Exception {
		List<Review> list = null;
		int size = 3;
		
		if (type == ReviewContext.MOVIE || type == ReviewContext.USER) 
			list = reviewDao.selectReviewsPage(type, no, page, size);
		else if (type == ReviewContext.LIKE || type == ReviewContext.FOLLOW)
			list = reviewDao.selectTwiceReviewsPage(type, no, page, size);
		
		if (list.isEmpty())
			throw new Exception("리뷰가 존재하지 않습니다.");
		
		return list;
	}

	@Override
	public void insertReview(Review review) throws Exception {
		int result = reviewDao.insertReview(review);
		
		if (result == 0)
			throw new Exception("리뷰가 등록되지 않았습니다.");
	}

	@Override
	public void updateReview(Review review) throws Exception {
		int result = reviewDao.updateReview(review);
		
		if (result == 0)
			throw new Exception("리뷰가 수정되지 않았습니다.");
	}

	@Override
	public void deleteReview(int reviewNo) throws Exception {
		int result = reviewDao.deleteReview(reviewNo);
		
		if (result == 0)
			throw new Exception("리뷰가 삭제되지 않았습니다.");
	}

	@Override
	public void insertLike(int userNo, int reviewNo) throws Exception {
		int result = reviewDao.insertLike(userNo, reviewNo);
		
		if (result == 0)
			throw new Exception("해당 리뷰에 좋아요 하기를 실패했습니다.");
	}

	@Override
	public void deleteLike(int userNo, int reviewNo) throws Exception {
		int result = reviewDao.deleteLike(userNo, reviewNo);
		
		if (result == 0)
			throw new Exception("해당 리뷰의 좋아요 취소를 실패했습니다.");
	}

	@Override
	public boolean isLiking(int userNo, int reviewNo) throws Exception {
		return reviewDao.isLiking(userNo, reviewNo);
	}
}
