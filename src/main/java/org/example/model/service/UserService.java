package org.example.model.service;

import org.example.model.dto.User;

/**
 * UserService 인터페이스
 * - 비즈니스 로직 계층
 * - Controller와 DAO 사이에서 데이터 검증 및 흐름 제어 담당
 */
public interface UserService {
   
    /**
     * 로그인 처리
     * - 이메일과 비밀번호를 검증하고
     * - 해당 사용자의 전체 정보를 반환한다.
     *
     * @param email 사용자 이메일
     * @param password 사용자 비밀번호
     * @return 로그인 성공 시 User 객체, 실패 시 null 또는 Exception 발생
     * @throws Exception 이메일/비밀번호 오류, DB 접근 문제 등
     */
    User login(String email, String password) throws Exception;

    /**
     * 회원가입 후 자동 로그인 처리
     * - 새로운 사용자를 DB에 등록하고
     * - 즉시 로그인 상태(User 객체 반환)로 만든다.
     *
     * @param user 등록할 사용자 객체
     * @return 등록 + 로그인 성공 시 User 객체
     * @throws Exception 유효성 검증 실패, DB 오류 등
     */
    User registerAndLogin(User user) throws Exception;

    /**
     * user_no(회원 고유 번호) 기반으로 사용자 정보 조회
     * - 마이페이지, 관리자 페이지 등에서 특정 회원 정보 확인 시 사용
     *
     * @param userNo 회원 고유 번호
     * @return 해당 user_no의 User 객체 (없으면 null)
     * @throws Exception 잘못된 번호이거나 DB 오류 시
     */
    User getUserByUserNo(int userNo) throws Exception;
}
