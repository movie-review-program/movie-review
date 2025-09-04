package org.example.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.example.model.dto.User;
import org.example.util.DBManager;
import org.example.util.DBManagerImpl;

/**
 * UserDAOImpl
 * - UserDAO 인터페이스의 구현체
 * - SQL 실행을 전담 (DB 접근 계층)
 * - Service 계층에서는 DAO만 호출하고, SQL을 직접 다루지 않음
 */
public class UserDAOImpl implements UserDAO {
    
    // DB 연결 관리 객체
    private DBManager dbManager;

    // ================== SQL 쿼리 상수 ==================
    private static final String COUNT_BY_EMAIL_SQL = 
        "SELECT COUNT(*) FROM users WHERE email = ?";
    private static final String INSERT_USER_SQL = 
        "INSERT INTO users (email, password, name) VALUES (?, ?, ?)";
    private static final String SELECT_USER_BY_EMAIL_PASSWORD_SQL = 
        "SELECT user_no, email, password, name, join_date " +
        "FROM users WHERE email = ? AND password = ?";
    
    /**
     * 생성자
     * - DBManager 인스턴스 초기화 (DB 연결 준비)
     */
    public UserDAOImpl() {
        this.dbManager = new DBManagerImpl();
    }
    
    // ================== 이메일 중복 검사 ==================
    /**
     * 이메일 중복 여부 확인
     * @param email 검사할 이메일
     * @return true → 이미 존재, false → 사용 가능
     */
    @Override
    public boolean isEmailDuplicate(String email) {
        try (Connection conn = dbManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(COUNT_BY_EMAIL_SQL)) {
            
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0; // 0보다 크면 중복
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // DB 오류 시 기본적으로 false 반환
    }
    
    // ================== 회원가입 ==================
    /**
     * 사용자 등록
     * @param user 등록할 사용자 객체
     * @return true → 등록 성공, false → 실패
     */
    @Override
    public boolean registerUser(User user) {
        try (Connection conn = dbManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(INSERT_USER_SQL)) {
            
            pstmt.setString(1, user.getEmail());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            
            return pstmt.executeUpdate() > 0; // 영향받은 행이 1 이상이면 성공
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // ================== 로그인 ==================
    /**
     * 로그인 처리
     * @param email 사용자 이메일
     * @param password 사용자 비밀번호
     * @return 로그인 성공 시 User 객체, 실패 시 null
     */
    @Override
    public User login(String email, String password) {
        try (Connection conn = dbManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_USER_BY_EMAIL_PASSWORD_SQL)) {
            
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // 실패 시 null 반환
    }
}
