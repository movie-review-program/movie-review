package org.example.model.dao;

import org.example.model.dto.User;
import org.example.model.dto.ReviewFeedDTO;
import java.sql.SQLException;
import java.util.List;

/**
 * UserDAO - 사용자(User) 및 팔로우/팔로잉 관련 DB 접근 인터페이스 - DAO 패턴: SQL 실행을 담당하고,
 * Service/Controller에서 호출
 */
public interface UserDAO {

	/**
	 * 이메일 중복 여부 확인
	 * 
	 * @param email 확인할 이메일
	 * @return true = 중복, false = 사용 가능
	 * @throws SQLException DB 오류
	 */
	boolean isEmailDuplicate(String email) throws SQLException;

	int registerUser(String email, String password, String name) throws SQLException;

	/**
	 * 이메일 + 비밀번호로 사용자 조회 (로그인용)
	 * 
	 * @param email    이메일
	 * @param password 비밀번호
	 * @return User 객체, 없으면 null
	 * @throws SQLException DB 오류
	 */
	User selectUserByUseremailAndPassword(String email, String password) throws SQLException;

	/**
	 * user_no로 사용자 조회
	 * 
	 * @param userNo 사용자 번호
	 * @return User 객체
	 * @throws SQLException DB 오류
	 */
	User selectUserByUserNo(int userNo) throws SQLException;

	/**
	 * 팔로우 하기
	 * 
	 * @param followerNo  팔로우하는 사람 번호
	 * @param followingNo 팔로우 대상 번호
	 * @return true = 성공, false = 실패
	 * @throws Exception DB 오류
	 */
	boolean followUser(int followerNo, int followingNo) throws Exception;

	/**
	 * 언팔로우 하기
	 * 
	 * @param followerNo  팔로우 취소하는 사람 번호
	 * @param followingNo 팔로우 대상 번호
	 * @return true = 성공, false = 실패
	 * @throws Exception DB 오류
	 */
	boolean unfollowUser(int followerNo, int followingNo) throws Exception;

	/**
	 * 팔로우 여부 확인
	 * 
	 * @param followerNo  로그인한 사용자 번호
	 * @param followingNo 팔로우 대상 번호
	 * @return true = 팔로우 중, false = 팔로우 아님
	 * @throws Exception DB 오류
	 */
	boolean isFollowing(int followerNo, int followingNo) throws Exception;

	/**
	 * 내가 팔로우한 사용자 목록 조회
	 * 
	 * @param followerNo 로그인한 사용자 번호
	 * @return User 리스트
	 * @throws Exception DB 오류
	 */
	List<User> getFollowingList(int followerNo) throws Exception;

	/**
	 * 내가 팔로우한 사용자들의 리뷰 최신순 조회
	 * 
	 * @param followerNo 로그인한 사용자 번호
	 * @return ReviewFeedDTO 리스트 (작성자명, 영화명, 평점, 좋아요 수, 리뷰 내용, 작성일 포함)
	 * @throws Exception DB 오류
	 */
	List<ReviewFeedDTO> getFollowingReviews(int followerNo) throws Exception;
}
