package org.example.model.service;

import org.example.model.dto.User;
import org.example.model.dto.ReviewFeedDTO;
import java.util.List;

/**
 * UserService
 * - 비즈니스 로직 계층 (Service Layer)
 * - Controller와 DAO 사이에서 동작
 * - 유효성 검사 및 비즈니스 규칙을 적용한 뒤 DAO를 호출
 */
public interface UserService {
    
    /**
     * 로그인
     * @param email 이메일
     * @param password 비밀번호
     * @return 로그인 성공 시 User 객체 반환
     * @throws Exception 이메일/비밀번호 유효성 실패, DB 오류
     */
    User login(String email, String password) throws Exception;

    /**
     * 회원가입 후 자동 로그인
     * @param user 가입할 사용자 정보
     * @return 가입 및 로그인된 User 객체
     * @throws Exception 이메일 중복, 비밀번호 유효성 실패, DB 오류
     */
    User registerAndLogin(User user) throws Exception;

    /**
     * 사용자 번호(user_no)로 사용자 조회
     * @param userNo 조회할 사용자 번호
     * @return User 객체
     * @throws Exception DB 오류
     */
    User getUserByUserNo(int userNo) throws Exception;

    /**
     * 팔로우 하기
     * @param followerNo 팔로우하는 사람 번호
     * @param followingNo 팔로우 대상 번호
     * @return true = 성공, false = 실패
     * @throws Exception 자기 자신 팔로우 시도, 중복 팔로우, DB 오류
     */
    boolean followUser(int followerNo, int followingNo) throws Exception;

    /**
     * 언팔로우 하기
     * @param followerNo 팔로우 취소하는 사람 번호
     * @param followingNo 언팔로우 대상 번호
     * @return true = 성공, false = 실패
     * @throws Exception DB 오류
     */
    boolean unfollowUser(int followerNo, int followingNo) throws Exception;

    /**
     * 특정 사용자를 팔로우 중인지 확인
     * @param followerNo 나(로그인한 사용자)
     * @param followingNo 대상 사용자
     * @return true = 팔로우 중, false = 팔로우 아님
     * @throws Exception DB 오류
     */
    boolean isFollowing(int followerNo, int followingNo) throws Exception;

    /**
     * 내가 팔로우한 사용자 목록 조회
     * @param followerNo 로그인한 사용자 번호
     * @return User 리스트
     * @throws Exception DB 오류
     */
    List<User> getFollowingList(int followerNo) throws Exception;

    /**
     * 내가 팔로우한 사용자들의 리뷰 최신순 조회
     * @param followerNo 로그인한 사용자 번호
     * @return ReviewFeedDTO 리스트 (작성자명, 영화명, 평점, 좋아요 수, 리뷰 내용, 작성일 포함)
     * @throws Exception DB 오류
     */
    List<ReviewFeedDTO> getFollowingReviews(int followerNo) throws Exception;
}
