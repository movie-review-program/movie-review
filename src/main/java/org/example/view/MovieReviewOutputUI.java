package org.example.view;
import java.util.*;

//============== OUTPUT UI 클래스 ==============
class MovieReviewOutputUI {
 
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
 
 public static void showLoginScreen() {
     System.out.println("┌─────────────────────────────────────────────────┐");
     System.out.println("│                   로그인                          │");
     System.out.println("├─────────────────────────────────────────────────┤");
     System.out.println("│  번호를 입력하세요:                                  │");
     System.out.println("│                                                 │");
     System.out.println("│  1. 로그인                                        │");
     System.out.println("│  2. 회원가입                                       │");
     System.out.println("│  3. 비회원으로 보기                                  │");
     System.out.println("│  0. 종료                                         │");
     System.out.println("└─────────────────────────────────────────────────┘");
 }
 
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
 
 public static void showWriteReviewPage() {
     System.out.println("┌─────────────────────────────────────────────────┐");
     System.out.println("│              ✍️ 리뷰 작성하기                    │");
     System.out.println("├─────────────────────────────────────────────────┤");
     System.out.println("│  아직 구현되지 않은 페이지입니다.               │");
     System.out.println("│                                                 │");
     System.out.println("│  향후 다음 기능들이 추가될 예정입니다:          │");
     System.out.println("│  - 영화 검색                                    │");
     System.out.println("│  - 별점 입력                                    │");
     System.out.println("│  - 리뷰 내용 작성                               │");
     System.out.println("│                                                 │");
     System.out.println("├─────────────────────────────────────────────────┤");
     System.out.println("│  0. 뒤로가기                                    │");
     System.out.println("└─────────────────────────────────────────────────┘");
 }
 
 public static void showFollowerFeedPage() {
     System.out.println("┌─────────────────────────────────────────────────┐");
     System.out.println("│              👥 팔로워 리뷰 피드                 │");
     System.out.println("├─────────────────────────────────────────────────┤");
     System.out.println("│  아직 팔로워가 없습니다.                        │");
     System.out.println("│                                                 │");
     System.out.println("│  다른 사용자를 팔로우하면 여기서                │");
     System.out.println("│  그들의 최신 리뷰를 볼 수 있습니다.             │");
     System.out.println("│                                                 │");
     System.out.println("│  향후 추가될 기능:                              │");
     System.out.println("│  - 팔로워 리뷰 목록                             │");
     System.out.println("│  - 실시간 피드 업데이트                         │");
     System.out.println("│                                                 │");
     System.out.println("├─────────────────────────────────────────────────┤");
     System.out.println("│  0. 뒤로가기                                    │");
     System.out.println("└─────────────────────────────────────────────────┘");
 }
 
 public static void showMyInfo(User user) {
     System.out.println("┌─────────────────────────────────────────────────────────┐");
     System.out.println("│                👤 내 정보                               │");
     System.out.println("├─────────────────────────────────────────────────────────┤");
     System.out.printf("│  🏷️  사용자명: %s (@%s)%15s│%n", user.getName(), user.getUsername(), "");
     System.out.printf("│  📧 이메일: %s%25s│%n", user.getEmail(), "");
     System.out.printf("│  📅 가입일: %s%25s│%n", user.getJoinDate(), "");
     System.out.println("│                                                         │");
     System.out.println("│  📊 활동 통계                                           │");
     System.out.printf("│  ├─ 작성 리뷰: %d개%30s│%n", user.getReviews().size(), "");
     
     double avgRating = user.getAverageRating();
     System.out.printf("│  ├─ 평균 평점: %.1f/5.0%25s│%n", avgRating, "");
     System.out.printf("│  ├─ 팔로잉: %d명%30s│%n", user.getFollowing().size(), "");
     System.out.printf("│  └─ 팔로워: %d명%30s│%n", user.getFollowers().size(), "");
     System.out.println("│                                                         │");
     System.out.println("├─────────────────────────────────────────────────────────┤");
     System.out.println("│  1. 작성한 리뷰보기 | 2. 팔로우 목록보기                 │");
     System.out.println("│  3. 좋아요 목록보기 | 4. 프로필 수정 | 0. 뒤로          │");
     System.out.println("└─────────────────────────────────────────────────────────┘");
 }
 
 public static void showSignupPage() {
     System.out.println("┌─────────────────────────────────────────────────┐");
     System.out.println("│                  회원가입                       │");
     System.out.println("├─────────────────────────────────────────────────┤");
     System.out.println("│  새로운 계정을 만들어주세요                     │");
     System.out.println("│                                                 │");
     System.out.println("│  모든 항목을 입력해주세요:                      │");
     System.out.println("└─────────────────────────────────────────────────┘");
 }
 
 public static void showUsernamePrompt() {
     System.out.println("┌─────────────────────────────────────────────────┐");
     System.out.println("│  1단계: 아이디 설정                             │");
     System.out.println("├─────────────────────────────────────────────────┤");
     System.out.println("│  • 5-20자 사이의 영문, 숫자 조합                │");
     System.out.println("│  • 특수문자는 불가합니다                        │");
     System.out.println("│                                                 │");
     System.out.print("│  아이디: ");
 }
 
