package org.example.controller;

import org.example.model.dto.User;
import org.example.model.service.UserService;
import org.example.model.service.UserServiceImpl;
import org.example.util.SessionManager;
import org.example.util.SessionManagerImpl;
import org.example.view.MainPageView;
import org.example.view.MyPageView;
import org.example.view.ReviewPageView;

/**
 * UserController
 * - View와 Service를 연결하는 역할
 * - 로그인/로그아웃 시 세션(SessionManagerImpl) 관리까지 포함
 */
public class UserController {
    private static final UserService userService = UserServiceImpl.getInstance();
    private static final SessionManager sm = SessionManagerImpl.getInstance();

    public static void login(String email, String password) {
        try {
            User user = userService.login(email, password);
            sm.setLoggedInUser(user);
            MainPageView.menu(user);
        } catch (Exception e) {
            System.out.println("로그인 실패: " + e.getMessage());
        }
    }

    public static void registerAndLogin(String email, String password, String name) {
        try {
            User user = userService.registerAndLogin(email, password, name);
            sm.setLoggedInUser(user);
            MainPageView.menu(user);
        } catch (Exception e) {
            System.out.println("회원가입 실패: " + e.getMessage());
        }
    }

    public static void getUserByUserNo(int userNo) {
        try {
            MyPageView.outputMovieInfo(userService.getUserByUserNo(userNo));
        } catch (Exception e) {
            System.out.println("사용자 조회 실패: " + e.getMessage());
        }
    }

    public static void getFollowersInfo(int userNo, int page, int size) {
        try {
            MyPageView.outputFollowsInfo(userService.getFollowersInfo(userNo, page, size));
        } catch (Exception e) {
            System.out.println("팔로워 정보를 찾아올 수 없습니다.: " + e.getMessage());
        }
    }


    public static void logout() {
        SessionManagerImpl.getInstance().logout();
    }

    public static void followUser(int followerNo, int followingNo) {
        try {
        	if (userService.isFollowing(followerNo, followingNo))
        		userService.unfollowUser(followerNo, followingNo);
        	else 
        		userService.followUser(followerNo, followingNo);
        } catch (Exception e) {
            System.out.println("팔로우 실패: " + e.getMessage());
        }
    }

    public static void unfollowUser(int followerNo, int followingNo) {
        try {
            userService.unfollowUser(followerNo, followingNo);
        } catch (Exception e) {
            System.out.println("언팔로우 실패: " + e.getMessage());
        }
    }

    public static void isFollowing(int followerNo, int followingNo) {
        try {
            if (userService.isFollowing(followerNo, followingNo))
            	ReviewPageView.outputReviewUnfollowFoot();
            else 
            	ReviewPageView.outputReviewFollowFoot();
        } catch (Exception e) {
            System.out.println("팔로우 여부 확인 실패: " + e.getMessage());
        }
    }

    //TODO: userSession 인증
    private User getCurrentUser() {
        return SessionManagerImpl.getInstance().getLoggedInUser();
    }
}
