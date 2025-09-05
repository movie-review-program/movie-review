package org.example.controller;

import org.example.model.dto.User;
import org.example.model.dto.ReviewFeedDTO;
import org.example.model.service.UserService;
import org.example.model.service.UserServiceImpl;
import org.example.util.SessionManagerImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * UserController
 * - View와 Service를 연결하는 역할
 * - 로그인/로그아웃 시 세션(SessionManagerImpl) 관리까지 포함
 */
public class UserController {

    private UserService userService = new UserServiceImpl();

    // ===== 로그인 / 회원가입 / 로그아웃 / 세션 =====
    public User login(String email, String password) {
        try {
            User user = userService.login(email, password);
            if (user != null) {
                SessionManagerImpl.getInstance().setLoggedInUser(user); // ✅ 세션 저장
            }
            return user;
        } catch (Exception e) {
            System.out.println("로그인 실패: " + e.getMessage());
            return null;
        }
    }

    public User registerAndLogin(User user) {
        try {
            User registered = userService.registerAndLogin(user);
            if (registered != null) {
                SessionManagerImpl.getInstance().setLoggedInUser(registered); // ✅ 세션 저장
            }
            return registered;
        } catch (Exception e) {
            System.out.println("회원가입 실패: " + e.getMessage());
            return null;
        }
    }

    public void logout() {
        SessionManagerImpl.getInstance().logout(); // ✅ 세션 초기화
    }

    public User getCurrentUser() {
        return SessionManagerImpl.getInstance().getLoggedInUser();
    }

    // ===== 사용자 조회 =====
    public User getUserByUserNo(int userNo) {
        try {
            return userService.getUserByUserNo(userNo);
        } catch (Exception e) {
            System.out.println("사용자 조회 실패: " + e.getMessage());
            return null;
        }
    }

    // ===== 팔로우 관련 =====
    public boolean followUser(int followerNo, int followingNo) {
        try {
            return userService.followUser(followerNo, followingNo);
        } catch (Exception e) {
            System.out.println("팔로우 실패: " + e.getMessage());
            return false;
        }
    }

    public boolean unfollowUser(int followerNo, int followingNo) {
        try {
            return userService.unfollowUser(followerNo, followingNo);
        } catch (Exception e) {
            System.out.println("언팔로우 실패: " + e.getMessage());
            return false;
        }
    }

    public boolean isFollowing(int followerNo, int followingNo) {
        try {
            return userService.isFollowing(followerNo, followingNo);
        } catch (Exception e) {
            System.out.println("팔로우 여부 확인 실패: " + e.getMessage());
            return false;
        }
    }

    public List<User> getFollowingList(int followerNo) {
        try {
            return userService.getFollowingList(followerNo);
        } catch (Exception e) {
            System.out.println("팔로우 목록 조회 실패: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // ===== 팔로잉 리뷰 조회 =====
    public List<ReviewFeedDTO> getFollowingReviews(int followerNo) {
        try {
            return userService.getFollowingReviews(followerNo);
        } catch (Exception e) {
            System.out.println("팔로잉 리뷰 조회 실패: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
