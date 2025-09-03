package org.example.model.service;

import org.example.model.dto.*;
import org.example.util.DBManager;
import org.example.util.DBManagerImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * UserService 클래스
 * - User 관련 비즈니스 로직 처리
 * - Controller에서 호출되어 DB와 상호작용
 */
public class UserServiceImpl {
    private DBManager dbManager;

    // ================== 생성자 ==================
    public UserServiceImpl() {
        // DBManagerImpl을 통해 DB 연결 관리
        this.dbManager = new DBManagerImpl();
    }

    // ================== 이메일 중복 검사 ==================
    /**
     * 주어진 이메일이 DB에 이미 존재하는지 확인
     * @param email 확인할 이메일
     * @return true: 이메일 존재, false: 이메일 미존재
     */
    public boolean isEmailDuplicate(String email) {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";

        try (Connection conn = dbManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // 파라미터 세팅
            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();

            // 조회 결과가 있으면 0보다 큰지 체크
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace(); // 예외 발생 시 콘솔에 출력
        }

        return false;
    }

    // ================== 회원가입 처리 ==================
    /**
     * User DTO 정보를 DB에 저장
     * @param userDTO 회원 정보 객체
     * @return true: 저장 성공, false: 저장 실패
     */
    public boolean registerUser(User userDTO) {
        String sql = "INSERT INTO users (email, password, name) VALUES (?, ?, ?)";

        try (Connection conn = dbManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // DTO 데이터를 SQL 파라미터로 설정
            pstmt.setString(1, userDTO.getEmail());
            pstmt.setString(2, userDTO.getPassword());
            pstmt.setString(3, userDTO.getName());

            // 실행 후 영향받은 행(row) 수 반환
            int result = pstmt.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            e.printStackTrace(); // SQL 오류 발생 시 콘솔에 출력
            return false;
        }
    }

    // ================== 로그인 처리 ==================
    /**
     * 이메일과 비밀번호로 사용자 인증
     * @param email 이메일
     * @param password 비밀번호
     * @return 로그인 성공 시 User 객체 반환, 실패 시 null
     */
    public User login(String email, String password) {
        String sql = "SELECT user_no, email, password, name, join_date FROM users WHERE email = ? AND password = ?";

        try (Connection conn = dbManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // SQL 파라미터 세팅
            pstmt.setString(1, email);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // DB 결과를 User DTO로 변환
                User user = new User();
                user.setUserNo(rs.getInt("user_no"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                user.setJoinDate(rs.getTimestamp("join_date").toLocalDateTime());

                return user; // 로그인 성공
            }
        } catch (SQLException e) {
            e.printStackTrace(); // 오류 발생 시 출력
        }

        return null; // 로그인 실패
    }
}
