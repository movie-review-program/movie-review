package org.example.model.dao;

import org.example.model.dto.User;

/**
 * UserDAO 인터페이스
 * - 사용자 데이터 접근 계층의 표준 규격 정의
 * - 메모리 저장소, DB 등 다양한 구현체로 교체 가능
 */
public interface UserDAO {
    
    /**
     * 사용자 추가
     * @param user - 추가할 User 객체
     * @return 추가 성공 시 true, 실패 시 false
     */
    boolean addUser(User user);
    
    /**
     * 이메일로 사용자 조회
     * @param email - 검색할 이메일
     * @return 이메일에 해당하는 User 객체, 없으면 null
     */
    User findByEmail(String email);
    
    /**
     * 이메일 존재 여부 확인
     * @param email - 확인할 이메일
     * @return 이미 존재하면 true, 없으면 false
     */
    boolean isEmailExist(String email);
}