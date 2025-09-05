package org.example.model.dto;

public class Review {
	private int reviewNo;
	private int rating;
	private String content;
	private String regDate;
	private int userNo;	// writer 작성자
	private int movieNo;
	private int likeCnt;	// 좋아요 수

	public Review() {}

	/**
	 * 리뷰 작성 시 호출하는 생성자 (좋아요 수 미포함)
	 */
	public Review(int rating, String content, int userNo, int movieNo) {
		this.rating = rating;
		this.content = content;
		this.movieNo = movieNo;
		this.userNo = userNo;
	}
	
	/**
	 * 리뷰 조회 시 호출하는 생성자 (좋아요 수 포함)
	 */
	public Review(int reviewNo, int rating, String content, String regDate, int userNo, int movieNo, int likeCnt) {
		this(rating, content, userNo, movieNo);
		this.reviewNo = reviewNo;
		this.regDate = regDate;
		this.likeCnt = likeCnt;
	}
	
	/**
	 * 리뷰 요약 보기를 위한 getter
	 * 리뷰 글 내용의 최대 20자를 가져온다.
	 */
	public String getContentPreviw() {
		return this.content.substring(0,  Math.min(20, this.content.length()));
	}
	
	public int getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getMovieNo() {
		return movieNo;
	}

	public void setMovieNo(int movieNo) {
		this.movieNo = movieNo;
	}

	public int getUserNo() {
		return userNo;
	}
	
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}	
	
	public int getLikeCnt() {
		return likeCnt;
	}
	
	public void setLikeCnt(int likeCnt) {
		this.likeCnt = likeCnt;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Review [reviewNo=");
		builder.append(reviewNo);
		builder.append(", rating=");
		builder.append(rating);
		builder.append(", content=");
		builder.append(content);
		builder.append(", regDate=");
		builder.append(regDate);
		builder.append(", movieNo=");
		builder.append(movieNo);
		builder.append(", userNo=");
		builder.append(userNo);
		builder.append("]");
		return builder.toString();
	}
}