 public static void showPasswordPrompt() {
     System.out.println("├─────────────────────────────────────────────────┤");
     System.out.println("│  2단계: 비밀번호 설정                           │");
     System.out.println("├─────────────────────────────────────────────────┤");
     System.out.println("│  • 8자 이상 입력해주세요                        │");
     System.out.println("│  • 영문, 숫자, 특수문자 포함 권장               │");
     System.out.println("│                                                 │");
     System.out.print("│  비밀번호: ");
 }
 
 public static void showPasswordConfirmPrompt() {
     System.out.print("│  비밀번호 확인: ");
 }
 
 public static void showNamePrompt() {
     System.out.println("├─────────────────────────────────────────────────┤");
     System.out.println("│  3단계: 이름 입력                               │");
     System.out.println("├─────────────────────────────────────────────────┤");
     System.out.println("│  • 실제 사용하실 이름을 입력해주세요             │");
     System.out.println("│                                                 │");
     System.out.print("│  이름: ");
 }
 
 public static void showEmailPrompt() {
     System.out.println("├─────────────────────────────────────────────────┤");
     System.out.println("│  4단계: 이메일 입력                             │");
     System.out.println("├─────────────────────────────────────────────────┤");
     System.out.println("│  • 유효한 이메일 주소를 입력해주세요             │");
     System.out.println("│  • 인증코드가 발송됩니다                        │");
     System.out.println("│                                                 │");
     System.out.print("│  이메일: ");
 }
 
 public static void showEmailVerificationPrompt(String email) {
     System.out.println("├─────────────────────────────────────────────────┤");
     System.out.println("│  5단계: 이메일 인증                             │");
     System.out.println("├─────────────────────────────────────────────────┤");
     System.out.printf("│  %s 로 인증코드를 발송했습니다.%10s│%n", 
         email.length() > 30 ? email.substring(0, 30) + "..." : email, "");
     System.out.println("│                                                 │");
     System.out.println("│  • 6자리 인증코드를 입력해주세요                 │");
     System.out.println("│  • 코드를 받지 못했다면 'resend'를 입력하세요    │");
     System.out.println("│                                                 │");
     System.out.print("│  인증코드: ");
 }
 
 public static void showSignupSummary(String username, String name, String email) {
     System.out.println("├─────────────────────────────────────────────────┤");
     System.out.println("│  가입 정보 확인                                 │");
     System.out.println("├─────────────────────────────────────────────────┤");
     System.out.printf("│  아이디: %s%25s│%n", username, "");
     System.out.printf("│  이름: %s%25s│%n", name, "");
     System.out.printf("│  이메일: %s%15s│%n", 
         email.length() > 25 ? email.substring(0, 25) + "..." : email, "");
     System.out.println("│                                                 │");
     System.out.println("│  위 정보로 가입하시겠습니까?                    │");
     System.out.println("├─────────────────────────────────────────────────┤");
     System.out.println("│  Y: 가입완료 | N: 취소 | E: 수정                │");
     System.out.println("└─────────────────────────────────────────────────┘");
 }
 
 public static void showEmailSending() {
     System.out.println("│                                                 │");
     System.out.println("│  📧 인증코드를 발송하고 있습니다...              │");
     System.out.println("│                                                 │");
 }
 
 public static void showEmailSent() {
     System.out.println("│  ✅ 인증코드가 발송되었습니다!                   │");
 }
 
 public static void showVerificationSuccess() {
     System.out.println("│  ✅ 이메일 인증이 완료되었습니다!                │");
     System.out.println("└─────────────────────────────────────────────────┘");
 }
 
 public static void showSignupComplete(String username) {
     System.out.println("┌─────────────────────────────────────────────────┐");
     System.out.println("│              🎉 가입 완료! 🎉                    │");
     System.out.println("├─────────────────────────────────────────────────┤");
     System.out.printf("│  %s님, 환영합니다!%20s│%n", username, "");
     System.out.println("│                                                 │");
     System.out.println("│  영화 리뷰 시스템에 가입해주셔서 감사합니다.     │");
     System.out.println("│  이제 다양한 영화들에 대한 리뷰를 작성하고       │");
     System.out.println("│  다른 사용자들과 의견을 공유할 수 있습니다.      │");
     System.out.println("│                                                 │");
     System.out.println("├─────────────────────────────────────────────────┤");
     System.out.println("│  로그인 화면으로 이동합니다...                   │");
     System.out.println("└─────────────────────────────────────────────────┘");
 }
 
 public static void showSuccessMessage(String message) {
     System.out.println("\n✅ " + message);
 }
 
 public static void showErrorMessage(String message) {
     System.out.println("\n❌ " + message);
 }
 
 public static void showWaitMessage() {
     System.out.println("\n계속하려면 Enter를 누르세요...");
 }
}
