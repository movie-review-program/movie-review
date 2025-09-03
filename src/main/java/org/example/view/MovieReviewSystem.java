package org.example.view;

import java.util.ArrayList;
import java.util.List;

import org.example.controller.UserController;
import org.example.model.dto.User;
import org.example.util.SessionManagerImpl;

// ================== 영화 정보 데이터 클래스 ==================
class Movie {
    private String movieId;    // 영화 고유 ID
    private String title;      // 영화 제목
    private String director;   // 감독 이름
    private String genre;      // 영화 장르
    private int year;          // 개봉 연도
    private String plot;       // 줄거리

    // 생성자: 영화 객체 생성 시 자동으로 movieId 부여
    public Movie(String title, String director, String genre, int year, String plot) {
        this.movieId = generateMovieId();
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.year = year;
        this.plot = plot;
    }

    // 영화 ID 생성 (현재 시간 기준 고유 ID)
    private String generateMovieId() {
        return "movie" + System.currentTimeMillis() + "_" + Math.random();
    }

    // 영화 평균 평점 반환 (현재는 임시 0.0)
    public double getAverageRating() {
        return 0.0; // 향후 리뷰 데이터 기반 구현 예정
    }

    // 영화 리뷰 수 반환 (현재는 임시 0)
    public int getReviewCount() {
        return 0; // 향후 리뷰 데이터 기반 구현 예정
    }

    // Getter 메소드
    public String getMovieId() { return movieId; }
    public String getTitle() { return title; }
    public String getDirector() { return director; }
    public String getGenre() { return genre; }
    public int getYear() { return year; }
    public String getPlot() { return plot; }
}

// ================== 리뷰 데이터 클래스 ==================
class Review {
    private String reviewId;  // 리뷰 고유 ID
    private String userId;    // 작성자 ID
    private String movieId;   // 영화 ID
    private int rating;       // 평점 (1-5)
    private String comment;   // 리뷰 내용
    private long createdTime; // 작성 시간

    // 생성자: 리뷰 생성 시 자동 reviewId 부여
    public Review(String userId, String movieId, int rating, String comment) {
        this.reviewId = generateReviewId();
        this.userId = userId;
        this.movieId = movieId;
        this.rating = rating;
        this.comment = comment;
        this.createdTime = System.currentTimeMillis();
    }

    // 리뷰 ID 생성
    private String generateReviewId() {
        return "review" + System.currentTimeMillis() + "_" + Math.random();
    }

    // Getter 메소드
    public String getReviewId() { return reviewId; }
    public String getUserId() { return userId; }
    public String getMovieId() { return movieId; }
    public int getRating() { return rating; }
    public String getComment() { return comment; }
    public long getCreatedTime() { return createdTime; }
}

// ================== 영화 리뷰 시스템 메인 클래스 ==================
public class MovieReviewSystem {
    private MovieReviewInputUI inputUI;     // 사용자 입력 처리 UI
    private List<Movie> movies;             // 영화 목록
    private List<Review> reviews;           // 리뷰 목록
    private boolean running;                // 프로그램 실행 상태
    private UserController userController;  // 사용자 컨트롤러
    private SessionManagerImpl sessionManager; // 세션 매니저

    // 생성자
    public MovieReviewSystem() {
        this.inputUI = new MovieReviewInputUI();
        this.movies = initializeSampleMovies();     // 샘플 영화 초기화
        this.reviews = new ArrayList<>();           // 리뷰 목록 초기화
        this.running = true;                        // 프로그램 실행 플래그
        this.userController = new UserController(); // 사용자 컨트롤러 초기화
        this.sessionManager = SessionManagerImpl.getInstance(); // 세션 매니저
    }

