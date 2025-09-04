package org.example.util;

import org.example.model.dto.User;


public class SessionManagerImpl implements SessionManager {
    
    private static User currentUser = null;
    private static final SessionManagerImpl instance = new SessionManagerImpl();

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
}
