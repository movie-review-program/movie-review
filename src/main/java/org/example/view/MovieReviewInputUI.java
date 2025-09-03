package org.example.view;

import java.util.Scanner;

/**
 * MovieReviewInputUI
 * - 사용자 입력(Input)을 처리하는 클래스
 * - 콘솔에서 입력받은 값을 컨트롤러로 전달
 */
class MovieReviewInputUI {
    private Scanner scanner; // 입력 스캐너 객체

    // 생성자: Scanner 초기화
    public MovieReviewInputUI() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * 로그인 화면에서 선택된 메뉴 번호 반환
     * 예: 0(종료), 1(로그인), 2(회원가입), 3(비회원)
     */
    public String getLoginChoice() {
        System.out.print("  선택하세요 (0-3): ");
        return scanner.nextLine().trim();
    }

    /**
     * 메인 메뉴에서 선택된 번호 반환
     * 예: 1(영화 조회), 2(리뷰 작성), 3(팔로워 리뷰), 4(내정보), 5(로그아웃)
     */
    public String getMainMenuChoice() {
        System.out.print("  선택1: ");
        return scanner.nextLine().trim();
    }

    /**
     * 영화 목록에서 선택된 영화 번호 반환
     */
    public String getMovieChoice() {
        System.out.print("  선택2: ");
        return scanner.nextLine().trim();
    }

    /**
     * 내 정보 메뉴에서 선택된 항목 번호 반환
     */
    public String getMyInfoChoice() {
        System.out.print("  선택3: ");
        return scanner.nextLine().trim();
    }

    /**
     * 일반적인 선택 입력 (기타 화면에서 사용)
     */
    public String getGeneralChoice() {
        System.out.print(" 선택4: ");
        return scanner.nextLine().trim();
    }

    /**
     * 엔터 대기용
     * - 사용자가 Enter를 누를 때까지 대기
     */
    public void waitForEnter() {
        scanner.nextLine();
    }
}
