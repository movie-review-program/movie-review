package org.example.view;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// ============== 데이터 클래스들 ==============
class User {
    private String userId;
    private String username;
    private String password;
    private String name;
    private String email;
    private String joinDate;
    private List<String> following;
    private List<String> followers;
    private List<Review> reviews;
    private List<String> likedReviews;
    
    public User(String username, String password, String name, String email) {
        this.userId = generateUserId();
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.joinDate = LocalDateTime.now().format(
            DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"));
        this.following = new ArrayList<>();
        this.followers = new ArrayList<>();
        this.reviews = new ArrayList<>();
        this.likedReviews = new ArrayList<>();
    }
    
    private String generateUserId() {
        return "user" + System.currentTimeMillis();
    }
    
    public double getAverageRating() {
        if (reviews.isEmpty()) return 0.0;
        return reviews.stream().mapToInt(Review::getRating).average().orElse(0.0);
    }
    
    // Getters
    public String getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getJoinDate() { return joinDate; }
    public List<String> getFollowing() { return following; }
    public List<String> getFollowers() { return followers; }
    public List<Review> getReviews() { return reviews; }
    public List<String> getLikedReviews() { return likedReviews; }
}

class Movie {
    private String movieId;
    private String title;
    private String director;
    private String genre;
    private int year;
    private String plot;
    private List<Review> reviews;
    
    public Movie(String title, String director, String genre, int year, String plot) {
        this.movieId = generateMovieId();
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.year = year;
        this.plot = plot;
        this.reviews = new ArrayList<>();
    }
    
    private String generateMovieId() {
        return "movie" + System.currentTimeMillis();
    }
    
    public double getAverageRating() {
        if (reviews.isEmpty()) return 0.0;
        return reviews.stream().mapToInt(Review::getRating).average().orElse(0.0);
    }
    
    public int getReviewCount() {
        return reviews.size();
    }
    
    // Getters
    public String getMovieId() { return movieId; }
    public String getTitle() { return title; }
    public String getDirector() { return director; }
    public String getGenre() { return genre; }
    public int getYear() { return year; }
    public String getPlot() { return plot; }
    public List<Review> getReviews() { return reviews; }
}

class Review {
    private String reviewId;
    private String userId;
    private String movieId;
    private String username;
    private String movieTitle;
    private String movieYear;
    private int rating;
    private String comment;
    private String createdAt;
    private int likes;
    private List<String> likedBy;
    private boolean likedByCurrentUser;
    private String timeAgo;
    
    public Review(String userId, String movieId, String username, String movieTitle, 
                  int rating, String comment) {
        this.reviewId = generateReviewId();
        this.userId = userId;
        this.movieId = movieId;
        this.username = username;
        this.movieTitle = movieTitle;
        this.rating = rating;
        this.comment = comment;
        this.createdAt = LocalDateTime.now().format(
            DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분"));
        this.likes = 0;
        this.likedBy = new ArrayList<>();
        this.likedByCurrentUser = false;
        this.timeAgo = "방금 전";
    }
    
    private String generateReviewId() {
        return "review" + System.currentTimeMillis();
    }
    
    // Getters
    public String getReviewId() { return reviewId; }
    public String getUserId() { return userId; }
    public String getMovieId() { return movieId; }
    public String getUsername() { return username; }
    public String getMovieTitle() { return movieTitle; }
    public String getMovieYear() { return movieYear; }
    public int getRating() { return rating; }
    public String getComment() { return comment; }
    public String getCreatedAt() { return createdAt; }
    public int getLikes() { return likes; }
    public List<String> getLikedBy() { return likedBy; }
    public boolean isLikedByCurrentUser() { return likedByCurrentUser; }
    public String getTimeAgo() { return timeAgo; }
}

class LoginInfo {
    private String username;
    private String password;
    
