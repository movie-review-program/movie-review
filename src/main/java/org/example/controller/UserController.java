package org.example.controller;

import java.sql.SQLException;

import org.example.model.dto.User;
import org.example.model.service.UserService;
import org.example.model.service.UserServiceImpl;
import org.example.util.SessionManagerImpl;

public class UserController {
    private UserService userService = new UserServiceImpl();
    private SessionManagerImpl sessionManager = SessionManagerImpl.getInstance();

  
    public User handleSignup(String email, String password, String name) {
        try {
            User loginUser = userService.registerAndLogin(new User(email, password, name));
            sessionManager.login(loginUser);
            return loginUser; 
        } catch (SQLException e) {
            System.err.println("DB 오류(회원가입): " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.err.println("회원가입 실패: " + e.getMessage());
            return null;
        }
    }

   
    public User handleLogin(String email, String password) {
        try {
            User user = userService.login(email, password);
            sessionManager.login(user);
            return user; 
        } catch (SQLException e) {
            System.err.println("DB 오류(로그인): " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.err.println("로그인 실패: " + e.getMessage());
            return null;
        }
    }

  
    public String handleLogout() {
        try {
            if (!sessionManager.isLoggedIn()) {
                throw new Exception("로그인된 사용자가 없습니다.");
            }
            String userName = sessionManager.getCurrentUserName();
            sessionManager.logout();
            return userName; 
        } catch (Exception e) {
            System.err.println("로그아웃 실패: " + e.getMessage());
            return null;
        }
    }

  
    public boolean isLoggedIn() {
        return sessionManager.isLoggedIn();
    }

    public User getCurrentUser() {
        return sessionManager.getCurrentUser();
    }

    public String getCurrentUserName() {
        return sessionManager.getCurrentUserName();
    }
}
