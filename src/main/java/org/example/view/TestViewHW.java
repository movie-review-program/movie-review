package org.example.view;

import java.util.List;
import java.util.Scanner;

import org.example.controller.UserController;
import org.example.model.dto.ReviewFeedDTO;
import org.example.model.dto.User;

/**
 * TestViewHW
 * - 콘솔 기반 View
 * - Controller 통해 세션 관리 및 기능 호출
 */
public class TestViewHW {
    private static Scanner sc = new Scanner(System.in);
    private static UserController userController = new UserController();

    public static void main(String[] args) {
        while (true) {
            User currentUser = userController.getCurrentUser(); // ✅ Controller에서 세션 조회

            if (currentUser == null) {
                System.out.println("\n===== 메뉴 =====");
                System.out.println("1. 회원가입");
                System.out.println("2. 로그인");
                System.out.println("0. 종료");
            } else {
                System.out.println("\n===== 로그인 : " + currentUser.getName() + " =====");
                System.out.println("3. 로그아웃");
                System.out.println("4. 내 정보 조회");
                System.out.println("5. 팔로우 하기");
                System.out.println("6. 언팔로우 하기");
                System.out.println("7. 팔로우 목록 보기");
                System.out.println("8. 팔로잉 리뷰 조회");
                System.out.println("0. 종료");
            }
            System.out.print("선택: ");

            int menu = sc.nextInt();
            if (currentUser == null) {
                switch (menu) {
                    case 1: register(); break;
                    case 2: login(); break;
                    case 0: System.out.println("종료합니다."); return;
                    default: System.out.println("잘못된 선택입니다.");
                }
            } else {
                switch (menu) {
                    case 3: logout(); break;
                    case 4: showMyInfo(); break;
                    case 5: followUser(); break;
                    case 6: unfollowUser(); break;
                    case 7: showFollowingList(); break;
                    case 8: showFollowingReviews(); break;
                    case 0: System.out.println("종료합니다."); return;
                    default: System.out.println("잘못된 선택입니다.");
                }
            }
        }
    }

    // ===== 회원가입 =====
    private static void register() {
        System.out.print("이메일: ");
        String email = sc.next();
        System.out.print("비밀번호: ");
        String password = sc.next();
        System.out.print("이름: ");
        String name = sc.next();

        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setName(name);

        User registeredUser = userController.registerAndLogin(newUser);
        if (registeredUser != null) {
            System.out.println("회원가입 성공 및 자동 로그인 완료!");
        } else {
            System.out.println("회원가입 실패");
        }
    }

    // ===== 로그인 =====
    private static void login() {
        System.out.print("이메일: ");
        String email = sc.next();
        System.out.print("비밀번호: ");
        String password = sc.next();

        User user = userController.login(email, password);
        if (user != null) {
            System.out.println(user.getName() + "님 로그인 성공!");
        } else {
            System.out.println("로그인 실패: 이메일 또는 비밀번호 확인");
        }
    }

    // ===== 로그아웃 =====
    private static void logout() {
        userController.logout();
        System.out.println("로그아웃 완료");
    }

    // ===== 내 정보 조회 =====
    private static void showMyInfo() {
        User currentUser = userController.getCurrentUser();
        if (currentUser == null) {
            System.out.println("로그인 후 이용 가능합니다.");
            return;
        }
        System.out.println("내 정보");
        System.out.println("번호: " + currentUser.getUserNo());
        System.out.println("이메일: " + currentUser.getEmail());
        System.out.println("이름: " + currentUser.getName());
        System.out.println("가입일: " + currentUser.getJoinDate());
    }

    // ===== 팔로우 하기 =====
    private static void followUser() {
        User currentUser = userController.getCurrentUser();
        System.out.print("팔로우할 유저 번호 입력: ");
        int followingNo = sc.nextInt();

        boolean result = userController.followUser(currentUser.getUserNo(), followingNo);
        System.out.println(result ? "팔로우 성공!" : "팔로우 실패!");
    }

