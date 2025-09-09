package org.example.model.dao;

import org.example.model.dto.User;
import org.example.util.DBManager;
import org.example.util.DBManagerImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * UserDAOImpl - UserDAO 인터페이스 구현체 - SQL을 직접 실행하여 DB와 통신
 * TODO: setter -> 생성자로 변경
 */
public class UserDAOImpl implements UserDAO {
	private DBManager dbManager = new DBManagerImpl();
	ReviewDao reviewDao = ReviewDaoImpl.getInstance();

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
	public int registerUser(String email, String password, String name) throws SQLException {
		String sql = "INSERT INTO users (email, password, name) VALUES (?, ?, ?)";
		try (Connection conn = dbManager.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			pstmt.setString(3, name);
			return pstmt.executeUpdate();
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
					return new User(rs.getInt("user_no"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("name"),
                            rs.getTimestamp("join_date").toLocalDateTime());
				}
			}
		}
		return null;
	}

	@Override
	public User selectUserByUserNo(int userNo) throws Exception {
		String sql = "SELECT user_no, email, password, name, join_date FROM users WHERE user_no=?";
		try (Connection conn = dbManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, userNo);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					int followers = selectUserFollower(rs.getInt("user_no"));
					int followings = selectUserFollowing(rs.getInt("user_no"));
					int reviews = reviewDao.getReviewCountByUserNo(rs.getInt("user_no"));

					return new User(rs.getInt("user_no"),
							rs.getString("email"),
							rs.getString("password"),
							rs.getString("name"),
							rs.getTimestamp("join_date").toLocalDateTime(),
							reviews,
							followers,
							followings
					);
				}
			}
		}
		return null;
	}

	@Override
	public List<User> selectFollowers(int userNo, int page, int size) throws Exception {
		List<User> users = new ArrayList<>();
		String sql = """
                select *
                from follows
                where following_no = ?
                order by follow_no desc
                limit ? offset ?
                """;

		int offset = (page - 1) * size;
		try(Connection con = dbManager.getConnection();
			PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, userNo);
			ps.setInt(2, size);
			ps.setInt(3, offset);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					int fallowerNo = rs.getInt("follower_no");
					users.add(selectUserByUserNo(fallowerNo));
				}
			}
		}

		return users;
	}

	public int selectUserFollower(int userNo) throws SQLException {
		String sql = """
				select count(*) from follows where following_no = ?
				""";
		try(Connection con = dbManager.getConnection();
		PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, userNo);

			try(ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1);
				}
			}
		}
		return 0;
	}

	public int selectUserFollowing(int userNo) throws SQLException {
		String sql = """
				select count(*) from follows where follower_no = ?
				""";
		try(Connection con = dbManager.getConnection();
			PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, userNo);

			try(ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1);
				}
			}
		}
		return 0;
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
		String sql = "SELECT * FROM follows WHERE follower_no=? AND following_no=?";
		try (Connection conn = dbManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, followerNo);
			pstmt.setInt(2, followingNo);
			try (ResultSet rs = pstmt.executeQuery()) {
				return rs.next();
			}
		}
	}
}
