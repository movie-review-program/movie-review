package org.example.util;

import org.example.model.dto.User;

/**
 * SessionManager 인터페이스
 * - 세션 관리 기능의 표준 규격 정의
 * - 메모리 세션, Redis 세션 등 다양한 구현체로 교체 가능
 */
public interface SessionManager {
    
    /**
     * 로그인 처리
     * @param user 로그인할 User 객체
     */
    void login(User user);
    
    /**
     * 로그아웃 처리
     */
    void logout();
    
    /**
     * 현재 로그인한 User 객체 반환
     * @return User 객체, 로그인 안 되어 있으면 null
     */
    User getCurrentUser();
    
    /**
     * 로그인 여부 확인
     * @return true: 로그인 상태, false: 비로그인 상태
     */
    boolean isLoggedIn();
    
    /**
     * 현재 로그인한 사용자의 이름 반환
     * @return 사용자 이름, 비회원이면 "비회원" 반환
     */
    String getCurrentUserName();
    
    /**
     * 현재 로그인한 사용자의 이메일 반환
     * @return 이메일, 비회원이면 빈 문자열
     */
    String getCurrentUserEmail();
    
    /**
     * 현재 로그인한 사용자의 ID(userNo) 반환
     * @return userNo, 비회원이면 -1
     */
    int getCurrentUserId();
}