    // 샘플 영화 목록 초기화
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
        movieList.add(new Movie("아바타", "제임스 카메론", "SF, 액션", 2009,
            "외계 행성 판도라를 배경으로 한 인간과 나비족 간의 갈등과 사랑 이야기."));
        return movieList;
    }

    // 프로그램 시작
    public void start() {
        try {
            MovieReviewOutputUI.clearScreen(); // 화면 초기화
            MovieReviewOutputUI.showTitleScreen(); // 타이틀 화면 출력
            MovieReviewOutputUI.showWaitMessage();  // Enter 대기
            inputUI.waitForEnter();

            showLoginPage(); // 로그인 화면 호출
        } catch (Exception e) {
            System.err.println("프로그램 시작 중 오류 발생: " + e.getMessage());
            exitProgram();
        }
    }

    // ================== 로그인 화면 ==================
    private void showLoginPage() {
        while (running) {
            try {
                MovieReviewOutputUI.clearScreen();
                MovieReviewOutputUI.showLoginScreen();

                String choice = inputUI.getLoginChoice();

                switch (choice) {
                    case "1": handleLogin(); break;  // 로그인
                    case "2": handleSignup(); break; // 회원가입
                    case "3": handleGuestMode(); break; // 비회원 모드
                    case "0": exitProgram(); break;  // 프로그램 종료
                    default:
                        MovieReviewOutputUI.showErrorMessage("올바른 번호를 입력해주세요.");
                        inputUI.waitForEnter();
                }
            } catch (Exception e) {
                MovieReviewOutputUI.showErrorMessage("로그인 화면 처리 중 오류 발생: " + e.getMessage());
                inputUI.waitForEnter();
            }
        }
    }

    // ================== 로그인 처리 ==================
    private void handleLogin() {
        try {
            MovieReviewOutputUI.clearScreen();
            MovieReviewOutputUI.showLoginIdPrompt();
            
            String email = inputUI.getGeneralChoice().trim();
            if (email.equals("0")) return; // 취소 시 이전 화면으로

            MovieReviewOutputUI.showLoginPasswordPrompt();
            String password = inputUI.getGeneralChoice().trim();
            if (password.equals("0")) return; // 취소 시 이전 화면으로

            boolean loginSuccess = userController.handleLogin(email, password);

            if (loginSuccess) {
                MovieReviewOutputUI.showSuccessMessage("로그인 성공!");
                inputUI.waitForEnter();
                showMainMenu();
            } else {
                MovieReviewOutputUI.showErrorMessage("이메일 또는 비밀번호가 올바르지 않습니다.");
                inputUI.waitForEnter();
            }
        } catch (Exception e) {
            MovieReviewOutputUI.showErrorMessage("로그인 처리 중 오류 발생: " + e.getMessage());
            inputUI.waitForEnter();
        }
    }

    // ================== 회원가입 처리 ==================
    private void handleSignup() {
        try {
            MovieReviewOutputUI.clearScreen();
        
            String email = getValidEmail();
            if (email == null) return; // 취소

            String password = getValidPassword();
            if (password == null) return; // 취소

            String name = getValidName();
            if (name == null) return; // 취소

            handleSignupConfirmation(email, password, name); // 가입 정보 확인
        } catch (Exception e) {
            MovieReviewOutputUI.showErrorMessage("회원가입 처리 중 오류 발생: " + e.getMessage());
            inputUI.waitForEnter();
        }
    }

    // 이메일 입력 및 유효성 검사
    private String getValidEmail() {
        while (true) {
            MovieReviewOutputUI.clearScreen();
            MovieReviewOutputUI.showEmailPrompt();
            String email = inputUI.getGeneralChoice().trim();

            if (email.equals("0")) return null;

            // 기본 유효성 검사
            if (email.isEmpty()) {
                MovieReviewOutputUI.showErrorMessage("이메일을 입력해주세요.");
                inputUI.waitForEnter();
                continue;
            }

            // 이메일 형식 체크 (수정된 정규식)
            if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                MovieReviewOutputUI.showErrorMessage("유효한 이메일 주소를 입력해주세요. (예: user@example.com)");
                inputUI.waitForEnter();
                continue;
            }

            // 길이 제한
            if (email.length() > 50) {
                MovieReviewOutputUI.showErrorMessage("이메일은 50자 이하로 입력해주세요.");
                inputUI.waitForEnter();
                continue;
            }

            // 중복 체크
            if (userController.isEmailDuplicate(email)) {
                MovieReviewOutputUI.showErrorMessage("이미 사용 중인 이메일입니다.");
                inputUI.waitForEnter();
                continue;
            }

            return email;
        }
    }

    // 비밀번호 입력 및 검증
    private String getValidPassword() {
        while (true) {
            MovieReviewOutputUI.clearScreen();
            MovieReviewOutputUI.showPasswordPrompt();
            String password = inputUI.getGeneralChoice().trim();

            if (password.equals("0")) return null;

            if (password.length() < 4) {
                MovieReviewOutputUI.showErrorMessage("비밀번호는 4자 이상이어야 합니다.");
                inputUI.waitForEnter();
                continue;
            }

            if (password.length() > 20) {
                MovieReviewOutputUI.showErrorMessage("비밀번호는 20자 이하로 입력해주세요.");
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

    // 이름 입력 및 검증
    private String getValidName() {
        while (true) {
            MovieReviewOutputUI.clearScreen();
            MovieReviewOutputUI.showNamePrompt();

            String name = inputUI.getGeneralChoice().trim();

            if (name.equals("0")) return null;
            
            if (name.isEmpty()) {
                MovieReviewOutputUI.showErrorMessage("이름을 입력해주세요.");
                inputUI.waitForEnter();
                continue;
            }
            
            if (name.length() < 2) {
                MovieReviewOutputUI.showErrorMessage("이름은 2자 이상 입력해주세요.");
                inputUI.waitForEnter();
                continue;
            }
            
            if (name.length() > 10) {
                MovieReviewOutputUI.showErrorMessage("이름은 10자 이하로 입력해주세요.");
                inputUI.waitForEnter();
                continue;
            }

            return name;
        }
    }

    // 회원가입 정보 확인 및 저장
    private void handleSignupConfirmation(String email, String password, String name) {
        while (true) {
            try {
                MovieReviewOutputUI.clearScreen();
                MovieReviewOutputUI.showSignupSummary(email, name, email);

                String choice = inputUI.getGeneralChoice().trim().toLowerCase();

                if (choice.equals("y")) {
                    boolean signupSuccess = userController.handleSignup(email, password, name);

                    if (signupSuccess) {
                        MovieReviewOutputUI.clearScreen();
                        MovieReviewOutputUI.showSignupComplete(name);
                        inputUI.waitForEnter();
                        showMainMenu();
                    } else {
                        MovieReviewOutputUI.showErrorMessage("회원가입 중 오류가 발생했습니다.");
                        inputUI.waitForEnter();
                    }
                    break;
                } else if (choice.equals("n")) {
                    MovieReviewOutputUI.showSuccessMessage("회원가입이 취소되었습니다.");
                    inputUI.waitForEnter();
                    break;
                } else if (choice.equals("e")) {
                    handleSignup();
                    break;
                } else {
                    MovieReviewOutputUI.showErrorMessage("Y, N, E 중에서 선택해주세요.");
                    inputUI.waitForEnter();
                }
            } catch (Exception e) {
                MovieReviewOutputUI.showErrorMessage("회원가입 확인 중 오류 발생: " + e.getMessage());
                inputUI.waitForEnter();
                break;
            }
        }
    }

    // ================== 비회원 모드 ==================
    private void handleGuestMode() {
        try {
            // 임시 게스트 사용자 생성
            User guestUser = new User();
            guestUser.setEmail("guest@temp.com");
            guestUser.setName("비회원");
            guestUser.setUserNo(-1); // 게스트 식별자
            
            sessionManager.login(guestUser);
            MovieReviewOutputUI.showSuccessMessage("비회원 모드로 진입합니다. (일부 기능이 제한됩니다.)");
            inputUI.waitForEnter();
            showMainMenu();
        } catch (Exception e) {
            MovieReviewOutputUI.showErrorMessage("비회원 모드 진입 중 오류 발생: " + e.getMessage());
            inputUI.waitForEnter();
        }
    }

    // ================== 메인 메뉴 ==================
    private void showMainMenu() {
        while (running && sessionManager.isLoggedIn()) {
            try {
                MovieReviewOutputUI.clearScreen();
                MovieReviewOutputUI.showMainMenu(sessionManager.getCurrentUserName());

                String choice = inputUI.getMainMenuChoice();

                switch (choice) {
                    case "1": showMovieInfoPage(); break;
                    case "2": showWriteReviewPage(); break;
                    case "3": showFollowerFeedPage(); break;
                    case "4": showMyInfoPage(); break;
                    case "5": handleLogout(); return;
                    default:
                        MovieReviewOutputUI.showErrorMessage("올바른 번호를 입력해주세요.");
                        inputUI.waitForEnter();
                }
            } catch (Exception e) {
                MovieReviewOutputUI.showErrorMessage("메인 메뉴 처리 중 오류 발생: " + e.getMessage());
                inputUI.waitForEnter();
            }
        }
    }

    // ================== 영화 목록 및 상세 정보 ==================
    private void showMovieInfoPage() {
        while (true) {
            try {
                MovieReviewOutputUI.clearScreen();
                MovieReviewOutputUI.showMovieList(movies);

                String choice = inputUI.getMovieChoice();

                if (choice.equals("0")) break;

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
            } catch (Exception e) {
                MovieReviewOutputUI.showErrorMessage("영화 목록 처리 중 오류 발생: " + e.getMessage());
                inputUI.waitForEnter();
            }
        }
    }

    private void showMovieDetail(Movie movie) {
        while (true) {
            try {
                MovieReviewOutputUI.clearScreen();
                displayMovieDetailUI(movie);

                String choice = inputUI.getGeneralChoice();
                switch (choice) {
                    case "1":
                        if (isGuestUser()) {
                            MovieReviewOutputUI.showErrorMessage("로그인 후 리뷰 작성이 가능합니다.");
                            inputUI.waitForEnter();
                        } else {
                            handleWriteReview(movie);
                        }
                        break;
                    case "2":
                        showMovieReviews(movie);
                        break;
                    case "0": 
                        return;
                    default:
                        MovieReviewOutputUI.showErrorMessage("올바른 번호를 입력해주세요.");
                        inputUI.waitForEnter();
                }
            } catch (Exception e) {
                MovieReviewOutputUI.showErrorMessage("영화 상세 정보 처리 중 오류 발생: " + e.getMessage());
                inputUI.waitForEnter();
                return;
            }
        }
    }

    private void displayMovieDetailUI(Movie movie) {
        List<Review> movieReviews = getMovieReviews(movie.getMovieId());
        double avgRating = calculateAverageRating(movieReviews);
        
        System.out.println("┌─────────────────────────────────────────────────┐");
        System.out.println("│                🎬 영화 상세 정보                 │");
        System.out.println("├─────────────────────────────────────────────────┤");
        System.out.printf("│  제목: %s (%d)%n", movie.getTitle(), movie.getYear());
        System.out.printf("│  감독: %s%n", movie.getDirector());
        System.out.printf("│  장르: %s%n", movie.getGenre());
        System.out.println("│  ────────────────────────────────────────────   │");
        System.out.println("│  📖 줄거리                                      │");
        System.out.printf("│  %s%n", movie.getPlot());
        System.out.println("│  ────────────────────────────────────────────   │");
        System.out.printf("│  ⭐ 평점: %.1f/5.0 (%d개 리뷰)%n", avgRating, movieReviews.size());
        System.out.println("├─────────────────────────────────────────────────┤");
        System.out.println("│  1. 📝 리뷰 작성하기                           │");
        System.out.println("│  2. 💬 리뷰 보기                               │");
        System.out.println("│  0. 🔙 뒤로가기                                │");
        System.out.println("└─────────────────────────────────────────────────┘");
    }

    // 특정 영화의 리뷰 목록 조회
    private List<Review> getMovieReviews(String movieId) {
        List<Review> movieReviews = new ArrayList<>();
        for (Review review : reviews) {
            if (review.getMovieId().equals(movieId)) {
                movieReviews.add(review);
            }
        }
        return movieReviews;
    }

    // 평균 평점 계산
    private double calculateAverageRating(List<Review> movieReviews) {
        if (movieReviews.isEmpty()) return 0.0;
        
        double sum = 0.0;
        for (Review review : movieReviews) {
            sum += review.getRating();
        }
        return sum / movieReviews.size();
    }

    // 리뷰 작성 처리
    private void handleWriteReview(Movie movie) {
        try {
            MovieReviewOutputUI.clearScreen();
            System.out.println("┌─────────────────────────────────────────────────┐");
            System.out.println("│                📝 리뷰 작성                      │");
            System.out.println("├─────────────────────────────────────────────────┤");
            System.out.printf("│  영화: %s%n", movie.getTitle());
            System.out.println("│  ────────────────────────────────────────────   │");
            System.out.println("│  평점을 입력해주세요 (1-5):                     │");
            System.out.println("└─────────────────────────────────────────────────┘");

            String ratingInput = inputUI.getGeneralChoice().trim();
            if (ratingInput.equals("0")) return;

            int rating = Integer.parseInt(ratingInput);
            if (rating < 1 || rating > 5) {
                MovieReviewOutputUI.showErrorMessage("평점은 1-5 사이의 숫자를 입력해주세요.");
                inputUI.waitForEnter();
                return;
            }

            System.out.println("리뷰 내용을 입력해주세요 (0: 취소):");
            String comment = inputUI.getGeneralChoice().trim();
            if (comment.equals("0")) return;

            if (comment.isEmpty()) {
                MovieReviewOutputUI.showErrorMessage("리뷰 내용을 입력해주세요.");
                inputUI.waitForEnter();
                return;
            }

            // 리뷰 저장
            String currentUserId = String.valueOf(sessionManager.getCurrentUserId());
            Review newReview = new Review(currentUserId, movie.getMovieId(), rating, comment);
            reviews.add(newReview);

            MovieReviewOutputUI.showSuccessMessage("리뷰가 성공적으로 작성되었습니다!");
            inputUI.waitForEnter();

        } catch (NumberFormatException e) {
            MovieReviewOutputUI.showErrorMessage("평점은 숫자로 입력해주세요.");
            inputUI.waitForEnter();
        } catch (Exception e) {
            MovieReviewOutputUI.showErrorMessage("리뷰 작성 중 오류 발생: " + e.getMessage());
            inputUI.waitForEnter();
        }
    }

    // 영화 리뷰 보기
    private void showMovieReviews(Movie movie) {
        try {
            List<Review> movieReviews = getMovieReviews(movie.getMovieId());
            
            MovieReviewOutputUI.clearScreen();
            System.out.println("┌─────────────────────────────────────────────────┐");
            System.out.printf("│           💬 %s - 리뷰 목록%n", movie.getTitle());
            System.out.println("├─────────────────────────────────────────────────┤");

            if (movieReviews.isEmpty()) {
                System.out.println("│  아직 작성된 리뷰가 없습니다.                   │");
            } else {
                for (int i = 0; i < movieReviews.size(); i++) {
                    Review review = movieReviews.get(i);
                    System.out.printf("│  %d. ⭐%d점 - %s%n", i + 1, review.getRating(), review.getComment());
                    if (i < movieReviews.size() - 1) {
                        System.out.println("│  ────────────────────────────────────────────   │");
                    }
                }
            }

            System.out.println("├─────────────────────────────────────────────────┤");
            System.out.println("│  0. 🔙 뒤로가기                                │");
            System.out.println("└─────────────────────────────────────────────────┘");

            inputUI.getGeneralChoice();
        } catch (Exception e) {
            MovieReviewOutputUI.showErrorMessage("리뷰 목록 조회 중 오류 발생: " + e.getMessage());
            inputUI.waitForEnter();
        }
    }

    // 게스트 사용자인지 확인
    private boolean isGuestUser() {
        User currentUser = sessionManager.getCurrentUser();
        return currentUser != null && currentUser.getUserNo() == -1;
    }

    // ================== 리뷰 작성 페이지 ==================
    private void showWriteReviewPage() {
        MovieReviewOutputUI.clearScreen();
        MovieReviewOutputUI.showWriteReviewPage();
        inputUI.waitForEnter();
    }

    // ================== 팔로워 피드 페이지 ==================
    private void showFollowerFeedPage() {
        MovieReviewOutputUI.clearScreen();
        MovieReviewOutputUI.showFollowerFeedPage();
        inputUI.waitForEnter();
    }

    // ================== 내 정보 페이지 ==================
    private void showMyInfoPage() {
        while (true) {
            try {
                MovieReviewOutputUI.clearScreen();
                MovieReviewOutputUI.showMyInfo(sessionManager.getCurrentUser());

                String choice = inputUI.getMyInfoChoice();

                switch (choice) {
                    case "1":
                        showMyReviews();
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
                    case "0": return;
                    default:
                        MovieReviewOutputUI.showErrorMessage("올바른 번호를 입력해주세요.");
                        inputUI.waitForEnter();
                }
            } catch (Exception e) {
                MovieReviewOutputUI.showErrorMessage("내 정보 페이지 처리 중 오류 발생: " + e.getMessage());
                inputUI.waitForEnter();
            }
        }
    }

    // 내가 작성한 리뷰 보기
    private void showMyReviews() {
        try {
            String currentUserId = String.valueOf(sessionManager.getCurrentUserId());
            List<Review> myReviews = new ArrayList<>();
            
            for (Review review : reviews) {
                if (review.getUserId().equals(currentUserId)) {
                    myReviews.add(review);
                }
            }

            MovieReviewOutputUI.clearScreen();
            System.out.println("┌─────────────────────────────────────────────────┐");
            System.out.println("│                📝 내가 작성한 리뷰                │");
            System.out.println("├─────────────────────────────────────────────────┤");

            if (myReviews.isEmpty()) {
                System.out.println("│  작성한 리뷰가 없습니다.                        │");
            } else {
                for (int i = 0; i < myReviews.size(); i++) {
                    Review review = myReviews.get(i);
                    Movie movie = findMovieById(review.getMovieId());
                    String movieTitle = movie != null ? movie.getTitle() : "알 수 없는 영화";
                    
                    System.out.printf("│  %d. [%s] ⭐%d점%n", i + 1, movieTitle, review.getRating());
                    System.out.printf("│     %s%n", review.getComment());
                    if (i < myReviews.size() - 1) {
                        System.out.println("│  ────────────────────────────────────────────   │");
                    }
                }
            }

            System.out.println("├─────────────────────────────────────────────────┤");
            System.out.println("│  0. 🔙 뒤로가기                                │");
            System.out.println("└─────────────────────────────────────────────────┘");

            inputUI.getGeneralChoice();
        } catch (Exception e) {
            MovieReviewOutputUI.showErrorMessage("내 리뷰 조회 중 오류 발생: " + e.getMessage());
            inputUI.waitForEnter();
        }
    }

    // 영화 ID로 영화 찾기
    private Movie findMovieById(String movieId) {
        for (Movie movie : movies) {
            if (movie.getMovieId().equals(movieId)) {
                return movie;
            }
        }
        return null;
    }

    // ================== 로그아웃 처리 ==================
    private void handleLogout() {
        try {
            boolean logoutSuccess = userController.handleLogout();
            if (logoutSuccess) {
                inputUI.waitForEnter();
            }
        } catch (Exception e) {
            MovieReviewOutputUI.showErrorMessage("로그아웃 처리 중 오류 발생: " + e.getMessage());
            inputUI.waitForEnter();
        }
    }

    // ================== 프로그램 종료 ==================
    private void exitProgram() {
        try {
            if (sessionManager.isLoggedIn()) {
                userController.handleLogout();
            }
            MovieReviewOutputUI.showSuccessMessage("프로그램을 종료합니다. 안녕히 가세요!");
            running = false;
        } catch (Exception e) {
            System.err.println("프로그램 종료 중 오류 발생: " + e.getMessage());
            running = false;
        }
    }

    // ================== 유틸리티 메서드 ==================
    
    /**
     * 프로그램 실행 상태 확인
     * @return 실행 중이면 true
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * 영화 목록 조회
     * @return 영화 목록
     */
    public List<Movie> getMovies() {
        return new ArrayList<>(movies); // 방어적 복사
    }

    /**
     * 리뷰 목록 조회
     * @return 리뷰 목록
     */
    public List<Review> getReviews() {
        return new ArrayList<>(reviews); // 방어적 복사
    }

    /**
     * 특정 사용자의 리뷰 개수 조회
     * @param userId 사용자 ID
     * @return 리뷰 개수
     */
    public int getUserReviewCount(String userId) {
        int count = 0;
        for (Review review : reviews) {
            if (review.getUserId().equals(userId)) {
                count++;
            }
        }
        return count;
    }

    /**
     * 영화 검색 기능 (제목으로 검색)
     * @param keyword 검색 키워드
     * @return 검색 결과 영화 목록
     */
    public List<Movie> searchMovies(String keyword) {
        List<Movie> results = new ArrayList<>();
        String lowerKeyword = keyword.toLowerCase();
        
        for (Movie movie : movies) {
            if (movie.getTitle().toLowerCase().contains(lowerKeyword) ||
                movie.getDirector().toLowerCase().contains(lowerKeyword) ||
                movie.getGenre().toLowerCase().contains(lowerKeyword)) {
                results.add(movie);
            }
        }
        return results;
    }

    /**
     * 평점별 영화 정렬
     * @return 평점 높은 순으로 정렬된 영화 목록
     */
    public List<Movie> getMoviesSortedByRating() {
        List<Movie> sortedMovies = new ArrayList<>(movies);
        sortedMovies.sort((m1, m2) -> {
            double rating1 = calculateAverageRating(getMovieReviews(m1.getMovieId()));
            double rating2 = calculateAverageRating(getMovieReviews(m2.getMovieId()));
            return Double.compare(rating2, rating1); // 내림차순 정렬
        });
        return sortedMovies;
    }

    /**
     * 연도별 영화 정렬
     * @return 최신 영화 순으로 정렬된 영화 목록
     */
    public List<Movie> getMoviesSortedByYear() {
        List<Movie> sortedMovies = new ArrayList<>(movies);
        sortedMovies.sort((m1, m2) -> Integer.compare(m2.getYear(), m1.getYear())); // 내림차순
        return sortedMovies;
    }

    /**
     * 시스템 통계 정보 조회
     * @return 통계 정보 문자열
     */
    public String getSystemStats() {
        StringBuilder stats = new StringBuilder();
        stats.append("┌─────────────────────────────────────────────────┐\n");
        stats.append("│                📊 시스템 통계                    │\n");
        stats.append("├─────────────────────────────────────────────────┤\n");
        stats.append(String.format("│  총 영화 수: %d편%n", movies.size()));
        stats.append(String.format("│  총 리뷰 수: %d개%n", reviews.size()));
        
        if (!reviews.isEmpty()) {
            double totalRating = 0;
            for (Review review : reviews) {
                totalRating += review.getRating();
            }
            double avgRating = totalRating / reviews.size();
            stats.append(String.format("│  전체 평균 평점: %.1f점%n", avgRating));
        } else {
            stats.append("│  전체 평균 평점: 0.0점\n");
        }
        
        stats.append("└─────────────────────────────────────────────────┘");
        return stats.toString();
    }

    // ================== 메인 메소드 ==================
    public static void main(String[] args) {
        try {
            MovieReviewSystem system = new MovieReviewSystem();
            system.start(); // 프로그램 실행
        } catch (Exception e) {
            System.err.println("프로그램 실행 중 치명적 오류 발생: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}