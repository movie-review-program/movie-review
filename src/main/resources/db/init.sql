-- init.sql
-- Assumptions:
-- 1) tables 'movies', 'genres', 'movies_genres' already exist and are populated.
-- 2) 'movies' has ids from 1..30 (at least).
-- 3) This script inserts dummy users, reviews, follows, likes.
-- 4) Subselects avoid relying on AUTO_INCREMENT ids.

SET NAMES utf8mb4;

START TRANSACTION;

-- =========================
-- 1) USERS (12명)
-- =========================
INSERT INTO users (email, password, name, join_date) VALUES
                                                         ('u01@mv.io','pass01!','Alice','2025-08-18 10:10:00'),
                                                         ('u02@mv.io','pass02!','Bob','2025-08-19 11:00:00'),
                                                         ('u03@mv.io','pass03!','Charlie','2025-08-20 09:45:00'),
                                                         ('u04@mv.io','pass04!','Daisy','2025-08-21 13:15:00'),
                                                         ('u05@mv.io','pass05!','Evan','2025-08-22 18:20:00'),
                                                         ('u06@mv.io','pass06!','Fiona','2025-08-23 08:55:00'),
                                                         ('u07@mv.io','pass07!','Glen','2025-08-24 21:30:00'),
                                                         ('u08@mv.io','pass08!','Hana','2025-08-25 07:35:00'),
                                                         ('u09@mv.io','pass09!','Ian','2025-08-26 12:25:00'),
                                                         ('u10@mv.io','pass10!','Jane','2025-08-27 14:40:00'),
                                                         ('u11@mv.io','pass11!','Kyle','2025-08-28 16:05:00'),
                                                         ('u12@mv.io','pass12!','Luna','2025-08-29 19:50:00');

-- =========================
-- 2) REVIEWS (22건)
--  - (user_no, movie_no) UNIQUE 제약 준수
--  - 영화 번호는 1..15 중 일부 사용 (원하면 조정)
-- =========================
-- u01: movies 3,7
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 5, '타이트한 연출과 좋은 리듬.', '2025-09-01 12:00:00', u.user_no, 3
FROM users u WHERE u.email='u01@mv.io';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 4, '초반은 평이하지만 후반이 좋음.', '2025-09-02 09:10:00', u.user_no, 7
FROM users u WHERE u.email='u01@mv.io';

-- u02: movies 5,12
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 4, '음악과 미장센이 인상적.', '2025-09-01 13:30:00', u.user_no, 5
FROM users u WHERE u.email='u02@mv.io';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 3, '스토리는 평범하지만 볼만함.', '2025-09-03 10:05:00', u.user_no, 12
FROM users u WHERE u.email='u02@mv.io';

-- u03: movies 7,9
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 5, '몰입감 최고. 재감상 의사 있음.', '2025-09-01 20:40:00', u.user_no, 7
FROM users u WHERE u.email='u03@mv.io';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 2, '과장된 연출이 아쉬움.', '2025-09-04 08:20:00', u.user_no, 9
FROM users u WHERE u.email='u03@mv.io';

-- u04: movies 2,6
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 4, '배우들의 합이 좋다.', '2025-09-01 15:22:00', u.user_no, 2
FROM users u WHERE u.email='u04@mv.io';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 3, '전개가 조금 루즈.', '2025-09-02 22:05:00', u.user_no, 6
FROM users u WHERE u.email='u04@mv.io';

-- u05: movies 1,14
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 5, '연출, 연기, 음악 모두 훌륭.', '2025-09-01 11:11:00', u.user_no, 1
FROM users u WHERE u.email='u05@mv.io';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 4, '의외의 반전이 매력적.', '2025-09-03 17:05:00', u.user_no, 14
FROM users u WHERE u.email='u05@mv.io';

-- u06: movies 8,10
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 2, '호불호가 크게 갈릴 듯.', '2025-09-02 12:12:00', u.user_no, 8
FROM users u WHERE u.email='u06@mv.io';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 4, '완성도 있는 장르물.', '2025-09-04 09:45:00', u.user_no, 10
FROM users u WHERE u.email='u06@mv.io';

-- u07: movies 11,4
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 3, '캐릭터 매력이 살짝 부족.', '2025-09-01 09:00:00', u.user_no, 11
FROM users u WHERE u.email='u07@mv.io';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 5, '연출 미쳤다. 강추.', '2025-09-03 13:33:00', u.user_no, 4
FROM users u WHERE u.email='u07@mv.io';

-- u08: movies 13,3
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 4, '잔잔하지만 여운이 큼.', '2025-09-02 08:08:00', u.user_no, 13
FROM users u WHERE u.email='u08@mv.io';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 3, '평균 이상. 무난.', '2025-09-04 19:10:00', u.user_no, 3
FROM users u WHERE u.email='u08@mv.io';

