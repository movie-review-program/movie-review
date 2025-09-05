package org.example.model.service;

import org.example.model.dao.UserDAO;
import org.example.model.dao.UserDAOImpl;
import org.example.model.dto.User;
import org.example.model.dto.ReviewFeedDTO;

import java.util.List;

/**
 * UserServiceImpl - UserService 인터페이스의 구현체 - 비즈니스 로직을 처리하고 DAO를 호출하여 결과 반환
 */
public class UserServiceImpl implements UserService {

	private UserDAO dao = new UserDAOImpl();

	/**
	 * 로그인 처리
	 * 
	 * @param email    이메일
	 * @param password 비밀번호
	 * @return 로그인 성공 시 User 객체
	 * @throws Exception 이메일/비밀번호 오류, DB 문제 등
	 */
	@Override
	public User login(String email, String password) throws Exception {
		if (email == null || email.trim().isEmpty()) {
			throw new Exception("이메일은 필수 입력 항목입니다.");
		}
		if (password == null || password.trim().isEmpty()) {
			throw new Exception("비밀번호는 필수 입력 항목입니다.");
		}

		User user = dao.selectUserByUseremailAndPassword(email, password);
		if (user == null) {
			throw new Exception("이메일 또는 비밀번호가 올바르지 않습니다.");
		}
		return user;
	}

	/**
	 * 회원가입 후 자동 로그인
	 * 
	 * @param user 가입할 사용자 정보
	 * @return 가입 후 로그인된 사용자 객체
	 * @throws Exception 이메일 중복, DB 오류 등
	 */
	@Override
	public User registerAndLogin(User user) throws Exception {
		if (user == null)
			throw new Exception("회원 정보가 비어있습니다.");
		if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
			throw new Exception("이메일은 필수 입력 항목입니다.");
		}
		if (user.getPassword() == null || user.getPassword().length() < 4) {
			throw new Exception("비밀번호는 4자리 이상이어야 합니다.");
		}
		if (dao.isEmailDuplicate(user.getEmail())) {
			throw new Exception("이미 사용 중인 이메일입니다.");
		}

		boolean success = dao.registerUser(user);
		if (!success) {
			throw new Exception("회원가입에 실패했습니다.");
		}

		return dao.selectUserByUseremailAndPassword(user.getEmail(), user.getPassword());
	}

	/**
	 * user_no 로 사용자 조회
	 * 
	 * @param userNo 사용자 번호
	 * @return User 객체
	 * @throws Exception 조회 실패 시
	 */
	@Override
	public User getUserByUserNo(int userNo) throws Exception {
		return dao.selectUserByUserNo(userNo);
	}

	/**
	 * 팔로우 하기
	 * 
	 * @param followerNo  팔로우하는 사람 번호
	 * @param followingNo 팔로우 대상 번호
	 * @return 성공 여부
	 * @throws Exception 자기 자신 팔로우, DB 오류
	 */
	@Override
	public boolean followUser(int followerNo, int followingNo) throws Exception {
		if (followerNo == followingNo) {
			throw new Exception("자기 자신은 팔로우할 수 없습니다.");
		}
		if (dao.isFollowing(followerNo, followingNo)) {
			throw new Exception("이미 팔로우 중입니다.");
		}
		return dao.followUser(followerNo, followingNo);
	}

	/**
	 * 언팔로우 하기
	 * 
	 * @param followerNo  팔로우 취소하는 사람 번호
	 * @param followingNo 언팔로우 대상 번호
	 * @return 성공 여부
	 * @throws Exception DB 오류
	 */
	@Override
	public boolean unfollowUser(int followerNo, int followingNo) throws Exception {
		return dao.unfollowUser(followerNo, followingNo);
	}

	/**
	 * 특정 대상 팔로우 여부 확인
	 * 
	 * @param followerNo  나
	 * @param followingNo 대상
	 * @return true = 팔로우 중, false = 팔로우 아님
	 * @throws Exception DB 오류
	 */
	@Override
	public boolean isFollowing(int followerNo, int followingNo) throws Exception {
		return dao.isFollowing(followerNo, followingNo);
	}

	/**
	 * 내가 팔로우한 사용자 목록
	 * 
	 * @param followerNo 나
	 * @return User 리스트
	 * @throws Exception DB 오류
	 */
	@Override
	public List<User> getFollowingList(int followerNo) throws Exception {
		return dao.getFollowingList(followerNo);
	}

	/**
	 * 내가 팔로우한 사용자들의 리뷰를 최신순으로 조회
	 * 
	 * @param followerNo 나
	 * @return ReviewFeedDTO 리스트
	 * @throws Exception DB 오류
	 */
	@Override
	public List<ReviewFeedDTO> getFollowingReviews(int followerNo) throws Exception {
		return dao.getFollowingReviews(followerNo);
	}
}
