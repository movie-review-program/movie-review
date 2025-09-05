package org.example.model.dao;

import org.example.model.dto.User;
import org.example.model.dto.ReviewFeedDTO;
import org.example.util.DBManager;
import org.example.util.DBManagerImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * UserDAOImpl - UserDAO 인터페이스 구현체 - SQL을 직접 실행하여 DB와 통신
 */
public class UserDAOImpl implements UserDAO {

	private DBManager dbManager = new DBManagerImpl();

	@Override
	public boolean isEmailDuplicate(String email) throws SQLException {
		String sql = "SELECT COUNT(*) FROM users WHERE email=?";
		try (Connection conn = dbManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, email);
			try (ResultSet rs = pstmt.executeQuery()) {
				return rs.next() && rs.getInt(1) > 0;
			}
		}
	}

	@Override
	public boolean registerUser(User user) throws SQLException {
		String sql = "INSERT INTO users (email, password, name) VALUES (?, ?, ?)";
		try (Connection conn = dbManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, user.getEmail());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getName());
			return pstmt.executeUpdate() > 0;
		}
	}

	@Override
	public User selectUserByUseremailAndPassword(String email, String password) throws SQLException {
		String sql = "SELECT user_no, email, password, name, join_date FROM users WHERE email=? AND password=?";
		try (Connection conn = dbManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					User user = new User();
					user.setUserNo(rs.getInt("user_no"));
					user.setEmail(rs.getString("email"));
					user.setPassword(rs.getString("password"));
					user.setName(rs.getString("name"));
					user.setJoinDate(rs.getTimestamp("join_date").toLocalDateTime());
					return user;
				}
			}
		}
		return null;
	}

	@Override
	public User selectUserByUserNo(int userNo) throws SQLException {
		String sql = "SELECT user_no, email, password, name, join_date FROM users WHERE user_no=?";
		try (Connection conn = dbManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, userNo);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					User user = new User();
					user.setUserNo(rs.getInt("user_no"));
					user.setEmail(rs.getString("email"));
					user.setPassword(rs.getString("password"));
					user.setName(rs.getString("name"));
					user.setJoinDate(rs.getTimestamp("join_date").toLocalDateTime());
					return user;
				}
			}
		}
		return null;
	}

	@Override
	public boolean followUser(int followerNo, int followingNo) throws Exception {
		String sql = "INSERT INTO follows (follower_no, following_no) VALUES (?, ?)";
		try (Connection conn = dbManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, followerNo);
			pstmt.setInt(2, followingNo);
			return pstmt.executeUpdate() > 0;
		}
	}

	@Override
	public boolean unfollowUser(int followerNo, int followingNo) throws Exception {
		String sql = "DELETE FROM follows WHERE follower_no=? AND following_no=?";
		try (Connection conn = dbManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, followerNo);
			pstmt.setInt(2, followingNo);
			return pstmt.executeUpdate() > 0;
		}
	}

	@Override
	public boolean isFollowing(int followerNo, int followingNo) throws Exception {
		String sql = "SELECT 1 FROM follows WHERE follower_no=? AND following_no=? LIMIT 1";
		try (Connection conn = dbManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, followerNo);
			pstmt.setInt(2, followingNo);
			try (ResultSet rs = pstmt.executeQuery()) {
				return rs.next();
			}
		}
	}

	@Override
	public List<User> getFollowingList(int followerNo) throws Exception {
		List<User> list = new ArrayList<>();
		String sql = "SELECT u.user_no, u.email, u.name, u.join_date "
				+ "FROM follows f JOIN users u ON f.following_no = u.user_no " + "WHERE f.follower_no = ?";

		try (Connection conn = dbManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, followerNo);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					User user = new User();
					user.setUserNo(rs.getInt("user_no"));
					user.setEmail(rs.getString("email"));
					user.setName(rs.getString("name"));
					user.setJoinDate(rs.getTimestamp("join_date").toLocalDateTime());
					list.add(user);
				}
			}
		}
		return list;
	}

	@Override
	public List<ReviewFeedDTO> getFollowingReviews(int followerNo) throws Exception {
		List<ReviewFeedDTO> list = new ArrayList<>();
		String sql = "SELECT u.name AS user_name, m.movie_name, r.rating, "
				+ "       (SELECT COUNT(*) FROM likes l WHERE l.review_no = r.review_no) AS like_count, "
				+ "       r.content, r.reg_date " + "FROM reviews r " + "JOIN users u ON r.user_no = u.user_no "
				+ "JOIN movies m ON r.movie_no = m.movie_no "
				+ "WHERE r.user_no IN (SELECT following_no FROM follows WHERE follower_no = ?) "
				+ "ORDER BY r.reg_date DESC";

		try (Connection conn = dbManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, followerNo);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					ReviewFeedDTO dto = new ReviewFeedDTO();
					dto.setUserName(rs.getString("user_name"));
					dto.setMovieName(rs.getString("movie_name"));
					dto.setRating(rs.getInt("rating"));
					dto.setLikeCount(rs.getInt("like_count"));
					dto.setContent(rs.getString("content"));
					dto.setRegDate(rs.getTimestamp("reg_date").toLocalDateTime());
					list.add(dto);
				}
			}
		}
		return list;
	}
}