-- u09: movies 15,5
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 5, '마지막 20분이 압권.', '2025-09-01 21:10:00', u.user_no, 15
FROM users u WHERE u.email='u09@mv.io';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 4, '사운드 디자인 좋음.', '2025-09-02 16:40:00', u.user_no, 5
FROM users u WHERE u.email='u09@mv.io';

-- u10: movies 2,7
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 3, '중반부가 아쉬움.', '2025-09-03 07:25:00', u.user_no, 2
FROM users u WHERE u.email='u10@mv.io';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 5, '올해 최고작 후보.', '2025-09-04 12:55:00', u.user_no, 7
FROM users u WHERE u.email='u10@mv.io';

-- u11: movie 6
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 4, '균형 잡힌 작품.', '2025-09-02 20:20:00', u.user_no, 6
FROM users u WHERE u.email='u11@mv.io';

-- u12: movie 9
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 2, '취향은 아니었음.', '2025-09-03 18:45:00', u.user_no, 9
FROM users u WHERE u.email='u12@mv.io';

-- =========================
-- 3) FOLLOWS (16건)
--  - 자기 자신 팔로우 방지
--  - (follower_no, following_no) UNIQUE 준수
-- =========================
-- 링 + 교차 팔로우
INSERT INTO follows (follower_no, following_no)
SELECT a.user_no, b.user_no FROM users a JOIN users b
                                              ON a.email='u01@mv.io' AND b.email='u02@mv.io';
INSERT INTO follows (follower_no, following_no)
SELECT a.user_no, b.user_no FROM users a JOIN users b
                                              ON a.email='u02@mv.io' AND b.email='u03@mv.io';
INSERT INTO follows (follower_no, following_no)
SELECT a.user_no, b.user_no FROM users a JOIN users b
                                              ON a.email='u03@mv.io' AND b.email='u04@mv.io';
INSERT INTO follows (follower_no, following_no)
SELECT a.user_no, b.user_no FROM users a JOIN users b
                                              ON a.email='u04@mv.io' AND b.email='u05@mv.io';
INSERT INTO follows (follower_no, following_no)
SELECT a.user_no, b.user_no FROM users a JOIN users b
                                              ON a.email='u05@mv.io' AND b.email='u06@mv.io';
INSERT INTO follows (follower_no, following_no)
SELECT a.user_no, b.user_no FROM users a JOIN users b
                                              ON a.email='u06@mv.io' AND b.email='u07@mv.io';
INSERT INTO follows (follower_no, following_no)
SELECT a.user_no, b.user_no FROM users a JOIN users b
                                              ON a.email='u07@mv.io' AND b.email='u08@mv.io';
INSERT INTO follows (follower_no, following_no)
SELECT a.user_no, b.user_no FROM users a JOIN users b
                                              ON a.email='u08@mv.io' AND b.email='u09@mv.io';
INSERT INTO follows (follower_no, following_no)
SELECT a.user_no, b.user_no FROM users a JOIN users b
                                              ON a.email='u09@mv.io' AND b.email='u10@mv.io';
INSERT INTO follows (follower_no, following_no)
SELECT a.user_no, b.user_no FROM users a JOIN users b
                                              ON a.email='u10@mv.io' AND b.email='u11@mv.io';
INSERT INTO follows (follower_no, following_no)
SELECT a.user_no, b.user_no FROM users a JOIN users b
                                              ON a.email='u11@mv.io' AND b.email='u12@mv.io';
INSERT INTO follows (follower_no, following_no)
SELECT a.user_no, b.user_no FROM users a JOIN users b
                                              ON a.email='u12@mv.io' AND b.email='u01@mv.io';

-- 교차/보완
INSERT INTO follows (follower_no, following_no)
SELECT a.user_no, b.user_no FROM users a JOIN users b
                                              ON a.email='u01@mv.io' AND b.email='u03@mv.io';
INSERT INTO follows (follower_no, following_no)
SELECT a.user_no, b.user_no FROM users a JOIN users b
                                              ON a.email='u02@mv.io' AND b.email='u05@mv.io';
INSERT INTO follows (follower_no, following_no)
SELECT a.user_no, b.user_no FROM users a JOIN users b
                                              ON a.email='u04@mv.io' AND b.email='u02@mv.io';
INSERT INTO follows (follower_no, following_no)
SELECT a.user_no, b.user_no FROM users a JOIN users b
                                              ON a.email='u09@mv.io' AND b.email='u07@mv.io';

