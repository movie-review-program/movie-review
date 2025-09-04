package org.example.view;

import java.util.List;

import org.example.model.dao.ReviewDao;
import org.example.model.dao.ReviewDaoImpl;
import org.example.model.dto.Review;

public class TestViewMJ {

	public static void main(String[] args) {
		ReviewDao rd = ReviewDaoImpl.getInstance();
		try {
			System.out.println("----- 사용자 리뷰 목록 -----");
			List<Review> list = rd.findReviewsByUserNo(1);
			for (Review r : list) {
				System.out.println(r);
			}
			
			System.out.println("----- 좋아요 리뷰 목록 -----");
			list = rd.findReviewsByLike(1);
			for (Review r : list) {
				System.out.println(r);
			}
			
			System.out.println("----- 영화의 리뷰 개수 -----");
			System.out.println(rd.getReviewCount(1));
			
			System.out.println("----- 영화의 평점 -----");
			System.out.println(rd.getAverageRating(1));
			
			System.out.println("----- 영화의 리뷰 조회 -----");
			System.out.println(rd.findReviewsByMovieNo(1));
			
			System.out.println("----- 유저의 리뷰 조회 -----");
			System.out.println(rd.findReviewsByUserNo(1));
			
			System.out.println("----- 리뷰 등록 -----");
			Review review = new Review(5, "좋아요", 3, 2);
			rd.insertReview(review);
			System.out.println(rd.findReviewsByUserNo(3));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
