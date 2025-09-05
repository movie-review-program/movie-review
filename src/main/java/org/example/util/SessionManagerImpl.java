package org.example.util;

import org.example.model.dto.User;

/**
 * SessionManagerImpl
 * - 애플리케이션에서 현재 로그인한 사용자 정보를 관리하는 싱글턴 클래스
 */
public class SessionManagerImpl implements SessionManager {

    private static SessionManagerImpl instance = new SessionManagerImpl();
    private User loggedInUser; // 현재 로그인한 사용자

    private SessionManagerImpl() {}

    public static SessionManagerImpl getInstance() {
        return instance;
    }

    @Override
    public void setLoggedInUser(User user) {
        this.loggedInUser = user;
    }

    @Override
    public User getLoggedInUser() {
        return this.loggedInUser;
    }

    @Override
    public void logout() {
        this.loggedInUser = null;
    }

    @Override
    public String getCurrentUserName() {
        return loggedInUser != null ? loggedInUser.getName() : "비회원";
    }
}
