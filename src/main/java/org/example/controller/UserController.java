package org.example.controller;

import org.example.model.dto.User;
import org.example.model.service.UserServiceImpl;
import org.example.util.SessionManagerImpl;

/**
 * UserController 클래스
 * - 사용자 관련 요청(Signup, Login, 이메일 중복 검사, Logout)을 처리
 * - Service 계층과 연결하여 실제 데이터 처리를 위임
 * - 세션 관리를 통해 로그인 상태 유지
 */
public class UserController {

    // UserService 인스턴스 생성 (DB 처리 담당)
    private UserServiceImpl userService = new UserServiceImpl();
    
    // SessionManager 인스턴스 가져오기
    private SessionManagerImpl sessionManager = SessionManagerImpl.getInstance();

    /**
     * 회원가입 처리
     * @param email - 사용자가 입력한 이메일
     * @param password - 사용자가 입력한 비밀번호
     * @param name - 사용자가 입력한 이름
     * @return 가입 성공 시 true, 실패 시 false
     */
    public boolean handleSignup(String email, String password, String name) {
        try {
            // 입력값 유효성 검사
            if (!isValidInput(email, password, name)) {
                System.out.println("입력값이 유효하지 않습니다.");
                return false;
            }
            
            // 이메일 중복 검사
            if (userService.isEmailDuplicate(email)) {
                System.out.println("이미 존재하는 이메일입니다: " + email);
                return false;
            }

            // User 객체 생성 및 정보 세팅
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            user.setName(name);

            // UserService를 통해 DB에 사용자 등록
            boolean success = userService.registerUser(user);

            // 등록 성공 시 로그인 처리 (세션에 저장)
            if (success) {
                User loginUser = userService.login(email, password);
                if (loginUser != null) {
                    sessionManager.login(loginUser);
                    System.out.println("회원가입 및 로그인 완료: " + name);
                } else {
                    System.out.println("회원가입은 성공했으나 자동 로그인 실패");
                }
            } else {
                System.out.println("회원가입 실패");
            }

            return success;
            
        } catch (Exception e) {
            System.err.println("회원가입 중 오류 발생: " + e.getMessage());
            return false;
        }
    }

    /**
     * 로그인 처리
     * @param email - 입력한 이메일
     * @param password - 입력한 비밀번호
     * @return 로그인 성공 시 true, 실패 시 false
     */
    public boolean handleLogin(String email, String password) {
        try {
            // 입력값 유효성 검사
            if (email == null || email.trim().isEmpty() || 
                password == null || password.trim().isEmpty()) {
                System.out.println("이메일과 비밀번호를 모두 입력해주세요.");
                return false;
            }
            
            // 이미 로그인된 상태인지 확인
            if (sessionManager.isLoggedIn()) {
                System.out.println("이미 로그인된 상태입니다: " + sessionManager.getCurrentUserName());
                return true;
            }

            // UserService를 통해 사용자 인증
            User user = userService.login(email.trim(), password);

            // 인증 성공 시 세션에 사용자 저장
            if (user != null) {
                sessionManager.login(user);
                System.out.println("로그인 성공: " + user.getName());
                return true;
            } else {
                System.out.println("이메일 또는 비밀번호가 올바르지 않습니다.");
                return false;
            }
            
        } catch (Exception e) {
            System.err.println("로그인 중 오류 발생: " + e.getMessage());
            return false;
        }
    }

    /**
     * 로그아웃 처리
     * @return 로그아웃 성공 시 true
     */
    public boolean handleLogout() {
        try {
            if (sessionManager.isLoggedIn()) {
                String userName = sessionManager.getCurrentUserName();
                sessionManager.logout();
                System.out.println("로그아웃 완료: " + userName);
                return true;
            } else {
                System.out.println("로그인되지 않은 상태입니다.");
                return false;
            }
        } catch (Exception e) {
            System.err.println("로그아웃 중 오류 발생: " + e.getMessage());
            return false;
        }
    }

    /**
     * 이메일 중복 검사
     * @param email - 확인할 이메일
     * @return 이미 존재하면 true, 없으면 false
     */
    public boolean isEmailDuplicate(String email) {
        try {
            if (email == null || email.trim().isEmpty()) {
                return false;
            }
            return userService.isEmailDuplicate(email.trim());
        } catch (Exception e) {
            System.err.println("이메일 중복 검사 중 오류 발생: " + e.getMessage());
            return true; // 안전을 위해 중복으로 간주
        }
    }
    
    /**
     * 현재 로그인 상태 확인
     * @return 로그인 상태면 true, 아니면 false
     */
    public boolean isLoggedIn() {
        return sessionManager.isLoggedIn();
    }
    
    /**
     * 현재 로그인한 사용자 정보 조회
     * @return 현재 로그인한 User 객체, 없으면 null
     */
    public User getCurrentUser() {
        return sessionManager.getCurrentUser();
    }
    
    /**
     * 현재 로그인한 사용자 이름 조회
     * @return 사용자 이름, 로그인하지 않았으면 "비회원"
     */
    public String getCurrentUserName() {
        return sessionManager.getCurrentUserName();
    }

    /**
     * 입력값 유효성 검사
     * @param email 이메일
     * @param password 비밀번호
     * @param name 이름
     * @return 모든 값이 유효하면 true
     */
    private boolean isValidInput(String email, String password, String name) {
        // null 체크
        if (email == null || password == null || name == null) {
            return false;
        }
        
        // 빈 문자열 체크
        if (email.trim().isEmpty() || password.trim().isEmpty() || name.trim().isEmpty()) {
            return false;
        }
        
        // 이메일 형식 간단 검증
        if (!email.contains("@") || !email.contains(".")) {
            System.out.println("올바른 이메일 형식이 아닙니다.");
            return false;
        }
        
        // 비밀번호 길이 검증 (최소 4자)
        if (password.length() < 4) {
            System.out.println("비밀번호는 최소 4자 이상이어야 합니다.");
            return false;
        }
        
        // 이름 길이 검증 (최소 2자)
        if (name.trim().length() < 2) {
            System.out.println("이름은 최소 2자 이상이어야 합니다.");
            return false;
        }
        
        return true;
    }
}