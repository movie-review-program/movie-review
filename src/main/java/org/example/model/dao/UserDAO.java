package org.example.model.dao;

import org.example.model.dto.User;

public interface UserDAO {
    boolean isEmailDuplicate(String email);   // 이메일 중복 여부
    boolean registerUser(User user);          // 회원가입
    User login(String email, String password); // 로그인
}
