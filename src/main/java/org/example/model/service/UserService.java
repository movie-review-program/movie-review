package org.example.model.service;

import org.example.model.dto.User;

/**
 * UserService 인터페이스
 * - 사용자 관련 비즈니스 로직의 표준 규격 정의
 * - 다양한 비즈니스 로직 구현체로 교체 가능
 */
public interface UserService {
    
    /**
     * 주어진 이메일이 DB에 이미 존재하는지 확인
     * @param email 확인할 이메일
     * @return true: 이메일 존재, false: 이메일 미존재
     */
    boolean isEmailDuplicate(String email);
    
    /**
     * User DTO 정보를 DB에 저장
     * @param userDTO 회원 정보 객체
     * @return true: 저장 성공, false: 저장 실패
     */
    boolean registerUser(User userDTO);
    
    /**
     * 이메일과 비밀번호로 사용자 인증
     * @param email 이메일
     * @param password 비밀번호
     * @return 로그인 성공 시 User 객체 반환, 실패 시 null
     */
    User login(String email, String password);
}