    public LoginInfo(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public String getUsername() { return username; }
    public String getPassword() { return password; }
}

// ============== 메인 시스템 클래스 ==============
public class MovieReviewSystem {
    private MovieReviewInputUI inputUI;
    private User currentUser;
    private List<Movie> movies;
    private boolean running;
    
    public MovieReviewSystem() {
        this.inputUI = new MovieReviewInputUI();
        this.currentUser = null;
        this.movies = initializeSampleMovies();
        this.running = true;
    }
    
    private List<Movie> initializeSampleMovies() {
        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie("기생충", "봉준호", "드라마, 스릴러", 2019, 
            "전원 백수로 살 길 막막한 기택 가족에게 고액 과외 자리가 아들에게 행운처럼 찾아온다."));
        movieList.add(new Movie("어벤져스: 엔드게임", "안토니 루소, 조 루소", "액션, SF", 2019,
            "인피니티 워의 끝에서 타노스가 모든 것을 파괴한 뒤 남은 어벤져스들의 마지막 전쟁."));
        movieList.add(new Movie("타이타닉", "제임스 카메론", "로맨스, 드라마", 1997,
            "1912년 처녀항해에서 침몰한 타이타닉호를 배경으로 한 비극적인 사랑 이야기."));
        movieList.add(new Movie("인터스텔라", "크리스토퍼 놀란", "SF, 드라마", 2014,
            "지구의 종말을 앞두고 새로운 행성을 찾아 떠나는 우주 탐험 이야기."));
        return movieList;
    }
    
    public void start() {
        MovieReviewOutputUI.clearScreen();
        MovieReviewOutputUI.showTitleScreen();
        MovieReviewOutputUI.showWaitMessage();
        inputUI.waitForEnter();
        
        showLoginPage();
    }
    
    private void showLoginPage() {
        while (running) {
            MovieReviewOutputUI.clearScreen();
            MovieReviewOutputUI.showLoginScreen();
            
            String choice = inputUI.getLoginChoice();
            
            switch (choice) {
                case "1":
                    handleLogin();
                    break;
                case "2":
                    handleSignup();
                    break;
                case "3":
                    handleGuestMode();
                    break;
                case "0":
                    exitProgram();
                    break;
                default:
                    MovieReviewOutputUI.showErrorMessage("올바른 번호를 입력해주세요.");
                    inputUI.waitForEnter();
            }
        }
    }
    
    private void handleLogin() {
        // 간단한 로그인 시뮬레이션
        currentUser = new User("demo", "password", "데모사용자", "demo@email.com");
        MovieReviewOutputUI.showSuccessMessage("로그인 성공!");
        inputUI.waitForEnter();
        showMainMenu();
    }
    
    private void handleSignup() {
        MovieReviewOutputUI.clearScreen();
        MovieReviewOutputUI.showSignupPage();
        inputUI.waitForEnter();
        
        // 사용자 정보 수집
        String username = getValidUsername();
        if (username == null) return; // 취소 시 로그인 화면으로
        
        String password = getValidPassword();
        if (password == null) return;
        
        String name = getName();
        if (name == null) return;
        
        String email = getEmail();
        if (email == null) return;
        
        // 이메일 인증
        if (!verifyEmail(email)) {
            return;
        }
        
        // 가입 정보 확인 및 처리
        handleSignupConfirmation(username, password, name, email);
    }

    private String getValidUsername() {
        while (true) {
            MovieReviewOutputUI.clearScreen();
            MovieReviewOutputUI.showUsernamePrompt();
            
            String username = inputUI.getGeneralChoice().trim();
            
            if (username.equals("0")) {
                return null; // 취소
            }
            
            // 유효성 검사
            if (username.length() < 5 || username.length() > 20) {
                MovieReviewOutputUI.showErrorMessage("아이디는 5-20자 사이여야 합니다.");
                inputUI.waitForEnter();
                continue;
            }
            
            if (!username.matches("^[a-zA-Z0-9]+$")) {
                MovieReviewOutputUI.showErrorMessage("아이디는 영문과 숫자만 사용할 수 있습니다.");
                inputUI.waitForEnter();
                continue;
            }
            
            // 중복 확인 (실제 구현에서는 데이터베이스 확인 필요)
            if (username.equals("demo") || username.equals("guest")) {
                MovieReviewOutputUI.showErrorMessage("이미 사용 중인 아이디입니다.");
                inputUI.waitForEnter();
                continue;
            }
            
            return username;
        }
    }

