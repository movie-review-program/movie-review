package org.example.model.service;

import org.example.model.dto.User;

/**
 * UserService
 * - 사용자 관련 비즈니스 로직 정의
 */
public interface UserService {
    boolean isEmailAvailable(String email);   // 이메일 사용 가능 여부
    boolean registerUser(User user);          // 회원가입 처리
    User login(String email, String password); // 로그인 처리
    User registerAndLogin(User user); //회원가입시 자동로그인
}
