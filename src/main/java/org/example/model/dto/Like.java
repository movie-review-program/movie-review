package org.example.model.dto;

public class Like {
	private long likeNo;
	private long userNo;
	private long reviewNo;
	
	public Like() {}

	public Like(long likeNo, long userNo, long reviewNo) {
		this.likeNo = likeNo;
		this.userNo = userNo;
		this.reviewNo = reviewNo;
	}

	public long getLikeNo() {
		return likeNo;
	}

	public void setLikeNo(long likeNo) {
		this.likeNo = likeNo;
	}

	public long getUserNo() {
		return userNo;
	}

	public void setUserNo(long userNo) {
		this.userNo = userNo;
	}

	public long getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(long reviewNo) {
		this.reviewNo = reviewNo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Like [likeNo=");
		builder.append(likeNo);
		builder.append(", userNo=");
		builder.append(userNo);
		builder.append(", reviewNo=");
		builder.append(reviewNo);
		builder.append("]");
		return builder.toString();
	}
}
