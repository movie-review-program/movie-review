package org.example.model.dto;

public class Review {
	private long reviewNo;
	private int rating;
	private String content;
	private String regDate;
	private long movieNo;
	private long userNo;	// writer 작성자
	
	public Review() {}

	public Review(int rating, String content, long movieNo, long userNo) {
		this.rating = rating;
		this.content = content;
		this.movieNo = movieNo;
		this.userNo = userNo;
	}

	public Review(long reviewNo, int rating, String content, String regDate, long movieNo, long userNo) {
		this(rating, content, movieNo, userNo);
		this.reviewNo = reviewNo;
		this.regDate = regDate;
	}

	public long getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(long reviewNo) {
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

	public long getMovieNo() {
		return movieNo;
	}

	public void setMovieNo(long movieNo) {
		this.movieNo = movieNo;
	}

	public long getUserNo() {
		return userNo;
	}
	
	public void setUserNo(long userNo) {
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