    // ===== 언팔로우 하기 =====
    private static void unfollowUser() {
        User currentUser = userController.getCurrentUser();
        System.out.print("언팔로우할 유저 번호 입력: ");
        int followingNo = sc.nextInt();

        boolean result = userController.unfollowUser(currentUser.getUserNo(), followingNo);
        System.out.println(result ? "언팔로우 성공!" : "언팔로우 실패!");
    }

    // ===== 팔로우 목록 보기 (3명씩 페이지네이션) =====
    private static void showFollowingList() {
        User currentUser = userController.getCurrentUser();
        List<User> followingList = userController.getFollowingList(currentUser.getUserNo());
        if (followingList.isEmpty()) {
            System.out.println("팔로우한 사용자가 없습니다.");
            return;
        }

        int pageSize = 3;
        int currentPage = 0;

        while (true) {
            int start = currentPage * pageSize;
            int end = Math.min(start + pageSize, followingList.size());

            System.out.println("\n팔로우 목록 (페이지 " + (currentPage + 1) + ")");
            for (int i = start; i < end; i++) {
                User u = followingList.get(i);
                System.out.println("- " + u.getUserNo() + " | " + u.getName() +
                                   " (" + u.getEmail() + ")");
            }

            System.out.println("\n[n] 다음 페이지 | [p] 이전 페이지 | [q] 나가기");
            String cmd = sc.next();

            if (cmd.equalsIgnoreCase("n")) {
                if (end >= followingList.size()) {
                    System.out.println("마지막 페이지입니다.");
                } else {
                    currentPage++;
                }
            } else if (cmd.equalsIgnoreCase("p")) {
                if (currentPage == 0) {
                    System.out.println("첫 페이지입니다.");
                } else {
                    currentPage--;
                }
            } else if (cmd.equalsIgnoreCase("q")) {
                break;
            } else {
                System.out.println("잘못된 입력입니다.");
            }
        }
    }

    // ===== 팔로잉 리뷰 조회 (3개씩 페이지네이션) =====
    private static void showFollowingReviews() {
        User currentUser = userController.getCurrentUser();
        List<ReviewFeedDTO> feed = userController.getFollowingReviews(currentUser.getUserNo());
        if (feed.isEmpty()) {
            System.out.println("팔로잉한 사용자의 리뷰가 없습니다.");
            return;
        }

        int pageSize = 3;
        int currentPage = 0;

        while (true) {
            int start = currentPage * pageSize;
            int end = Math.min(start + pageSize, feed.size());

            System.out.println("\n팔로잉 리뷰 목록 (페이지 " + (currentPage + 1) + ")");
            for (int i = start; i < end; i++) {
                ReviewFeedDTO r = feed.get(i);

                // 리뷰 내용을 앞 20자까지만 표시
                String shortContent = r.getContent();
                if (shortContent != null && shortContent.length() > 20) {
                    shortContent = shortContent.substring(0, 20) + "...";
                }

                System.out.println("- 작성자: " + r.getUserName() +
                                   " | 영화: " + r.getMovieName() +
                                   " | 평점: " + r.getRating() +
                                   " | 좋아요: " + r.getLikeCount() +
                                   " | 리뷰: " + shortContent +
                                   " | 작성일: " + r.getRegDate());
            }

            System.out.println("\n[n] 다음 페이지 | [p] 이전 페이지 | [q] 나가기");
            String cmd = sc.next();

            if (cmd.equalsIgnoreCase("n")) {
                if (end >= feed.size()) {
                    System.out.println("마지막 페이지입니다.");
                } else {
                    currentPage++;
                }
            } else if (cmd.equalsIgnoreCase("p")) {
                if (currentPage == 0) {
                    System.out.println("첫 페이지입니다.");
                } else {
                    currentPage--;
                }
            } else if (cmd.equalsIgnoreCase("q")) {
                break;
            } else {
                System.out.println("잘못된 입력입니다.");
            }
        }
    }
}
