package org.example.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.example.model.dto.User;
import org.example.util.DBManager;
import org.example.util.DBManagerImpl;

public class UserDAOImpl implements UserDAO {
    private DBManager dbManager = new DBManagerImpl();

    private static final String COUNT_BY_EMAIL_SQL =
            "SELECT COUNT(*) FROM users WHERE email = ?";
    private static final String INSERT_USER_SQL =
            "INSERT INTO users (email, password, name) VALUES (?, ?, ?)";
    private static final String SELECT_USER_SQL =
            "SELECT user_no, email, password, name, join_date FROM users WHERE email = ? AND password = ?";

   
    @Override
    public boolean isEmailDuplicate(String email) throws SQLException {
        try (Connection conn = dbManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(COUNT_BY_EMAIL_SQL)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        }
    }

   
    @Override
    public boolean registerUser(User user) throws SQLException {
        try (Connection conn = dbManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(INSERT_USER_SQL)) {
            pstmt.setString(1, user.getEmail());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            return pstmt.executeUpdate() > 0;
        }
    }

 
    @Override
    public User login(String email, String password) throws SQLException {
        try (Connection conn = dbManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_USER_SQL)) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setUserNo(rs.getInt("user_no"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                user.setJoinDate(rs.getTimestamp("join_date").toLocalDateTime());
                return user;
            }
            return null;
        }
    }
}
