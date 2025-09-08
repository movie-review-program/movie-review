package org.example.model.dto;

import java.time.LocalDateTime;

/**
 * User DTO - users 테이블 및 관련 정보(팔로우/리뷰 수)를 담는 데이터 객체
 */
public class User {
	private int userNo;
	private String email;
	private String password;
	private String name;
	private LocalDateTime joinDate;
	private int reviewCnt;
	private int followerCnt;
	private int followingCnt;

	public User(String email, String password, String name) {
		this.email = email;
		this.password = password;
		this.name = name;
	}

	public User(int userNo, String email, String password, String name, LocalDateTime joinDate) {
		this.userNo = userNo;
		this.email = email;
		this.password = password;
		this.name = name;
		this.joinDate = joinDate;
	}

	public User(int userNo, String email, String password, String name, LocalDateTime joinDate, int reviewCnt,
			int followerCnt, int followingCnt) {
		this.userNo = userNo;
		this.email = email;
		this.password = password;
		this.name = name;
		this.joinDate = joinDate;
		this.reviewCnt = reviewCnt;
		this.followerCnt = followerCnt;
		this.followingCnt = followingCnt;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(LocalDateTime joinDate) {
		this.joinDate = joinDate;
	}

	public int getReviewCnt() {
		return reviewCnt;
	}

	public int getFollowerCnt() {
		return followerCnt;
	}

	public int getFollowingCnt() {
		return followingCnt;
	}
}
