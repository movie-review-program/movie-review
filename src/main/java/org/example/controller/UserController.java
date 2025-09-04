package org.example.controller;

import org.example.model.dto.User;
import org.example.model.service.UserService;
import org.example.model.service.UserServiceImpl;
import org.example.util.SessionManagerImpl;
import org.example.view.AuthView;

/**
 * UserController
 * - Service 계층 호출만 담당
 * - 입력값 검증 로직은 모두 Service에 위임
 * - View와 Service 사이의 다리 역할
 */
public class UserController {
    private UserService userService = new UserServiceImpl();
    private SessionManagerImpl sessionManager = SessionManagerImpl.getInstance();

    // ================== 회원가입 ==================
    public boolean handleSignup(String email, String password, String name) {
        User loginUser = userService.registerAndLogin(new User(email, password, name));

        if (loginUser != null) {
            sessionManager.login(loginUser); // 세션 저장만 Controller에서 수행
            return true;
        }
        return false;
    }

    // ================== 로그인 ==================
    public boolean handleLogin(String email, String password) {
        User user = userService.login(email, password);

        if (user != null) {
            sessionManager.login(user);
            return true;
        }
        return false;
    }

    public boolean handleLogout() {
        if (sessionManager.isLoggedIn()) {
            String userName = sessionManager.getCurrentUserName(); // 로그아웃 전 이름 저장
            sessionManager.logout();
            new AuthView.LogoutSuccessView().render(userName); // 저장해둔 이름 전달
            return true;
        }
        new AuthView.LogoutFailView().render();
        return false;
    }

    // ================== 세션 확인 ==================
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
