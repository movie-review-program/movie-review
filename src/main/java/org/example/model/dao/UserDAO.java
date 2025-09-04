package org.example.model.dao;

import org.example.model.dto.User;
import java.sql.SQLException;

/**
 * UserDAO 인터페이스
 * - 사용자(User) 관련 DB 접근 로직 정의
 * - 구현체(UserDAOImpl)에서 실제 SQL 처리 수행
 */
public interface UserDAO {

    /**
     * 이메일 중복 여부 확인
     * @param email 확인할 이메일
     * @return 이미 존재하면 true, 없으면 false
     * @throws SQLException DB 처리 중 오류
     */
    boolean isEmailDuplicate(String email) throws SQLException;

    /**
     * 회원 등록 (회원가입)
     * @param user 저장할 사용자 객체
     * @return 성공 시 true, 실패 시 false
     * @throws SQLException DB 처리 중 오류
     */
    boolean registerUser(User user) throws SQLException;

    /**
     * 이메일 + 비밀번호로 회원 전체 정보 조회
     * - 로그인 시 사용자 인증 및 정보 확인 용도로 사용
     * @param email 사용자 이메일
     * @param password 사용자 비밀번호
     * @return 조회된 User 객체 (없으면 null)
     * @throws SQLException DB 처리 중 오류
     */
    User selectUserByUseremailAndPassword(String email, String password) throws SQLException;

    /**
     * user_no(회원 고유 번호)로 회원 전체 정보 조회
     * - 마이페이지, 관리자 기능 등 특정 회원 정보 확인 용도로 사용
     * @param userNo 회원 고유 번호
     * @return 조회된 User 객체 (없으면 null)
     * @throws SQLException DB 처리 중 오류
     */
    User selectUserByUserNo(int userNo) throws SQLException;
}
