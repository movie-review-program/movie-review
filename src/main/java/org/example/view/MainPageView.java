package org.example.view;

import java.util.List;
import java.util.Scanner;

import org.example.controller.MovieController;
import org.example.model.dto.Movie;
import org.example.model.dto.User;

public class MainPageView {
    private static final Scanner sc = new Scanner(System.in);
    /*
     * 1. 영화 정보 조회
     * 2. 리뷰 작성
     * 3. 팔로워 리뷰 보기
     * 4. 내 정보 보기
     * 5. 로그아웃 (if userId = null -> 안뜨게??)
     * */
    public static void menu(User user) {
        while (true) {
            try {
                int num = mainPageV(user);
                switch (num) {
                    case 1:
                        mainInfo();
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        LoginPageView.menu();
                    default:
                        System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
                }
            } catch (NumberFormatException e) {
                System.out.println("메뉴는 숫자만 가능합니다.");
            }
        }
    }

    static int mainPageV(User user) {
        outputMainPage(user.getName());
        return choiceNumber();
    }

    static void mainInfo() {
        MovieController.getMovieBasicInfo(1, 3);
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
                ├──────────────────────────────────────────────────┤""");;
    }

    private static void outputMovieInfo(List<Movie> movies, int page, int size) {
        System.out.println("""
                ┌──────────────────────────────────────────────────┐
                │                🎭 영화 목록                        │
                ├──────────────────────────────────────────────────┤
                """);
    }

    private static int choiceNumber() {
        System.out.print("│    입력 > ");
        return Integer.parseInt(sc.nextLine().trim());
    }

}
