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
	private static final UserService instance = new UserServiceImpl();
	private UserDAO dao = new UserDAOImpl();

	public static UserService getInstance() {
		return instance;
	}

	@Override
	public User login(String email, String password) throws Exception {
		User user = dao.selectUserByUseremailAndPassword(email, password);
		if (user == null) {
			throw new Exception("이메일 또는 비밀번호가 올바르지 않습니다.");
		}
		return user;
	}

	@Override
	public User registerAndLogin(String email, String password, String name) throws Exception {
		validateRegister();

		int result = dao.registerUser(email, password, name);
		if (result != 1) {
			throw new Exception("회원가입에 실패했습니다.");
		}

        return login(email, password);
	}

	@Override
	public User getUserByUserNo(int userNo) throws Exception {
		return dao.selectUserByUserNo(userNo);
	}

	@Override
	public List<User> getFollowersInfo(int userNo, int page, int size) throws Exception {
		List<User> users = dao.selectFollowers(userNo, page, size);
		if(users.isEmpty())throw new Exception("데이터를 올바르게 전달하지 못했습니다");
		return users;
	}

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

	@Override
	public boolean unfollowUser(int followerNo, int followingNo) throws Exception {
		return dao.unfollowUser(followerNo, followingNo);
	}

	@Override
	public boolean isFollowing(int followerNo, int followingNo) throws Exception {
		return dao.isFollowing(followerNo, followingNo);
	}

	@Override
	public List<User> getFollowingList(int followerNo) throws Exception {
		return dao.getFollowingList(followerNo);
	}

	@Override
	public List<ReviewFeedDTO> getFollowingReviews(int followerNo) throws Exception {
		return dao.getFollowingReviews(followerNo);
	}

	//TODO: validationEmailAndPassword
	private void validateRegister() {

	}
}
