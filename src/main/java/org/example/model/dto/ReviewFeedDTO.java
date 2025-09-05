package org.example.model.dto;

import java.time.LocalDateTime;

/**
 * ReviewFeedDTO - 팔로잉한 사용자들의 리뷰를 보여주기 위한 DTO - JOIN 결과를 담는 용도로 사용
 */
public class ReviewFeedDTO {
	private String userName;
	private String movieName;
	private int rating;
	private int likeCount;
	private String content;
	private LocalDateTime regDate;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}
}
