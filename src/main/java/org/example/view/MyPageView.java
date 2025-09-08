package org.example.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.example.common.ReviewContext;
import org.example.controller.UserController;
import org.example.model.dto.User;

//TODO: 다음 페이지가 존재하지 않을 때를 관리
public class MyPageView {
    private static final Scanner sc = new Scanner(System.in);
    private static int page = 1;
    private static final int size = 3;

    /*
     * fallowers 상태 관리
     * */
    private static List<User> usersStorge;

    /*
     * 1. 작성한 리뷰보기
     * 2. 팔로우 목록 보기
     * 3. 좋아요 목록 보기
     * 3. 뒤로가기
     * */
    public static void menu(User user) {
        while (true) {
            try {
                int num = myPageInfo(user);
                switch (num) {
                    case 1:
                        ReviewPageView.menu(user, ReviewContext.USER, user.getUserNo());
                        return;
                    case 2:
                        myPageFollowsInfo(user);
                        return;
                    case 3:
                        ReviewPageView.menu(user, ReviewContext.LIKE, user.getUserNo());
                        return;
                    case 4:
                        MainPageView.menu(user);
                        return;
                    default:
                        System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
                }
            } catch (NumberFormatException e) {
                System.out.println("메뉴는 숫자만 가능합니다.");
            }
        }
    }

    static int myPageInfo(User user) {
        outputMovieInfoHead();
        UserController.getUserByUserNo(user.getUserNo());
        outputMovieInfoFoot();
        return choiceNumber();
    }

    /*
     * 1. 팔로우 삭제 하기
     * 2. 다음으로
     * 3. 뒤로
     * */
    static void myPageFollowsInfo(User user) {

        outputFollowsInfoHead();
        UserController.getFollowersInfo(user.getUserNo(), page, size);
        outputFollowsInfoFoot();

        while (true) {
            try {
                int num = choiceNumber();
                switch (num) {
                    case 1:
                        outputFollowsInfoHead();
                        UserController.getFollowersInfo(user.getUserNo(), page, size);
                        outputDeleteFollows();
                        int c = choiceNumber();
                        int findId = c - ((page - 1) * size);
                        UserController.unfollowUser(
                                usersStorge.get(findId).getUserNo(),
                                user.getUserNo()
                        );
                        myPageFollowsInfo(user);
                        page = 1;
                        return;
                    case 2:
                        ++page;
                        myPageFollowsInfo(user);
                        return;
                    case 3:
                        page = 1;
                        menu(user);
                        return;
                    default:
                        System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
                }
            } catch (NumberFormatException e) {
                System.out.println("메뉴는 숫자만 가능합니다.");
            }
        }
    }

    private static void outputMovieInfoHead() {
        System.out.println("""
                ┌──────────────────────────────────────────────────┐
                │                👤 내 정보                          │
                ├──────────────────────────────────────────────────┤""");
    }

    public static void outputMovieInfo(User user) {
        System.out.printf("│    사용자명: %s %35s%n",
                user.getName(),
                ""
        );
        System.out.printf("│    이메일: %s %35s%n",
                user.getEmail(),
                ""
        );
        System.out.printf("│    가입일: %s %35s%n",
                user.getJoinDate().toLocalDate(),
                ""
        );

        System.out.println("│    활동 통계");
        System.out.printf("│      작성 리뷰: %s %35s%n",
                user.getReviewCnt(),
                ""
        );
        System.out.printf("│      팔로잉: %s %33s%n",
                user.getFollowingCnt(),
                ""
        );
        System.out.printf("│      팔로워: %s %33s%n",
                user.getFollowerCnt(),
                ""
        );
        System.out.println();
    }

    private static void outputMovieInfoFoot() {
        System.out.println("""
                ├──────────────────────────────────────────────────┤
                │    1. 작성한 리뷰 보기                               │
                │    2. 팔로우 목록 보기                               │
                │    3. 좋아요 목록 보기                               │
                │    4. 뒤로 가기                                    │
                ├──────────────────────────────────────────────────┤""");
    }

    private static void outputFollowsInfoHead() {
        System.out.println("""
                ┌──────────────────────────────────────────────────┐
                │                👤 팔로우 목록                       │
                ├──────────────────────────────────────────────────┤""");
    }

    public static void outputFollowsInfo(List<User> users) {
        usersStorge = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            int num = (page - 1) * size;
            System.out.printf("│  [%d] %s%15s%n",
                    num + i + 1,
                    users.get(i).getName(),
                    ""
            );
            System.out.printf("│    리뷰: %d  │  팔로워: %d%15s%n",
                    users.get(i).getReviewCnt(),
                    users.get(i).getFollowerCnt(),
                    ""
            );
            usersStorge.add(users.get(i));
        }
    }

    private static void outputFollowsInfoFoot() {
        System.out.println("""
                ├──────────────────────────────────────────────────┤
                │    1. 팔로우 삭제 하기                               │
                │    2. 다음 페이지                                   │
                │    3. 뒤로 가기                                    │
                ├──────────────────────────────────────────────────┤""");
    }

    private static void outputDeleteFollows() {
        System.out.println("""
                ├──────────────────────────────────────────────────┤
                │    1. 어떤 팔로우를 삭제하시겠습니다? 번호를 입력해 주세요    │
                ├──────────────────────────────────────────────────┤""");
    }

    private static int choiceNumber() {
        System.out.print("│    입력 > ");
        return Integer.parseInt(sc.nextLine().trim());
    }
}
