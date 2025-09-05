package org.example.util;

import org.example.model.dto.User;

/**
 * SessionManager
 * - 현재 로그인 사용자(Session)를 관리하는 인터페이스
 */
public interface SessionManager {

    /**
     * 로그인된 사용자 저장
     */
    void setLoggedInUser(User user);

    /**
     * 현재 로그인된 사용자 반환
     * @return User 객체 (로그인 안 되어 있으면 null)
     */
    User getLoggedInUser();

    /**
     * 로그아웃 (세션 초기화)
     */
    void logout();

    /**
     * 현재 로그인 사용자의 이름 반환
     * @return 로그인 상태면 사용자 이름, 아니면 "비회원"
     */
    String getCurrentUserName();
}
