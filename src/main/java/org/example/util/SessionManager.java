package org.example.util;

import org.example.model.dto.User;

/**
 * SessionManager 인터페이스 - 로그인한 사용자(Session)를 관리하는 역할 - 프로그램 실행 동안 단일 사용자 세션을 유지
 * - View 계층에서 로그인 상태를 확인하거나 해제할 때 사용
 */
public interface SessionManager {

	/**
	 * 로그인한 사용자 정보를 세션에 저장
	 * 
	 * @param user 로그인한 사용자 객체
	 */
	void setLoggedInUser(User user);

	/**
	 * 현재 세션에 저장된 로그인 사용자 반환
	 * 
	 * @return 현재 로그인한 사용자 객체 (로그아웃 상태일 경우 null)
	 */
	User getLoggedInUser();

	/**
	 * 로그아웃 처리 - 세션에 저장된 로그인 사용자 정보를 제거
	 */
	void logout();
}
