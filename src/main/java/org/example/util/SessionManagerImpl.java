// SessionManagerImpl.java (기존 SessionManager 클래스명 변경)
package org.example.util;

import org.example.model.dto.User;

/**
 * SessionManager 메모리 구현체
 * - SessionManager 인터페이스를 static 변수로 구현
 */
public class SessionManagerImpl implements SessionManager {
    
    private static User currentUser = null;
    private static SessionManagerImpl instance = new SessionManagerImpl();
    
    private SessionManagerImpl() {}
    
    public static SessionManagerImpl getInstance() {
        return instance;
    }
    
    @Override
    public void login(User user) {
        currentUser = user;
    }
    
    @Override
    public void logout() {
        currentUser = null;
    }
    
    @Override
    public User getCurrentUser() {
        return currentUser;
    }
    
    @Override
    public boolean isLoggedIn() {
        return currentUser != null;
    }
    
    @Override
    public String getCurrentUserName() {
        return currentUser != null ? currentUser.getName() : "비회원";
    }
    
    @Override
    public String getCurrentUserEmail() {
        return currentUser != null ? currentUser.getEmail() : "";
    }
    
    @Override
    public int getCurrentUserId() {
        return currentUser != null ? currentUser.getUserNo() : -1;
    }
}