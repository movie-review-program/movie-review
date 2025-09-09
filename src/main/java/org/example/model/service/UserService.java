package org.example.model.service;

import org.example.model.dto.User;
import java.util.List;

/**
 * UserService
 * - 비즈니스 로직 계층 (Service Layer)
 * - Controller와 DAO 사이에서 동작
 * - 유효성 검사 및 비즈니스 규칙을 적용한 뒤 DAO를 호출
 */
public interface UserService {

    User login(String email, String password) throws Exception;

    User registerAndLogin(String email, String password, String name) throws Exception;

    User getUserByUserNo(int userNo) throws Exception;

    List<User> getFollowersInfo(int userNo, int page, int size) throws Exception;

    boolean followUser(int followerNo, int followingNo) throws Exception;

    boolean unfollowUser(int followerNo, int followingNo) throws Exception;

    boolean isFollowing(int followerNo, int followingNo) throws Exception;

}
