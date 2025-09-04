CREATE TABLE users
(
    user_no   int PRIMARY KEY AUTO_INCREMENT,
    email     varchar(20) NOT NULL UNIQUE,
    password  varchar(20) NOT NULL,
    join_date datetime    NOT NULL DEFAULT now()
);

# moive_cd 번호 추가 예정(unique 확인을 위한 과정)
# 현재 movie_name 으로 잠시 대체
CREATE TABLE movies
(
    movie_no   int PRIMARY KEY AUTO_INCREMENT,
    movie_name varchar(20)  NOT NULL UNIQUE ,
    director   varchar(20)  NOT NULL,
    open_date  date         NOT NULL,
    plot       varchar(1000) NOT NULL,
    audi_cnt   int          NOT NULL
);


CREATE TABLE reviews
(
    review_no int PRIMARY KEY AUTO_INCREMENT,
    rating    int          NOT NULL,
    content   varchar(100) NULL,
    reg_date  datetime     NOT NULL DEFAULT now(),
    user_no   int          NOT NULL,
    movie_no  int          NOT NULL,
    UNIQUE KEY (user_no, movie_no),
    FOREIGN KEY (user_no) REFERENCES users (user_no) ON DELETE CASCADE,
    FOREIGN KEY (movie_no) REFERENCES movies (movie_no) ON DELETE CASCADE
);


CREATE TABLE genres
(
    genre_no   int PRIMARY KEY AUTO_INCREMENT,
    genre_name varchar(20) NOT NULL UNIQUE
);


CREATE TABLE movies_genres
(
    no       int PRIMARY KEY AUTO_INCREMENT,
    movie_no int NOT NULL,
    genre_no int NOT NULL,
    UNIQUE KEY (movie_no, genre_no),
    FOREIGN KEY (movie_no) REFERENCES movies (movie_no) ON DELETE CASCADE,
    FOREIGN KEY (genre_no) REFERENCES genres (genre_no) ON DELETE CASCADE
);


CREATE TABLE follows
(
    follow_no    int PRIMARY KEY AUTO_INCREMENT,
    follower_no  int NOT NULL,
    following_no int NOT NULL,
    UNIQUE KEY (follower_no, following_no),
	FOREIGN KEY (follower_no) REFERENCES users(user_no) ON DELETE CASCADE,
    FOREIGN KEY (following_no) REFERENCES users(user_no) ON DELETE CASCADE
);


CREATE TABLE likes
(
    like_no   int PRIMARY KEY AUTO_INCREMENT,
    user_no   int NOT NULL,
	review_no int NOT NULL,
    UNIQUE KEY (user_no, review_no),
	FOREIGN KEY (user_no) REFERENCES users(user_no) ON DELETE CASCADE,
    FOREIGN KEY (review_no) REFERENCES reviews(review_no) ON DELETE CASCADE
);

