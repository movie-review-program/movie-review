package org.example.view;

import java.util.Scanner;

import org.example.controller.UserController;
import org.example.model.dto.User;

/**
 * 콘솔 기반 View (메뉴 화면)
 * - Controller 호출 후 AuthView를 통해 출력만 담당
 */
public class UserView {
    private UserController controller = new UserController();
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            System.out.println("\n===== 메뉴 =====");
            System.out.println("1. 회원가입");
            System.out.println("2. 로그인");
            System.out.println("3. 로그아웃");
            System.out.println("4. 현재 로그인 상태 확인");
            System.out.println("0. 종료");
            System.out.print("선택: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1": signup(); break;
                case "2": login(); break;
                case "3": logout(); break;
                case "4": checkLoginStatus(); break;
                case "0":
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("잘못된 선택입니다.");
            }
        }
    }

    // ================== 회원가입 ==================
    private void signup() {
        System.out.print("이메일: ");
        String email = scanner.nextLine();
        System.out.print("비밀번호: ");
        String password = scanner.nextLine();
        System.out.print("이름: ");
        String name = scanner.nextLine();

        boolean success = controller.handleSignup(email, password, name);

        if (success) {
            // ✅ 세션에서 현재 로그인된 사용자 꺼내기
            User user = controller.getCurrentUser();
            new AuthView.SignupSuccessView().render(user);
        } else {
            new AuthView.SignupFailView().render();
        }
    }

    // ================== 로그인 ==================
    private void login() {
        System.out.print("이메일: ");
        String email = scanner.nextLine();
        System.out.print("비밀번호: ");
        String password = scanner.nextLine();

        boolean success = controller.handleLogin(email, password);

        if (success) {
            User user = controller.getCurrentUser();
            new AuthView.LoginSuccessView().render(user);
        } else {
            new AuthView.LoginFailView().render();
        }
    }

    // ================== 로그아웃 ==================
    private void logout() {
        boolean result = controller.handleLogout();

        if (result) {
            new AuthView.LogoutSuccessView();
        } else {
            new AuthView.LogoutFailView();
        }
    }

    // ================== 현재 로그인 상태 확인 ==================
    private void checkLoginStatus() {
        if (controller.isLoggedIn()) {
            new AuthView.LoginStatusView().renderLoggedIn(controller.getCurrentUserName());
        } else {
            new AuthView.LoginStatusView().renderLoggedOut();
        }
    }

    public static void main(String[] args) {
        new UserView().start();
    }
}
