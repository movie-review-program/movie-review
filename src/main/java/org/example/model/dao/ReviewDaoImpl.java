package org.example.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.exception.NotFoundException;
import org.example.model.dto.Review;
import org.example.util.DBManager;
import org.example.util.DBManagerImpl;

public class ReviewDaoImpl implements ReviewDao {
	private static ReviewDao instance = new ReviewDaoImpl();
	DBManager dbManager = new DBManagerImpl();
	
	private ReviewDaoImpl() {}
	
	public static ReviewDao getInstance() {
		return instance;
	}

	@Override
	public List<Review> findReviewsByMovieNo(long movieNo) throws Exception {
		List<Review> list = new ArrayList<>();
		String sql = "select * from reviews where movie_no = ?";
		
		try (Connection con = dbManager.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setLong(1, movieNo);
			
			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					list.add(new Review(
							rs.getLong(1), 
							rs.getInt(2), 
							rs.getString(3), 
							rs.getString(4), 
							rs.getLong(5), 
							rs.getLong(6)));
				}
			}
		} catch (SQLException e) {
			//e.printStackTrace();
			// TODO 사용자정의 예외 처리
			//throw new MyException("DB 문제");
		}
		
		return list;
	}

	@Override
	public List<Review> findReviewsByUserNo(long userNo) throws Exception {
		List<Review> list = new ArrayList<>();
		String sql = "select * from reviews where user_no = ?";
		
		try (Connection con = dbManager.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setLong(1, userNo);
			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					list.add(new Review(
							rs.getLong(1), 
							rs.getInt(2), 
							rs.getString(3), 
							rs.getString(4), 
							rs.getLong(5), 
							rs.getLong(6)));
				}				
			}
		} catch (SQLException e) {
			//e.printStackTrace();
			// TODO 사용자정의 예외 처리
		}
		
		return list;
	}

	@Override
	public List<Review> findReviewsByLike(long userNo) throws Exception {
		List<Review> list = new ArrayList<>();
		List<Long> reviewNoList = new ArrayList<>();
		String sql = "select review_no from likes where user_no = ?";
		
		try (Connection con = dbManager.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setLong(1, userNo);
			
			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					reviewNoList.add(rs.getLong("review_no"));
				}
				
				list = findReviewsByReviewNos(con, reviewNoList);
			}
		} catch (SQLException e) {
			//e.printStackTrace();
			// TODO 사용자정의 예외 처리
		}
		
		return list;
	}
	
	private List<Review> findReviewsByReviewNos(Connection con, List<Long> reviewNoList) throws Exception {
		List<Review> list = new ArrayList<>();
		
		String nos = reviewNoList.toString();
		nos = nos.substring(1, nos.length() - 1);
		
		String sql = "select * from reviews where review_no in (" + nos + ")";
		
		try (PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();) {
			while (rs.next()) {
				list.add(new Review(
						rs.getLong(1), 
						rs.getInt(2), 
						rs.getString(3), 
						rs.getString(4), 
						rs.getLong(5), 
						rs.getLong(6)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO 사용자정의 예외 처리
		}
		
		return list;
	}
	
	@Override
	public int getReviewCount(long movieNo) throws Exception {
		int count = 0;
		String sql = "select count(*) from reviews where movie_no = ?";
		
		try (Connection con = dbManager.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setLong(1, movieNo);
			
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					count = rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			//e.printStackTrace();
			// TODO 사용자정의 예외 처리
		}
		
		return count;
	}
	
	@Override
	public double getAverageRating(long movieNo) throws Exception {
		double avg = 0;
		String sql = "select avg(rating) from reviews where movie_no = ?"; 
		
		try (Connection con = dbManager.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setLong(1, movieNo);
			
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					avg = rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			//e.printStackTrace();
			// TODO 사용자정의 예외 처리
		}
		
		return avg;
	}

	@Override
	public int insertReview(Review review) {
		int result = 0;
		String sql = "insert into reviews(rating,content,user_no,movie_no) "
				+ "values(?, ?, ?, ?);";
		
		try (Connection con = dbManager.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, review.getRating());
			ps.setString(2, review.getContent());
			ps.setLong(3, review.getUserNo());
			ps.setLong(4, review.getMovieNo());
			
			result = ps.executeUpdate();
		} catch (SQLException e) {
			//e.printStackTrace();
			// TODO 사용자정의 예외 처리
		}
		
		return result;
	}

	@Override
	public int updateReview(Review review) {
		int result = 0;
		String sql = "update reviews set rating = ?, content = ? where review_no = ?";
		
		try (Connection con = dbManager.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, review.getRating());
			ps.setString(2, review.getContent());
			ps.setLong(3, review.getReviewNo());
			
			result = ps.executeUpdate();
		} catch (SQLException e) {
			//e.printStackTrace();
			// TODO 사용자정의 예외 처리
		}
		
		return result;
	}

	@Override
	public int deleteReview(long reviewNo) {
		int result = 0;
		String sql = "delete from reviews where review_no = ?";
		
		try (Connection con = dbManager.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setLong(1, reviewNo);
			
			result = ps.executeUpdate();
		} catch (SQLException e) {
			//e.printStackTrace();
			// TODO 사용자정의 예외 처리
		}
		
		return result;
	}

}
