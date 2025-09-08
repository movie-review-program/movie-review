package org.example.view;

import java.util.Scanner;

import org.example.common.ReviewContext;
import org.example.controller.UserController;
import org.example.model.dto.User;

public class MainPageView {
    private static final Scanner sc = new Scanner(System.in);

    /*
     * 1. 영화 정보 조회
     * 2. 리뷰 작성
     * 3. 팔로워 리뷰 보기
     * 4. 내 정보 보기
     * 5. 로그아웃
     * */
    public static void menu(User user) {
        while (true) {
                int num = mainPageV(user);
                if (user.getEmail() == null) {
                    try {
                        switch (num) {
                            case 1:
                                MoviePageView.menu(user);
                                return;
                            case 2:
                                LoginPageView.menu();
                                return;
                            default:
                                System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
                        }
                    }catch (NumberFormatException e) {
                        System.out.println("메뉴는 숫자만 가능합니다.");
                    }
                }else {
                    try {
                    switch (num) {
                        case 1:
                            MoviePageView.menu(user);
                            return;
                        case 2:
                            ReviewPageView.searchReview(user);
                            return;
                        case 3:
                            ReviewPageView.menu(user, ReviewContext.FOLLOW, user.getUserNo());
                            return;
                        case 4:
                            MyPageView.menu(user);
                            break;
                        case 5:
                            UserController.logout();
                            LoginPageView.menu();
                            return;
                        default:
                            System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
                    }
                    }catch (NumberFormatException e) {
                        System.out.println("메뉴는 숫자만 가능합니다.");
                    }
                }
        }
    }

    static int mainPageV(User user) {
        if (user.getEmail() == null) {
            outputNoneUserMainPage(user.getName());
        }else {
            outputMainPage(user.getName());
        }
        return choiceNumber();
    }

    private static void outputMainPage(String userName) {
        System.out.println("""
                ┌──────────────────────────────────────────────────┐
                │               🎬 영화 리뷰 시스템 🎬                 │""");
        System.out.printf("│  현재 사용자: %s 님%32s│%n", userName, "");
        System.out.println("""
                ├──────────────────────────────────────────────────┤
                │    1. 🎭 영화 정보 조회                             │
                │    2. ✍️  리뷰 작성                                │
                │    3. 👥 팔로워 리뷰 보기                            │
                │    4. 👤 내정보 보기                                │
                │    5. 로그아웃                                     │
                ├──────────────────────────────────────────────────┤""");
    }

    private static void outputNoneUserMainPage(String userName) {
        System.out.println("""
                ┌──────────────────────────────────────────────────┐
                │               🎬 영화 리뷰 시스템 🎬                 │""");
        System.out.printf("│  현재 사용자: %s 님%32s│%n", userName, "");
        System.out.println("""
                ├──────────────────────────────────────────────────┤
                │    1. 🎭 영화 정보 조회                             │
                │    2. 뒤로 가기                                    │
                ├──────────────────────────────────────────────────┤""");
    }

    private static int choiceNumber() {
        System.out.print("│    입력 > ");
        return Integer.parseInt(sc.nextLine().trim());
    }

}
