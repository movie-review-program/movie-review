package org.example.model.service;

import java.util.List;

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
	public Review findReviewByReviewNo(int reviewNo) throws Exception {
		Review review = reviewDao.findReviewByReviewNo(reviewNo);
		
		if (review == null)
			throw new Exception("해당 리뷰가 존재하지 않습니다.");
		
		return review;
	}
	
	@Override
	public List<Review> findReviewsByMovieNo(int movieNo) throws Exception {
		List<Review> list = reviewDao.findReviewsByMovieNo(movieNo);

		if (list.isEmpty())
			throw new Exception("해당 영화에 리뷰가 없습니다.");
		
		return list;
	}

	@Override
	public List<Review> findReviewsByUserNo(int userNo) throws Exception {
		List<Review> list = reviewDao.findReviewsByUserNo(userNo);
		
		if (list.isEmpty())
			throw new Exception("사용자가 남긴 리뷰가 없습니다.");
		
		return list;
	}

	@Override
	public List<Review> findReviewsByLike(int userNo) throws Exception {
		List<Review> list = reviewDao.findReviewsByLike(userNo);
		
		if (list.isEmpty())
			throw new Exception("좋아요 한 리뷰가 없습니다.");
		
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
}
