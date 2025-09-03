package org.example.view;

import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * MovieReviewOutputUI
 * - 콘솔 화면 출력(Output)을 담당하는 클래스
 * - UI 텍스트, 메뉴, 화면 구성 등을 출력
 * - 화면 종류: 타이틀, 로그인, 메인 메뉴, 영화 목록, 리뷰 작성, 내 정보, 이메일/회원가입 등
 */
class MovieReviewOutputUI {

    /**
     * 화면 초기화 (clear)
     * - 운영체제에 따라 콘솔 화면을 지움
     * - Windows: cls, 그 외: clear
     * - 예외 발생 시 단순히 여러 줄을 출력하여 화면 초기화
     */
    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }

    /**
     * 타이틀 화면 출력
     */
    public static void showTitleScreen() {
        System.out.println("""
██████╗ ███████╗██╗   ██╗██╗███████╗██╗    ██╗
██╔══██╗██╔════╝██║   ██║██║██╔════╝██║    ██║
██████╔╝█████╗  ██║   ██║██║█████╗  ██║ █╗ ██║
██╔══██╗██╔══╝  ╚██╗ ██╔╝██║██╔══╝  ██║███╗██║
██║  ██║███████╗ ╚████╔╝ ██║███████╗╚███╔███╔╝
╚═╝  ╚═╝╚══════╝  ╚═══╝  ╚═╝╚══════╝ ╚══╝╚══╝ 
       🎬 MOVIE REVIEW SYSTEM 🎬
    """);
    }

    /**
     * 로그인 화면 출력
     */
    public static void showLoginScreen() {
        System.out.println("┌─────────────────────────────────────────────────┐");
        System.out.println("│                   로그인                          │");
        System.out.println("├─────────────────────────────────────────────────┤");
        System.out.println("│  번호를 입력하세요:                                  │");
        System.out.println("│  1. 로그인                                        │");
        System.out.println("│  2. 회원가입                                       │");
        System.out.println("│  3. 비회원으로 보기                                  │");
        System.out.println("│  0. 종료                                         │");
        System.out.println("└─────────────────────────────────────────────────┘");
    }

    /**
     * 메인 메뉴 화면 출력
     * @param userName 현재 로그인한 사용자 이름
     */
    public static void showMainMenu(String userName) {
        System.out.println("┌─────────────────────────────────────────────────┐");
        System.out.println("│         🎬 영화 리뷰 시스템 🎬                   │");
        System.out.println("│                                                 │");
        System.out.printf("│  현재 사용자: %s 님%20s│%n", userName, "");
        System.out.println("│                                                 │");
        System.out.println("├─────────────────────────────────────────────────┤");
        System.out.println("│  1. 🎭 영화 정보 조회                           │");
        System.out.println("│  2. ✍️  리뷰 작성                               │");
        System.out.println("│  3. 👥 팔로워 리뷰 보기                         │");
        System.out.println("│  4. 👤 내정보 보기                              │");
        System.out.println("│  5. 로그아웃                                    │");
        System.out.println("├─────────────────────────────────────────────────┤");
        System.out.println("│  선택하세요 (1-5): _                           │");
        System.out.println("└─────────────────────────────────────────────────┘");
    }

    /**
     * 영화 목록 출력
     * @param movies 영화 목록
     */
    public static void showMovieList(List<Movie> movies) {
        System.out.println("┌─────────────────────────────────────────────────┐");
        System.out.println("│                🎭 영화 목록                      │");
        System.out.println("├─────────────────────────────────────────────────┤");
        
        for (int i = 0; i < movies.size(); i++) {
            Movie movie = movies.get(i);
            System.out.printf("│  [%d] %s (%d)%15s│%n", i+1, movie.getTitle(), movie.getYear(), "");
            System.out.printf("│      감독: %s | 장르: %s%10s│%n", movie.getDirector(), movie.getGenre(), "");
            System.out.printf("│      ⭐ %.1f (리뷰 %d개)%15s│%n", movie.getAverageRating(), movie.getReviewCount(), "");
            System.out.println("│  ────────────────────────────────────────────   │");
        }
        
        System.out.println("├─────────────────────────────────────────────────┤");
        System.out.println("│  영화 번호 입력 | 0. 뒤로가기                   │");
        System.out.println("└─────────────────────────────────────────────────┘");
    }

    /**
     * 리뷰 작성 페이지 출력
     */
    public static void showWriteReviewPage() {
        System.out.println("┌─────────────────────────────────────────────────┐");
        System.out.println("│              ✍️ 리뷰 작성하기                    │");
        System.out.println("├─────────────────────────────────────────────────┤");
        System.out.println("│  아직 구현되지 않은 페이지입니다.               │");
        System.out.println("│  향후 다음 기능들이 추가될 예정입니다:          │");
        System.out.println("│  - 영화 검색                                    │");
        System.out.println("│  - 별점 입력                                    │");
        System.out.println("│  - 리뷰 내용 작성                               │");
        System.out.println("│  0. 뒤로가기                                    │");
        System.out.println("└─────────────────────────────────────────────────┘");
    }

    /**
     * 팔로워 리뷰 피드 출력
     */
    public static void showFollowerFeedPage() {
        System.out.println("┌─────────────────────────────────────────────────┐");
        System.out.println("│              👥 팔로워 리뷰 피드                 │");
        System.out.println("├─────────────────────────────────────────────────┤");
        System.out.println("│  아직 팔로워가 없습니다.                        │");
        System.out.println("│  다른 사용자를 팔로우하면 여기서                │");
        System.out.println("│  그들의 최신 리뷰를 볼 수 있습니다.             │");
        System.out.println("│  향후 추가될 기능:                              │");
        System.out.println("│  - 팔로워 리뷰 목록                             │");
        System.out.println("│  - 실시간 피드 업데이트                         │");
        System.out.println("│  0. 뒤로가기                                    │");
        System.out.println("└─────────────────────────────────────────────────┘");
    }

    /**
     * 내 정보 화면 출력
     * @param user User 객체
     */
    public static void showMyInfo(org.example.model.dto.User user) {
        System.out.println("┌─────────────────────────────────────────────────────────┐");
        System.out.println("│                👤 내 정보                                 │");
        System.out.println("├─────────────────────────────────────────────────────────┤");
        System.out.printf("│  🏷️  사용자명: %s%30s│%n", user.getName(), "");
        System.out.printf("│  📧 이메일: %s%25s│%n", user.getEmail(), "");
        
        // 가입일 포맷팅
        String joinDateStr = user.getJoinDate() != null ? 
            user.getJoinDate().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")) : "알 수 없음";
        System.out.printf("│  📅 가입일: %s%25s│%n", joinDateStr, "");
        System.out.println("│                                                         │");
        System.out.println("│  📊 활동 통계                                           │");
        System.out.printf(" │  ├─ 작성 리뷰: %d개%30s│%n", 0, "");
        System.out.printf(" │  ├─ 평균 평점: %.1f/5.0%25s│%n", 0.0, "");
        System.out.printf(" │  ├─ 팔로잉: %d명%30s│%n", 0, "");
        System.out.printf(" │  └─ 팔로워: %d명%30s│%n", 0, "");
        System.out.println("│                                                         │");
        System.out.println("├─────────────────────────────────────────────────────────┤");
        System.out.println("│  1. 작성한 리뷰보기 | 2. 팔로우 목록보기                        │");
        System.out.println("│  3. 좋아요 목록보기 | 4. 프로필 수정 | 0. 뒤로                  │");
        System.out.println("└─────────────────────────────────────────────────────────┘");
    }

    /**
     * 이메일 입력 프롬프트 출력
     */
    public static void showEmailPrompt() {
        System.out.println("├─────────────────────────────────────────────────┤");
        System.out.println("│  1단계: 이메일 입력                                 │");
        System.out.println("│  • 유효한 이메일 주소를 입력해주세요                     │");
        System.out.print("│  이메일:");
    }

    /**
     * 비밀번호 입력 프롬프트 출력
     */
    public static void showPasswordPrompt() {
        System.out.println("├─────────────────────────────────────────────────┤");
        System.out.println("│  2단계: 비밀번호 설정                                │");
        System.out.println("│  • 8자 이상 입력해주세요                             │");
        System.out.print("│  비밀번호: ");
    }

    /**
     * 비밀번호 확인 입력 프롬프트
     */
    public static void showPasswordConfirmPrompt() {
        System.out.print("│  비밀번호 확인: ");
    }

    /**
     * 이름 입력 프롬프트
     */
    public static void showNamePrompt() {
        System.out.println("├─────────────────────────────────────────────────┤");
        System.out.println("│  3단계: 이름 입력                                   │");
        System.out.print("│  이름: ");
    }

    /**
     * 이메일 인증 코드 입력 프롬프트
     * @param email 사용자 이메일
     */
    public static void showEmailVerificationPrompt(String email) {
        System.out.println("├─────────────────────────────────────────────────┤");
        System.out.printf("│  %s 로 인증코드를 발송했습니다.%10s│%n", 
            email.length() > 30 ? email.substring(0, 30) + "..." : email, "");
        System.out.print("│  인증코드: ");
    }

    /**
     * 가입 정보 요약 화면
     */
    public static void showSignupSummary(String username, String name, String email) {
       System.out.println("├─────────────────────────────────────────────────┤");
        System.out.printf("│  아이디: %-30s            │%n", username);
        System.out.printf("│  이름: %-31s           │%n", name);
        System.out.printf("│  이메일: %-29s            │%n", email.length() > 25 ? email.substring(0, 25) + "..." : email);
       System.out.println("│  Y, N, E 중에서 선택해주세요."); 
       System.out.println("├─────────────────────────────────────────────────┤");
    }

    /**
     * 인증코드 발송 메시지
     */
    public static void showEmailSending() {
        System.out.println("│  📧 인증코드를 발송하고 있습니다...              │");
    }

    /**
     * 인증코드 발송 완료 메시지
     */
    public static void showEmailSent() {
        System.out.println("│  ✅ 인증코드가 발송되었습니다!                   │");
    }

    /**
     * 이메일 인증 성공 메시지
     */
    public static void showVerificationSuccess() {
        System.out.println("│  ✅ 이메일 인증이 완료되었습니다!                │");
        System.out.println("└─────────────────────────────────────────────────┘");
    }

    /**
     * 회원가입 완료 화면
     */
    public static void showSignupComplete(String username) {
        System.out.println("┌─────────────────────────────────────────────────┐");
        System.out.printf("│  %s님, 환영합니다!%20s│%n", username, "");
    }

    /**
     * 로그인 ID 입력 프롬프트
     */
    public static void showLoginIdPrompt() {
        System.out.print("│  이메일: ");
    }

    /**
     * 로그인 비밀번호 입력 프롬프트
     */
    public static void showLoginPasswordPrompt() {
        System.out.print("│  비밀번호: ");
    }

    /**
     * 성공 메시지 출력
     */
    public static void showSuccessMessage(String message) {
        System.out.println("\n✅ " + message);
    }

    /**
     * 에러 메시지 출력
     */
    public static void showErrorMessage(String message) {
        System.out.println("\n❌ " + message);
    }

    /**
     * Enter 대기 메시지 출력
     */
    public static void showWaitMessage() {
        System.out.println("\n계속하려면 Enter를 누르세요...");
    }
}
