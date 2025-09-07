package org.example.view;

import java.util.Scanner;

import org.example.controller.UserController;
import org.example.model.dto.User;

public class LoginPageView {
    private static final Scanner sc = new Scanner(System.in);

    /*
     * 1. 로그인
     * 2. 회원가입
     * 3. 메인페이지로 가기
     * 4. 종료
     * */
    public static void menu() {
        while (true) {
            try {
                int num = LoginV();
                switch (num) {
                    case 1:
                        login();
                        break;
                    case 2:
                        signUp();
                        break;
                    case 3:
                        mainPage();
                        break;
                    case 4:
                        System.exit(0);
                    default:
                        System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
                }
            } catch (NumberFormatException e) {
                System.out.println("메뉴는 숫자만 가능합니다.");
            }
        }
    }

    private static int LoginV() {
        outputLoginV();
        int num = choiceNumber();
        return num;
    }

    /**
     * @param:  email, password
     * */
    private static void login() {
        outputLogin();
        String email = inputEmail();
        String password = inputPassword();
        UserController.login(email, password);
    }

    /**
     * @param:  email, password, name
     * */
    private static void signUp() {
        outputJoin();
        String email = inputEmail();
        String password = inputPassword();
        String name = inputName();
        UserController.registerAndLogin(email, password, name);
        // MainPageView.menu(user.getUserNo());
    }

    /*
     * 비회원 로그인
     * */
    private static void mainPage() {
        MainPageView.menu(new User(null, null, "비회원"));
    }


    private static void outputLoginV() {
        System.out.println("""
                ┌──────────────────────────────────────────────────┐
                │  ██████╗ ███████╗██╗   ██╗██╗███████╗██╗    ██╗  │
                │  ██╔══██╗██╔════╝██║   ██║██║██╔════╝██║    ██║  │
                │  ██████╔╝█████╗  ██║   ██║██║█████╗  ██║ █╗ ██║  │
                │  ██╔══██╗██╔══╝  ╚██╗ ██╔╝██║██╔══╝  ██║███╗██║  │
                │  ██║  ██║███████╗ ╚████╔╝ ██║███████╗╚███╔███╔╝  │
                │  ╚═╝  ╚═╝╚══════╝  ╚═══╝  ╚═╝╚══════╝ ╚══╝╚══╝   │
                │            🎬 MOVIE REVIEW SYSTEM 🎬             │
                ├──────────────────────────────────────────────────┤
                │    1. 로그인                                       │
                │    2. 회원가입                                     │
                │    3. 메인페이지로 가기                              │
                │    4. 종료하기                                     │""");
    }

    private static void outputLogin() {
        System.out.println("""
                ┌──────────────────────────────────────────────────┐
                │                  🍿  로그인  🍿                   │
                │                                                  │
                │            ★ 리뷰를 남기고, 함께 즐겨보세요 ★           │
                ├──────────────────────────────────────────────────┤""");
    }

    private static void outputJoin() {
        System.out.println("""
                ┌──────────────────────────────────────────────────┐
                │                 🍿  회원가입  🍿                   │
                │                                                  │
                │            ★ 리뷰를 남기고, 함께 즐겨보세요 ★           │
                ├──────────────────────────────────────────────────┤""");
    }


    private static int choiceNumber() {
        System.out.print("│    입력 > ");
        return Integer.parseInt(sc.nextLine().trim());
    }

    private static String inputEmail() {
        System.out.print("│    아이디 입력 : ");
        return sc.nextLine();
    }

    private static String inputPassword() {
        System.out.print("│    패스워드 입력 : ");
        return sc.nextLine();
    }

    private static String inputName() {
        System.out.print("│    이름 입력 : ");
        return sc.nextLine();
    }

}
