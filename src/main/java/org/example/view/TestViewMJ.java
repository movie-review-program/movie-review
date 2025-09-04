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
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