-- =========================
-- 4) LIKES (22건)
--  - 자기 리뷰 좋아요는 피함
--  - (user_no, review_no) UNIQUE 준수
--  - 리뷰를 (작성자이메일, movie_no)로 식별하여 review_no를 서브쿼리로 가져옴
-- =========================
-- u01 likes: (u02,5), (u03,7), (u05,1)
INSERT INTO likes (user_no, review_no)
SELECT liker.user_no, r.review_no
FROM users liker
         JOIN users author ON author.email='u02@mv.io'
         JOIN reviews r ON r.user_no=author.user_no AND r.movie_no=5
WHERE liker.email='u01@mv.io';
INSERT INTO likes (user_no, review_no)
SELECT liker.user_no, r.review_no
FROM users liker
         JOIN users author ON author.email='u03@mv.io'
         JOIN reviews r ON r.user_no=author.user_no AND r.movie_no=7
WHERE liker.email='u01@mv.io';
INSERT INTO likes (user_no, review_no)
SELECT liker.user_no, r.review_no
FROM users liker
         JOIN users author ON author.email='u05@mv.io'
         JOIN reviews r ON r.user_no=author.user_no AND r.movie_no=1
WHERE liker.email='u01@mv.io';

-- u02 likes: (u01,3), (u04,6), (u10,7)
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no
FROM users l
         JOIN users a ON a.email='u01@mv.io'
         JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=3
WHERE l.email='u02@mv.io';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no
FROM users l
         JOIN users a ON a.email='u04@mv.io'
         JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=6
WHERE l.email='u02@mv.io';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no
FROM users l
         JOIN users a ON a.email='u10@mv.io'
         JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=7
WHERE l.email='u02@mv.io';

-- u03 likes: (u07,4), (u05,14)
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no
FROM users l
         JOIN users a ON a.email='u07@mv.io'
         JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=4
WHERE l.email='u03@mv.io';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no
FROM users l
         JOIN users a ON a.email='u05@mv.io'
         JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=14
WHERE l.email='u03@mv.io';

-- u04 likes: (u03,9), (u06,10)
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no
FROM users l
         JOIN users a ON a.email='u03@mv.io'
         JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=9
WHERE l.email='u04@mv.io';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no
FROM users l
         JOIN users a ON a.email='u06@mv.io'
         JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=10
WHERE l.email='u04@mv.io';

-- u05 likes: (u08,13), (u01,7)
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no
FROM users l
         JOIN users a ON a.email='u08@mv.io'
         JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=13
WHERE l.email='u05@mv.io';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no
FROM users l
         JOIN users a ON a.email='u01@mv.io'
         JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=7
WHERE l.email='u05@mv.io';

-- u06 likes: (u09,15), (u05,1)
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no
FROM users l
         JOIN users a ON a.email='u09@mv.io'
         JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=15
WHERE l.email='u06@mv.io';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no
FROM users l
         JOIN users a ON a.email='u05@mv.io'
         JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=1
WHERE l.email='u06@mv.io';

-- u07 likes: (u10,2), (u02,12)
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no
FROM users l
         JOIN users a ON a.email='u10@mv.io'
         JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=2
WHERE l.email='u07@mv.io';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no
FROM users l
         JOIN users a ON a.email='u02@mv.io'
         JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=12
WHERE l.email='u07@mv.io';

-- u08 likes: (u01,3), (u09,5)
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no
FROM users l
         JOIN users a ON a.email='u01@mv.io'
         JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=3
WHERE l.email='u08@mv.io';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no
FROM users l
         JOIN users a ON a.email='u09@mv.io'
         JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=5
WHERE l.email='u08@mv.io';

-- u09 likes: (u07,11), (u03,7)
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no
FROM users l
         JOIN users a ON a.email='u07@mv.io'
         JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=11
WHERE l.email='u09@mv.io';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no
FROM users l
         JOIN users a ON a.email='u03@mv.io'
         JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=7
WHERE l.email='u09@mv.io';

-- u10 likes: (u04,2), (u12,9)
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no
FROM users l
         JOIN users a ON a.email='u04@mv.io'
         JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=2
WHERE l.email='u10@mv.io';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no
FROM users l
         JOIN users a ON a.email='u12@mv.io'
         JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=9
WHERE l.email='u10@mv.io';

-- u11 likes: (u06,10), (u05,14)
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no
FROM users l
         JOIN users a ON a.email='u06@mv.io'
         JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=10
WHERE l.email='u11@mv.io';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no
FROM users l
         JOIN users a ON a.email='u05@mv.io'
         JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=14
WHERE l.email='u11@mv.io';

-- u12 likes: (u01,7), (u09,15)
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no
FROM users l
         JOIN users a ON a.email='u01@mv.io'
         JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=7
WHERE l.email='u12@mv.io';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no
FROM users l
         JOIN users a ON a.email='u09@mv.io'
         JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=15
WHERE l.email='u12@mv.io';

COMMIT;