    private String getValidPassword() {
        while (true) {
            MovieReviewOutputUI.clearScreen();
            MovieReviewOutputUI.showPasswordPrompt();
            
            String password = inputUI.getGeneralChoice().trim();
            
            if (password.equals("0")) {
                return null; // 취소
            }
            
            if (password.length() < 8) {
                MovieReviewOutputUI.showErrorMessage("비밀번호는 8자 이상이어야 합니다.");
                inputUI.waitForEnter();
                continue;
            }
            
            MovieReviewOutputUI.showPasswordConfirmPrompt();
            String confirmPassword = inputUI.getGeneralChoice().trim();
            
            if (!password.equals(confirmPassword)) {
                MovieReviewOutputUI.showErrorMessage("비밀번호가 일치하지 않습니다.");
                inputUI.waitForEnter();
                continue;
            }
            
            return password;
        }
    }

    private String getName() {
        MovieReviewOutputUI.clearScreen();
        MovieReviewOutputUI.showNamePrompt();
        
        String name = inputUI.getGeneralChoice().trim();
        
        if (name.equals("0")) {
            return null; // 취소
        }
        
        if (name.isEmpty()) {
            MovieReviewOutputUI.showErrorMessage("이름을 입력해주세요.");
            inputUI.waitForEnter();
            return getName(); // 재귀 호출
        }
        
        return name;
    }

    private String getEmail() {
        while (true) {
            MovieReviewOutputUI.clearScreen();
            MovieReviewOutputUI.showEmailPrompt();
            
            String email = inputUI.getGeneralChoice().trim();
            
            if (email.equals("0")) {
                return null; // 취소
            }
            
            // 간단한 이메일 형식 검사
            if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                MovieReviewOutputUI.showErrorMessage("유효한 이메일 주소를 입력해주세요.");
                inputUI.waitForEnter();
                continue;
            }
            
            return email;
        }
    }

