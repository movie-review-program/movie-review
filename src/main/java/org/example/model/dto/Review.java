package org.example.model.dto;

public class Review {
	private int reviewNo;
	private int rating;
	private String content;
	private String regDate;
	private int movieNo;
	private int userNo;	// writer 작성자
	
	public Review() {}

	public Review(int reviewNo, int rating, String content, String regDate, int movieNo, int userNo) {
		super();
		this.reviewNo = reviewNo;
		this.rating = rating;
		this.content = content;
		this.regDate = regDate;
		this.movieNo = movieNo;
		this.userNo = userNo;
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
