package org.example.model.service;

import org.example.model.dao.UserDAO;
import org.example.model.dao.UserDAOImpl;
import org.example.model.dto.User;

/**
 * UserServiceImpl
 * - Service 계층 구현체
 * - 비즈니스 로직 + 입력값 검증 담당
 * - SQL은 DAO에서만 실행
 */
public class UserServiceImpl implements UserService {
    private UserDAO dao = new UserDAOImpl();

    // ================== 이메일 사용 가능 여부 ==================
    @Override
    public boolean isEmailAvailable(String email) {
        if (email == null || email.trim().isEmpty()) return false;
        if (!email.contains("@")) return false; // 간단한 이메일 형식 체크
        return !dao.isEmailDuplicate(email);    // DAO 호출
    }

    // ================== 회원가입 처리 ==================
    @Override
    public boolean registerUser(User userDTO) {
        if (userDTO == null) return false;
        if (userDTO.getEmail() == null || userDTO.getEmail().trim().isEmpty()) return false;
        if (userDTO.getPassword() == null || userDTO.getPassword().length() < 4) return false;
        if (userDTO.getName() == null || userDTO.getName().length() < 2) return false;

        if (dao.isEmailDuplicate(userDTO.getEmail())) return false; // 중복 검사

        return dao.registerUser(userDTO); // DAO 호출
    }

    // ================== 로그인 처리 ==================
    @Override
    public User login(String email, String password) {
        if (email == null || email.trim().isEmpty()) return null;
        if (password == null || password.trim().isEmpty()) return null;

        return dao.login(email, password); // DAO 호출
    }
    
    
    @Override
    public User registerAndLogin(User userDTO) {
        // 1. 회원가입 처리
        boolean success = registerUser(userDTO);

        // 2. 회원가입 성공 → 로그인 시도
        if (success) {
            return login(userDTO.getEmail(), userDTO.getPassword());
        }

        return null; // 실패 시
    }

}


