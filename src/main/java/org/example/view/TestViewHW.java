package org.example.view;

import java.util.Scanner;
import org.example.controller.UserController;
import org.example.model.dto.User;

public class TestViewHW {
    private UserController controller = new UserController();
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            if (!controller.isLoggedIn()) {
                showMenuBeforeLogin();
            } else {
                showMenuAfterLogin();
            }
            String choice = scanner.nextLine().trim();
            handleMenu(choice);
        }
    }

    private void showMenuBeforeLogin() {
        System.out.println("\n===== 메뉴 (로그인 전) =====");
        System.out.println("1. 회원가입");
        System.out.println("2. 로그인");
        System.out.println("0. 종료");
        System.out.print("선택: ");
    }

    private void showMenuAfterLogin() {
        System.out.println("\n===== 메뉴 (로그인 후) =====");
        System.out.println("1. 로그아웃");
        System.out.println("2. 현재 로그인 상태 확인");
        System.out.println("3. 회원 정보 조회 (user_no)");
        System.out.println("4. 종료");
        System.out.print("선택: ");
    }

    private void handleMenu(String choice) {
        if (!controller.isLoggedIn()) {
            switch (choice) {
                case "1": signup(); break;
                case "2": login(); break;
                case "0": exit(); break;
                default: System.out.println("잘못된 선택입니다.");
            }
        } else {
            switch (choice) {
                case "1": logout(); break;
                case "2": checkLoginStatus(); break;
                case "3": getUserInfo(); break;
                case "4": exit(); break;
                default: System.out.println("잘못된 선택입니다.");
            }
        }
    }

    private void signup() {
        System.out.print("이메일: ");
        String email = scanner.nextLine().trim();
        System.out.print("비밀번호: ");
        String password = scanner.nextLine().trim();
        System.out.print("이름: ");
        String name = scanner.nextLine().trim();

        User user = controller.handleSignup(email, password, name);
        if (user != null) {
            System.out.println("✅ 회원가입 성공! 자동 로그인 완료 → " + user.getName());
        } else {
            System.out.println("❌ 회원가입 실패");
        }
    }

    private void login() {
        System.out.print("이메일: ");
        String email = scanner.nextLine().trim();
        System.out.print("비밀번호: ");
        String password = scanner.nextLine().trim();

        User user = controller.handleLogin(email, password);
        if (user != null) {
            System.out.println("✅ 로그인 성공! " + user.getName());
        } else {
            System.out.println("❌ 로그인 실패 (이메일/비밀번호 오류 또는 DB 문제)");
        }
    }

    private void logout() {
        String userName = controller.handleLogout();
        if (userName != null) {
            System.out.println("👋 로그아웃 완료: " + userName);
        }
    }

    private void checkLoginStatus() {
        if (controller.isLoggedIn()) {
            User current = controller.getCurrentUser();
            System.out.println("🔐 현재 로그인된 사용자:");
            System.out.println(" - ID(user_no): " + current.getUserNo());
            System.out.println(" - 이메일: " + current.getEmail());
            System.out.println(" - 이름: " + current.getName());
        } else {
            System.out.println("❌ 현재 로그인된 사용자가 없습니다.");
        }
    }

    private void getUserInfo() {
        System.out.print("조회할 user_no 입력: ");
        int userNo = Integer.parseInt(scanner.nextLine().trim());
        User user = controller.handleGetUserInfo(userNo);
        if (user != null) {
            System.out.println("📋 회원 상세 정보");
            System.out.println(" - ID(user_no): " + user.getUserNo());
            System.out.println(" - 이메일: " + user.getEmail());
            System.out.println(" - 이름: " + user.getName());
            System.out.println(" - 가입일: " + user.getJoinDate());
        } else {
            System.out.println("❌ 해당 회원을 찾을 수 없습니다.");
        }
    }

    private void exit() {
        System.out.println("프로그램을 종료합니다.");
        System.exit(0);
    }

    public static void main(String[] args) {
        new TestViewHW().start();
    }
}