    private boolean verifyEmail(String email) {
        // 실제 구현에서는 이메일 발송 및 인증 코드 검증 로직이 필요함
        // 여기서는 간단히 시뮬레이션만 함
        
        String verificationCode = "123456"; // 실제로는 랜덤 생성
        
        MovieReviewOutputUI.clearScreen();
        MovieReviewOutputUI.showEmailSending();
        
        // 이메일 발송 시뮬레이션 (실제로는 SMTP 서버를 통해 발송)
        try {
            Thread.sleep(1500); // 발송 중인 것처럼 보이기 위해 잠시 대기
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        MovieReviewOutputUI.showEmailSent();
        inputUI.waitForEnter();
        
        while (true) {
            MovieReviewOutputUI.clearScreen();
            MovieReviewOutputUI.showEmailVerificationPrompt(email);
            
            String inputCode = inputUI.getGeneralChoice().trim();
            
            if (inputCode.equals("0")) {
                return false; // 취소
            }
            
            if (inputCode.equals("resend")) {
                // 인증코드 재발송
                MovieReviewOutputUI.showEmailSending();
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                MovieReviewOutputUI.showEmailSent();
                inputUI.waitForEnter();
                continue;
            }
            
            if (inputCode.equals(verificationCode)) {
                MovieReviewOutputUI.showVerificationSuccess();
                inputUI.waitForEnter();
                return true;
            } else {
                MovieReviewOutputUI.showErrorMessage("인증코드가 일치하지 않습니다.");
                inputUI.waitForEnter();
            }
        }
    }

    private void handleSignupConfirmation(String username, String password, String name, String email) {
        while (true) {
            MovieReviewOutputUI.clearScreen();
            MovieReviewOutputUI.showSignupSummary(username, name, email);
            
            String choice = inputUI.getGeneralChoice().trim().toLowerCase();
            
            if (choice.equals("y")) {
                // 사용자 생성 및 저장 (실제 구현에서는 데이터베이스에 저장)
                User newUser = new User(username, password, name, email);
                
                MovieReviewOutputUI.clearScreen();
                MovieReviewOutputUI.showSignupComplete(username);
                inputUI.waitForEnter();
                
                // 자동 로그인
                currentUser = newUser;
                showMainMenu();
                break;
            } else if (choice.equals("n")) {
                MovieReviewOutputUI.showSuccessMessage("회원가입이 취소되었습니다.");
                inputUI.waitForEnter();
                break;
            } else if (choice.equals("e")) {
                // 수정 기능은 구현하지 않음 (처음부터 다시 시작)
                handleSignup();
                break;
            } else {
                MovieReviewOutputUI.showErrorMessage("Y, N, E 중에서 선택해주세요.");
                inputUI.waitForEnter();
            }
        }
    }
    private void handleGuestMode() {
        currentUser = new User("guest", "", "비회원", "guest@temp.com");
        MovieReviewOutputUI.showSuccessMessage("비회원 모드로 진입합니다.");
        inputUI.waitForEnter();
        showMainMenu();
    }
    
    private void showMainMenu() {
        while (running && currentUser != null) {
            MovieReviewOutputUI.clearScreen();
            MovieReviewOutputUI.showMainMenu(currentUser.getName());
            
            String choice = inputUI.getMainMenuChoice();
            
            switch (choice) {
                case "1":
                    showMovieInfoPage();
                    break;
                case "2":
                    showWriteReviewPage();
                    break;
                case "3":
                    showFollowerFeedPage();
                    break;
                case "4":
                    showMyInfoPage();
                    break;
                case "5":
                    handleLogout();
                    return;
                default:
                    MovieReviewOutputUI.showErrorMessage("올바른 번호를 입력해주세요.");
                    inputUI.waitForEnter();
            }
        }
    }
    
    private void showMovieInfoPage() {
        while (true) {
            MovieReviewOutputUI.clearScreen();
            MovieReviewOutputUI.showMovieList(movies);
            
            String choice = inputUI.getMovieChoice();
            
            if (choice.equals("0")) {
                break; // 메인 메뉴로 돌아가기
            }
            
            try {
                int movieIndex = Integer.parseInt(choice) - 1;
                if (movieIndex >= 0 && movieIndex < movies.size()) {
                    showMovieDetail(movies.get(movieIndex));
                } else {
                    MovieReviewOutputUI.showErrorMessage("올바른 영화 번호를 입력해주세요.");
                    inputUI.waitForEnter();
                }
            } catch (NumberFormatException e) {
                MovieReviewOutputUI.showErrorMessage("숫자를 입력해주세요.");
                inputUI.waitForEnter();
            }
        }
    }
    
    private void showMovieDetail(Movie movie) {
        MovieReviewOutputUI.clearScreen();
        System.out.println("┌─────────────────────────────────────────────────┐");
        System.out.println("│                🎬 영화 상세 정보                 │");
        System.out.println("├─────────────────────────────────────────────────┤");
        System.out.printf("│  제목: %s (%d)%15s│%n", movie.getTitle(), movie.getYear(), "");
        System.out.printf("│  감독: %s%25s│%n", movie.getDirector(), "");
        System.out.printf("│  장르: %s%25s│%n", movie.getGenre(), "");
        System.out.println("│  ────────────────────────────────────────────   │");
        System.out.println("│  📖 줄거리                                      │");
        System.out.printf("│  %s%15s│%n", movie.getPlot().substring(0, Math.min(40, movie.getPlot().length())), "");
        System.out.println("│  ────────────────────────────────────────────   │");
        System.out.printf("│  ⭐ 평점: %.1f/5.0 (%d개 리뷰)%10s│%n", 
            movie.getAverageRating(), movie.getReviewCount(), "");
        System.out.println("├─────────────────────────────────────────────────┤");
        System.out.println("│  1. 📝 리뷰 작성하기                           │");
        System.out.println("│  2. 💬 리뷰 보기                               │");
        System.out.println("│  0. 🔙 뒤로가기                                │");
        System.out.println("└─────────────────────────────────────────────────┘");
        
        String choice = inputUI.getGeneralChoice();
        switch (choice) {
            case "1":
                MovieReviewOutputUI.showSuccessMessage("리뷰 작성 기능은 향후 구현 예정입니다.");
                inputUI.waitForEnter();
                break;
            case "2":
                MovieReviewOutputUI.showSuccessMessage("리뷰 보기 기능은 향후 구현 예정입니다.");
                inputUI.waitForEnter();
                break;
            case "0":
                break;
            default:
                MovieReviewOutputUI.showErrorMessage("올바른 번호를 입력해주세요.");
                inputUI.waitForEnter();
                showMovieDetail(movie); // 재귀 호출로 다시 보여주기
        }
    }
    
    private void showWriteReviewPage() {
        MovieReviewOutputUI.clearScreen();
        MovieReviewOutputUI.showWriteReviewPage();
        
        String choice = inputUI.getGeneralChoice();
        if (!choice.equals("0")) {
            MovieReviewOutputUI.showErrorMessage("올바른 번호를 입력해주세요.");
            inputUI.waitForEnter();
            showWriteReviewPage(); // 재귀 호출
        }
    }
    
    private void showFollowerFeedPage() {
        MovieReviewOutputUI.clearScreen();
        MovieReviewOutputUI.showFollowerFeedPage();
        
        String choice = inputUI.getGeneralChoice();
        if (!choice.equals("0")) {
            MovieReviewOutputUI.showErrorMessage("올바른 번호를 입력해주세요.");
            inputUI.waitForEnter();
            showFollowerFeedPage(); // 재귀 호출
        }
    }
    
    private void showMyInfoPage() {
        while (true) {
            MovieReviewOutputUI.clearScreen();
            MovieReviewOutputUI.showMyInfo(currentUser);
            
            String choice = inputUI.getMyInfoChoice();
            
            switch (choice) {
                case "1":
                    MovieReviewOutputUI.showSuccessMessage("작성한 리뷰보기 기능은 향후 구현 예정입니다.");
                    inputUI.waitForEnter();
                    break;
                case "2":
                    MovieReviewOutputUI.showSuccessMessage("팔로우 목록보기 기능은 향후 구현 예정입니다.");
                    inputUI.waitForEnter();
                    break;
                case "3":
                    MovieReviewOutputUI.showSuccessMessage("좋아요 목록보기 기능은 향후 구현 예정입니다.");
                    inputUI.waitForEnter();
                    break;
                case "4":
                    MovieReviewOutputUI.showSuccessMessage("프로필 수정 기능은 향후 구현 예정입니다.");
                    inputUI.waitForEnter();
                    break;
                case "0":
                    return; // 메인 메뉴로 돌아가기
                default:
                    MovieReviewOutputUI.showErrorMessage("올바른 번호를 입력해주세요.");
                    inputUI.waitForEnter();
            }
        }
    }
    
    private void handleLogout() {
        MovieReviewOutputUI.showSuccessMessage(currentUser.getName() + "님, 로그아웃 되었습니다.");
        currentUser = null;
        inputUI.waitForEnter();
    }
    
    private void exitProgram() {
        MovieReviewOutputUI.showSuccessMessage("프로그램을 종료합니다. 안녕히 가세요!");
        running = false;
    }
    
    // ============== 메인 메소드 ==============
    public static void main(String[] args) {
        MovieReviewSystem system = new MovieReviewSystem();
        system.start();
    }
}