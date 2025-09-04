package org.example.view;

import org.example.model.dto.User;

/**
 * AuthView
 * - 회원가입/로그인/로그아웃/상태확인의 성공·실패 메시지를 전담
 * - 출력만 담당 (Controller, Service는 메시지 모름)
 */
public class AuthView {

    // ================== 회원가입 뷰 ==================
    public static class SignupSuccessView {
        public void render(User user) {
            System.out.println("✅ 회원가입 성공! 환영합니다, " + user.getName() + "님");
        }
    }

    public static class SignupFailView {
        public void render() {
            System.out.println("❌ 회원가입 실패: 중복 이메일 또는 유효성 불만족");
        }
    }

    // ================== 로그인 뷰 ==================
    public static class LoginSuccessView {
        public void render(User user) {
            System.out.println("✅ 로그인 성공! 환영합니다, " + user.getName() + "님");
        }
    }

    public static class LoginFailView {
        public void render() {
            System.out.println("❌ 로그인 실패: 이메일 또는 비밀번호가 올바르지 않습니다.");
        }
    }

    // ================== 로그아웃 뷰 ==================
    public static class LogoutSuccessView {
        public void render(String userName) {
            System.out.println("👋 로그아웃 완료: " + userName);
        }
    }

    public static class LogoutFailView {
        public void render() {
            System.out.println("⚠ 로그아웃 실패: 로그인된 사용자가 없습니다.");
        }
    }

    // ================== 현재 로그인 상태 뷰 ==================
    public static class LoginStatusView {
        public void renderLoggedIn(String userName) {
            System.out.println("🔐 현재 로그인된 사용자: " + userName);
        }

        public void renderLoggedOut() {
            System.out.println("❌ 현재 로그인된 사용자가 없습니다.");
        }
    }
}
