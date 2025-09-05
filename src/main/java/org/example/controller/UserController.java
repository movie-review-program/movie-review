package org.example.controller;

import org.example.model.dto.User;
import org.example.model.dto.ReviewFeedDTO;
import org.example.model.service.UserService;
import org.example.model.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * UserController - View와 Service를 연결하는 역할 - View에서 들어온 요청을 Service로 전달하고 결과를 반환
 */
public class UserController {

	private UserService userService = new UserServiceImpl();

	/**
	 * 로그인
	 * 
	 * @param email    이메일
	 * @param password 비밀번호
	 * @return 로그인 성공 시 User 객체, 실패 시 null
	 */
	public User login(String email, String password) {
		try {
			return userService.login(email, password);
		} catch (Exception e) {
			System.out.println("로그인 실패: " + e.getMessage());
			return null;
		}
	}

	/**
	 * 회원가입 후 자동 로그인
	 * 
	 * @param user 가입할 User 객체
	 * @return 가입 및 로그인된 User 객체
	 */
	public User registerAndLogin(User user) {
		try {
			return userService.registerAndLogin(user);
		} catch (Exception e) {
			System.out.println("회원가입 실패: " + e.getMessage());
			return null;
		}
	}

	/**
	 * user_no 로 사용자 조회
	 * 
	 * @param userNo 사용자 번호
	 * @return User 객체
	 */
	public User getUserByUserNo(int userNo) {
		try {
			return userService.getUserByUserNo(userNo);
		} catch (Exception e) {
			System.out.println("사용자 조회 실패: " + e.getMessage());
			return null;
		}
	}

	/**
	 * 팔로우 하기
	 * 
	 * @param followerNo  팔로우 하는 사람 번호
	 * @param followingNo 팔로우 대상 번호
	 * @return 성공 여부
	 */
	public boolean followUser(int followerNo, int followingNo) {
		try {
			return userService.followUser(followerNo, followingNo);
		} catch (Exception e) {
			System.out.println("팔로우 실패: " + e.getMessage());
			return false;
		}
	}

	/**
	 * 언팔로우 하기
	 * 
	 * @param followerNo  팔로우 취소하는 사람 번호
	 * @param followingNo 팔로우 대상 번호
	 * @return 성공 여부
	 */
	public boolean unfollowUser(int followerNo, int followingNo) {
		try {
			return userService.unfollowUser(followerNo, followingNo);
		} catch (Exception e) {
			System.out.println("언팔로우 실패: " + e.getMessage());
			return false;
		}
	}

	/**
	 * 특정 유저를 내가 팔로우 중인지 확인
	 * 
	 * @param followerNo  로그인한 사용자 번호
	 * @param followingNo 대상 사용자 번호
	 * @return true = 팔로우 중, false = 팔로우 안 함
	 */
	public boolean isFollowing(int followerNo, int followingNo) {
		try {
			return userService.isFollowing(followerNo, followingNo);
		} catch (Exception e) {
			System.out.println("팔로우 여부 확인 실패: " + e.getMessage());
			return false;
		}
	}

	/**
	 * 내가 팔로우한 사용자 목록 조회
	 * 
	 * @param followerNo 로그인한 사용자 번호
	 * @return User 리스트
	 */
	public List<User> getFollowingList(int followerNo) {
		try {
			return userService.getFollowingList(followerNo);
		} catch (Exception e) {
			System.out.println("팔로우 목록 조회 실패: " + e.getMessage());
			return new ArrayList<>();
		}
	}

	/**
	 * 내가 팔로우한 사용자들의 리뷰를 최신순으로 조회
	 * 
	 * @param followerNo 로그인한 사용자 번호
	 * @return ReviewFeedDTO 리스트
	 */
	public List<ReviewFeedDTO> getFollowingReviews(int followerNo) {
		try {
			return userService.getFollowingReviews(followerNo);
		} catch (Exception e) {
			System.out.println("팔로잉 리뷰 조회 실패: " + e.getMessage());
			return new ArrayList<>();
		}
	}
}
