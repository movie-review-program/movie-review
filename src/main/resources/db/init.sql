-- =========================================
-- 1) USERS (20명) - .com 이메일 + 한국어 이름
-- =========================================
INSERT INTO users (email, password, name, join_date) VALUES
                                                         ('u01@mv.com','pw01!','김민수','2025-08-18 10:10:00'),
                                                         ('u02@mv.com','pw02!','이서연','2025-08-18 11:10:00'),
                                                         ('u03@mv.com','pw03!','박지훈','2025-08-18 12:10:00'),
                                                         ('u04@mv.com','pw04!','최유진','2025-08-18 13:10:00'),
                                                         ('u05@mv.com','pw05!','정하늘','2025-08-18 14:10:00'),
                                                         ('u06@mv.com','pw06!','강민재','2025-08-18 15:10:00'),
                                                         ('u07@mv.com','pw07!','윤소희','2025-08-18 16:10:00'),
                                                         ('u08@mv.com','pw08!','한지민','2025-08-18 17:10:00'),
                                                         ('u09@mv.com','pw09!','장우석','2025-08-18 18:10:00'),
                                                         ('u10@mv.com','pw10!','오세준','2025-08-18 19:10:00'),
                                                         ('u11@mv.com','pw11!','서지우','2025-08-18 20:10:00'),
                                                         ('u12@mv.com','pw12!','문예린','2025-08-18 21:10:00'),
                                                         ('u13@mv.com','pw13!','신동혁','2025-08-18 22:10:00'),
                                                         ('u14@mv.com','pw14!','배수빈','2025-08-18 23:10:00'),
                                                         ('u15@mv.com','pw15!','노지훈','2025-08-19 08:10:00'),
                                                         ('u16@mv.com','pw16!','임가은','2025-08-19 09:10:00'),
                                                         ('u17@mv.com','pw17!','조민지','2025-08-19 10:10:00'),
                                                         ('u18@mv.com','pw18!','하승우','2025-08-19 11:10:00'),
                                                         ('u19@mv.com','pw19!','공서현','2025-08-19 12:10:00'),
                                                         ('u20@mv.com','pw20!','권도윤','2025-08-19 13:10:00');

-- =========================================
-- 2) REVIEWS (각 4개 = 총 80개)
--  - (user_no, movie_no) UNIQUE 준수
--  - 영화 1..32 존재 가정
-- =========================================
-- u01: movies 1,5,9,13
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 5, '연출 탄탄, 마지막 반전 훌륭.', '2025-09-01 12:00:00', u.user_no, 1  FROM users u WHERE u.email='u01@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 4, '음악과 리듬이 좋다.',       '2025-09-02 09:10:00', u.user_no, 5  FROM users u WHERE u.email='u01@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 3, '전개는 무난, 배우 케미 굿.', '2025-09-03 10:05:00', u.user_no, 9  FROM users u WHERE u.email='u01@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 5, '올해 최고작 후보.',         '2025-09-04 20:10:00', u.user_no, 13 FROM users u WHERE u.email='u01@mv.com';

-- u02: 2,6,10,14
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 3, '초반이 늘어지지만 중반 이후 좋음.', '2025-09-01 13:30:00', u.user_no, 2  FROM users u WHERE u.email='u02@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 4, '디테일이 살아있다.',             '2025-09-02 15:20:00', u.user_no, 6  FROM users u WHERE u.email='u02@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 2, '호불호 강함, 난 별로.',           '2025-09-03 09:40:00', u.user_no, 10 FROM users u WHERE u.email='u02@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 5, '연기력이 모든 걸 이김.',         '2025-09-04 12:55:00', u.user_no, 14 FROM users u WHERE u.email='u02@mv.com';

-- u03: 3,7,11,15
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 5, '몰입감 최고. 재감상 의사 있음.', '2025-09-01 20:40:00', u.user_no, 3  FROM users u WHERE u.email='u03@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 2, '캐릭터 동기가 약함.',           '2025-09-02 18:10:00', u.user_no, 7  FROM users u WHERE u.email='u03@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 4, '연출이 세련됐다.',               '2025-09-03 21:05:00', u.user_no, 11 FROM users u WHERE u.email='u03@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 3, '잔잔하지만 여운 큼.',           '2025-09-04 08:20:00', u.user_no, 15 FROM users u WHERE u.email='u03@mv.com';

-- u04: 4,8,12,16
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 4, '배우들의 합이 좋다.',          '2025-09-01 15:22:00', u.user_no, 4  FROM users u WHERE u.email='u04@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 3, '전개가 조금 루즈.',            '2025-09-02 22:05:00', u.user_no, 8  FROM users u WHERE u.email='u04@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 5, '완성도 높은 장르물.',          '2025-09-03 11:11:00', u.user_no, 12 FROM users u WHERE u.email='u04@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 2, '편집이 산만함.',              '2025-09-04 09:33:00', u.user_no, 16 FROM users u WHERE u.email='u04@mv.com';

-- u05: 5,9,13,17
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 5, '연출/연기/음악 모두 훌륭.',    '2025-09-01 11:11:00', u.user_no, 5  FROM users u WHERE u.email='u05@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 4, '의외의 반전이 매력적.',        '2025-09-02 17:05:00', u.user_no, 9  FROM users u WHERE u.email='u05@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 3, '호흡이 길다. 취향 탈 듯.',     '2025-09-03 18:20:00', u.user_no, 13 FROM users u WHERE u.email='u05@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 4, '대사 맛집. 재밌다.',          '2025-09-04 19:10:00', u.user_no, 17 FROM users u WHERE u.email='u05@mv.com';

-- u06: 6,10,14,18
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 2, '호불호가 크게 갈릴 듯.',       '2025-09-02 12:12:00', u.user_no, 6  FROM users u WHERE u.email='u06@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 4, '완성도 있는 전개.',            '2025-09-03 09:45:00', u.user_no, 10 FROM users u WHERE u.email='u06@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 3, '배경음이 과함.',               '2025-09-04 13:15:00', u.user_no, 14 FROM users u WHERE u.email='u06@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 5, '연기 미쳤다. 강추.',            '2025-09-05 10:55:00', u.user_no, 18 FROM users u WHERE u.email='u06@mv.com';

-- u07: 7,11,15,19
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 3, '캐릭터 매력이 살짝 부족.',     '2025-09-01 09:00:00', u.user_no, 7  FROM users u WHERE u.email='u07@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 5, '연출 미쳤다. 몰입도 최고.',    '2025-09-02 13:33:00', u.user_no, 11 FROM users u WHERE u.email='u07@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 4, '색 보정이 예술.',              '2025-09-03 20:22:00', u.user_no, 15 FROM users u WHERE u.email='u07@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 2, '설정 과함. 아쉬움.',           '2025-09-04 07:40:00', u.user_no, 19 FROM users u WHERE u.email='u07@mv.com';

-- u08: 8,12,16,20
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 4, '잔잔하지만 여운이 큼.',        '2025-09-02 08:08:00', u.user_no, 8  FROM users u WHERE u.email='u08@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 3, '평균 이상, 무난.',             '2025-09-03 19:10:00', u.user_no, 12 FROM users u WHERE u.email='u08@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 5, '감성 폭발. 취향 저격.',        '2025-09-04 09:20:00', u.user_no, 16 FROM users u WHERE u.email='u08@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 4, '연출/미장센 인상적.',          '2025-09-05 21:05:00', u.user_no, 20 FROM users u WHERE u.email='u08@mv.com';

-- u09: 9,13,17,21
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 5, '마지막 20분이 압권.',          '2025-09-01 21:10:00', u.user_no, 9  FROM users u WHERE u.email='u09@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 4, '사운드 디자인 좋음.',          '2025-09-02 16:40:00', u.user_no, 13 FROM users u WHERE u.email='u09@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 3, '스토리는 평범.',               '2025-09-03 18:22:00', u.user_no, 17 FROM users u WHERE u.email='u09@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 5, '연출과 리듬이 뛰어남.',        '2025-09-04 12:10:00', u.user_no, 21 FROM users u WHERE u.email='u09@mv.com';

-- u10: 10,14,18,22
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 3, '중반부가 아쉬움.',             '2025-09-03 07:25:00', u.user_no, 10 FROM users u WHERE u.email='u10@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 5, '올해 최고작 후보.',            '2025-09-04 12:55:00', u.user_no, 14 FROM users u WHERE u.email='u10@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 4, '연기가 극을 끌고 간다.',       '2025-09-05 08:40:00', u.user_no, 18 FROM users u WHERE u.email='u10@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 2, '편집이 아쉬움.',               '2025-09-06 10:00:00', u.user_no, 22 FROM users u WHERE u.email='u10@mv.com';

-- u11: 11,15,19,23
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 4, '균형 잡힌 작품.',               '2025-09-02 20:20:00', u.user_no, 11 FROM users u WHERE u.email='u11@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 3, '아이디어는 좋음.',             '2025-09-03 14:12:00', u.user_no, 15 FROM users u WHERE u.email='u11@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 5, '대사 한 줄 한 줄 명품.',       '2025-09-04 09:18:00', u.user_no, 19 FROM users u WHERE u.email='u11@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 4, '보는 재미 확실.',              '2025-09-05 11:35:00', u.user_no, 23 FROM users u WHERE u.email='u11@mv.com';

-- u12: 12,16,20,24
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 2, '취향은 아니었음.',            '2025-09-03 18:45:00', u.user_no, 12 FROM users u WHERE u.email='u12@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 3, '독특한 미장센.',               '2025-09-04 20:05:00', u.user_no, 16 FROM users u WHERE u.email='u12@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 4, '잔잔한 매력.',                 '2025-09-05 09:19:00', u.user_no, 20 FROM users u WHERE u.email='u12@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 5, '마무리가 깔끔.',               '2025-09-06 21:21:00', u.user_no, 24 FROM users u WHERE u.email='u12@mv.com';

-- u13: 13,17,21,25
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 5, '연출이 아주 노련하다.',        '2025-09-01 10:10:00', u.user_no, 13 FROM users u WHERE u.email='u13@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 4, '배우 합이 좋음.',              '2025-09-02 10:20:00', u.user_no, 17 FROM users u WHERE u.email='u13@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 3, '후반이 아쉬움.',               '2025-09-03 10:30:00', u.user_no, 21 FROM users u WHERE u.email='u13@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 5, '감상 후 여운이 오래감.',       '2025-09-04 10:40:00', u.user_no, 25 FROM users u WHERE u.email='u13@mv.com';

-- u14: 14,18,22,26
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 4, '촬영 미장센이 뛰어남.',        '2025-09-01 11:10:00', u.user_no, 14 FROM users u WHERE u.email='u14@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 2, '설정 과함.',                   '2025-09-02 11:20:00', u.user_no, 18 FROM users u WHERE u.email='u14@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 3, '중반 템포 처짐.',              '2025-09-03 11:30:00', u.user_no, 22 FROM users u WHERE u.email='u14@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 5, '클래식한 매력.',               '2025-09-04 11:40:00', u.user_no, 26 FROM users u WHERE u.email='u14@mv.com';

-- u15: 15,19,23,27
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 5, '음악과 연출 조합이 좋다.',     '2025-09-01 12:10:00', u.user_no, 15 FROM users u WHERE u.email='u15@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 4, '메시지가 분명.',               '2025-09-02 12:20:00', u.user_no, 19 FROM users u WHERE u.email='u15@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 2, '연기가 아쉬움.',               '2025-09-03 12:30:00', u.user_no, 23 FROM users u WHERE u.email='u15@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 3, '후반 연출이 살림.',            '2025-09-04 12:40:00', u.user_no, 27 FROM users u WHERE u.email='u15@mv.com';

-- u16: 16,20,24,28
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 4, '보는 재미 확실.',              '2025-09-01 13:10:00', u.user_no, 16 FROM users u WHERE u.email='u16@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 5, '연출/음악 완성도 높음.',       '2025-09-02 13:20:00', u.user_no, 20 FROM users u WHERE u.email='u16@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 3, '클리셰가 조금 많다.',          '2025-09-03 13:30:00', u.user_no, 24 FROM users u WHERE u.email='u16@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 2, '편집 호흡이 맞지 않음.',       '2025-09-04 13:40:00', u.user_no, 28 FROM users u WHERE u.email='u16@mv.com';

-- u17: 17,21,25,29
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 5, '연출이 아주 안정적.',          '2025-09-01 14:10:00', u.user_no, 17 FROM users u WHERE u.email='u17@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 4, '전개 깔끔.',                   '2025-09-02 14:20:00', u.user_no, 21 FROM users u WHERE u.email='u17@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 3, '초반 몰입도 보통.',            '2025-09-03 14:30:00', u.user_no, 25 FROM users u WHERE u.email='u17@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 5, '후반 몰아치기 훌륭.',          '2025-09-04 14:40:00', u.user_no, 29 FROM users u WHERE u.email='u17@mv.com';

-- u18: 18,22,26,30
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 2, '톤이 일관되지 않음.',          '2025-09-01 15:10:00', u.user_no, 18 FROM users u WHERE u.email='u18@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 4, '후반 연출이 매력.',            '2025-09-02 15:20:00', u.user_no, 22 FROM users u WHERE u.email='u18@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 5, '캐릭터 빌드 훌륭.',            '2025-09-03 15:30:00', u.user_no, 26 FROM users u WHERE u.email='u18@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 3, '연출은 좋으나 호흡은 긴 편.',  '2025-09-04 15:40:00', u.user_no, 30 FROM users u WHERE u.email='u18@mv.com';

-- u19: 19,23,27,31
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 4, '메시지 전달력 좋음.',          '2025-09-01 16:10:00', u.user_no, 19 FROM users u WHERE u.email='u19@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 5, '연출/연기 시너지가 대단.',     '2025-09-02 16:20:00', u.user_no, 23 FROM users u WHERE u.email='u19@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 2, '기대보다 밋밋.',               '2025-09-03 16:30:00', u.user_no, 27 FROM users u WHERE u.email='u19@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 4, '연출 구성 좋음.',              '2025-09-04 16:40:00', u.user_no, 31 FROM users u WHERE u.email='u19@mv.com';

-- u20: 20,24,28,32
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 3, '유니크한 설정이 돋보임.',      '2025-09-01 17:10:00', u.user_no, 20 FROM users u WHERE u.email='u20@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 5, '엔딩이 강렬하다.',             '2025-09-02 17:20:00', u.user_no, 24 FROM users u WHERE u.email='u20@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 4, '리듬감 좋은 장르물.',          '2025-09-03 17:30:00', u.user_no, 28 FROM users u WHERE u.email='u20@mv.com';
INSERT INTO reviews (rating, content, reg_date, user_no, movie_no)
SELECT 2, '호흡 길고 늘어짐.',            '2025-09-04 17:40:00', u.user_no, 32 FROM users u WHERE u.email='u20@mv.com';

-- =========================================
-- 3) FOLLOWS (각자 5명 팔로우 = 100행)
--  - 자기 자신 제외, 유니크 준수
--  - 한 번에 5명씩 지정 (IN 리스트)
-- =========================================
INSERT INTO follows (follower_no, following_no)
SELECT f.user_no, t.user_no FROM users f JOIN users t ON t.email IN ('u02@mv.com','u03@mv.com','u04@mv.com','u05@mv.com','u06@mv.com') WHERE f.email='u01@mv.com';
INSERT INTO follows (follower_no, following_no)
SELECT f.user_no, t.user_no FROM users f JOIN users t ON t.email IN ('u03@mv.com','u04@mv.com','u05@mv.com','u06@mv.com','u07@mv.com') WHERE f.email='u02@mv.com';
INSERT INTO follows (follower_no, following_no)
SELECT f.user_no, t.user_no FROM users f JOIN users t ON t.email IN ('u04@mv.com','u05@mv.com','u06@mv.com','u07@mv.com','u08@mv.com') WHERE f.email='u03@mv.com';
INSERT INTO follows (follower_no, following_no)
SELECT f.user_no, t.user_no FROM users f JOIN users t ON t.email IN ('u05@mv.com','u06@mv.com','u07@mv.com','u08@mv.com','u09@mv.com') WHERE f.email='u04@mv.com';
INSERT INTO follows (follower_no, following_no)
SELECT f.user_no, t.user_no FROM users f JOIN users t ON t.email IN ('u06@mv.com','u07@mv.com','u08@mv.com','u09@mv.com','u10@mv.com') WHERE f.email='u05@mv.com';
INSERT INTO follows (follower_no, following_no)
SELECT f.user_no, t.user_no FROM users f JOIN users t ON t.email IN ('u07@mv.com','u08@mv.com','u09@mv.com','u10@mv.com','u11@mv.com') WHERE f.email='u06@mv.com';
INSERT INTO follows (follower_no, following_no)
SELECT f.user_no, t.user_no FROM users f JOIN users t ON t.email IN ('u08@mv.com','u09@mv.com','u10@mv.com','u11@mv.com','u12@mv.com') WHERE f.email='u07@mv.com';
INSERT INTO follows (follower_no, following_no)
SELECT f.user_no, t.user_no FROM users f JOIN users t ON t.email IN ('u09@mv.com','u10@mv.com','u11@mv.com','u12@mv.com','u13@mv.com') WHERE f.email='u08@mv.com';
INSERT INTO follows (follower_no, following_no)
SELECT f.user_no, t.user_no FROM users f JOIN users t ON t.email IN ('u10@mv.com','u11@mv.com','u12@mv.com','u13@mv.com','u14@mv.com') WHERE f.email='u09@mv.com';
INSERT INTO follows (follower_no, following_no)
SELECT f.user_no, t.user_no FROM users f JOIN users t ON t.email IN ('u11@mv.com','u12@mv.com','u13@mv.com','u14@mv.com','u15@mv.com') WHERE f.email='u10@mv.com';
INSERT INTO follows (follower_no, following_no)
SELECT f.user_no, t.user_no FROM users f JOIN users t ON t.email IN ('u12@mv.com','u13@mv.com','u14@mv.com','u15@mv.com','u16@mv.com') WHERE f.email='u11@mv.com';
INSERT INTO follows (follower_no, following_no)
SELECT f.user_no, t.user_no FROM users f JOIN users t ON t.email IN ('u13@mv.com','u14@mv.com','u15@mv.com','u16@mv.com','u17@mv.com') WHERE f.email='u12@mv.com';
INSERT INTO follows (follower_no, following_no)
SELECT f.user_no, t.user_no FROM users f JOIN users t ON t.email IN ('u14@mv.com','u15@mv.com','u16@mv.com','u17@mv.com','u18@mv.com') WHERE f.email='u13@mv.com';
INSERT INTO follows (follower_no, following_no)
SELECT f.user_no, t.user_no FROM users f JOIN users t ON t.email IN ('u15@mv.com','u16@mv.com','u17@mv.com','u18@mv.com','u19@mv.com') WHERE f.email='u14@mv.com';
INSERT INTO follows (follower_no, following_no)
SELECT f.user_no, t.user_no FROM users f JOIN users t ON t.email IN ('u16@mv.com','u17@mv.com','u18@mv.com','u19@mv.com','u20@mv.com') WHERE f.email='u15@mv.com';
INSERT INTO follows (follower_no, following_no)
SELECT f.user_no, t.user_no FROM users f JOIN users t ON t.email IN ('u17@mv.com','u18@mv.com','u19@mv.com','u20@mv.com','u01@mv.com') WHERE f.email='u16@mv.com';
INSERT INTO follows (follower_no, following_no)
SELECT f.user_no, t.user_no FROM users f JOIN users t ON t.email IN ('u18@mv.com','u19@mv.com','u20@mv.com','u01@mv.com','u02@mv.com') WHERE f.email='u17@mv.com';
INSERT INTO follows (follower_no, following_no)
SELECT f.user_no, t.user_no FROM users f JOIN users t ON t.email IN ('u19@mv.com','u20@mv.com','u01@mv.com','u02@mv.com','u03@mv.com') WHERE f.email='u18@mv.com';
INSERT INTO follows (follower_no, following_no)
SELECT f.user_no, t.user_no FROM users f JOIN users t ON t.email IN ('u20@mv.com','u01@mv.com','u02@mv.com','u03@mv.com','u04@mv.com') WHERE f.email='u19@mv.com';
INSERT INTO follows (follower_no, following_no)
SELECT f.user_no, t.user_no FROM users f JOIN users t ON t.email IN ('u01@mv.com','u02@mv.com','u03@mv.com','u04@mv.com','u05@mv.com') WHERE f.email='u20@mv.com';

-- =========================================
-- 4) LIKES (각자 6개 = 총 120행)
--  - 자기 리뷰 좋아요 금지
--  - (작성자 email, movie_no)로 review_no 서브쿼리
-- =========================================
-- 헬퍼: 다음 사용자/그 다음 사용자 리뷰에 분산해서 좋아요
-- u01 likes: u02(2), u03(7), u04(12), u05(17), u06(6), u07(11)
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l
                                       JOIN users a ON a.email='u02@mv.com'
                                       JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=2
WHERE l.email='u01@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u03@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=7 WHERE l.email='u01@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u04@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=12 WHERE l.email='u01@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u05@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=17 WHERE l.email='u01@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u06@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=6 WHERE l.email='u01@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u07@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=11 WHERE l.email='u01@mv.com';

-- u02 likes: u03(3), u04(8), u05(13), u06(18), u07(7), u08(12)
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u03@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=3 WHERE l.email='u02@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u04@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=8 WHERE l.email='u02@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u05@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=13 WHERE l.email='u02@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u06@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=18 WHERE l.email='u02@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u07@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=7 WHERE l.email='u02@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u08@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=12 WHERE l.email='u02@mv.com';

-- u03 likes: u04(4), u05(9), u06(14), u07(19), u08(8), u09(13)
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u04@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=4 WHERE l.email='u03@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u05@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=9 WHERE l.email='u03@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u06@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=14 WHERE l.email='u03@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u07@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=19 WHERE l.email='u03@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u08@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=8 WHERE l.email='u03@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u09@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=13 WHERE l.email='u03@mv.com';

-- u04 likes: u05(5), u06(10), u07(15), u08(20), u09(9), u10(14)
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u05@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=5 WHERE l.email='u04@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u06@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=10 WHERE l.email='u04@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u07@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=15 WHERE l.email='u04@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u08@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=20 WHERE l.email='u04@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u09@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=9 WHERE l.email='u04@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u10@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=14 WHERE l.email='u04@mv.com';

-- u05 likes: u06(6), u07(11), u08(16), u09(21), u10(10), u11(15)
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u06@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=6 WHERE l.email='u05@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u07@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=11 WHERE l.email='u05@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u08@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=16 WHERE l.email='u05@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u09@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=21 WHERE l.email='u05@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u10@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=10 WHERE l.email='u05@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u11@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=15 WHERE l.email='u05@mv.com';

-- u06 likes: u07(7), u08(12), u09(17), u10(22), u11(11), u12(16)
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u07@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=7 WHERE l.email='u06@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u08@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=12 WHERE l.email='u06@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u09@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=17 WHERE l.email='u06@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u10@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=22 WHERE l.email='u06@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u11@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=11 WHERE l.email='u06@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u12@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=16 WHERE l.email='u06@mv.com';

-- u07 likes: u08(8), u09(13), u10(18), u11(23), u12(12), u13(17)
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u08@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=8 WHERE l.email='u07@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u09@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=13 WHERE l.email='u07@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u10@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=18 WHERE l.email='u07@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u11@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=23 WHERE l.email='u07@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u12@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=12 WHERE l.email='u07@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u13@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=17 WHERE l.email='u07@mv.com';

-- u08 likes: u09(9), u10(14), u11(19), u12(24), u13(13), u14(18)
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u09@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=9 WHERE l.email='u08@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u10@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=14 WHERE l.email='u08@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u11@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=19 WHERE l.email='u08@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u12@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=24 WHERE l.email='u08@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u13@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=13 WHERE l.email='u08@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u14@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=18 WHERE l.email='u08@mv.com';

-- u09 likes: u10(10), u11(15), u12(20), u13(25), u14(14), u15(19)
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u10@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=10 WHERE l.email='u09@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u11@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=15 WHERE l.email='u09@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u12@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=20 WHERE l.email='u09@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u13@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=25 WHERE l.email='u09@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u14@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=14 WHERE l.email='u09@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u15@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=19 WHERE l.email='u09@mv.com';

-- u10 likes: u11(11), u12(16), u13(21), u14(26), u15(15), u16(20)
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u11@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=11 WHERE l.email='u10@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u12@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=16 WHERE l.email='u10@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u13@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=21 WHERE l.email='u10@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u14@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=26 WHERE l.email='u10@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u15@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=15 WHERE l.email='u10@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u16@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=20 WHERE l.email='u10@mv.com';

-- u11 likes: u12(12), u13(17), u14(22), u15(27), u16(16), u17(21)
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u12@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=12 WHERE l.email='u11@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u13@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=17 WHERE l.email='u11@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u14@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=22 WHERE l.email='u11@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u15@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=27 WHERE l.email='u11@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u16@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=16 WHERE l.email='u11@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u17@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=21 WHERE l.email='u11@mv.com';

-- u12 likes: u13(13), u14(18), u15(23), u16(28), u17(17), u18(22)
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u13@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=13 WHERE l.email='u12@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u14@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=18 WHERE l.email='u12@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u15@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=23 WHERE l.email='u12@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u16@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=28 WHERE l.email='u12@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u17@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=17 WHERE l.email='u12@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u18@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=22 WHERE l.email='u12@mv.com';

-- u13 likes: u14(14), u15(19), u16(24), u17(29), u18(18), u19(23)
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u14@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=14 WHERE l.email='u13@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u15@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=19 WHERE l.email='u13@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u16@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=24 WHERE l.email='u13@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u17@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=29 WHERE l.email='u13@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u18@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=18 WHERE l.email='u13@mv.com';
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no FROM users l JOIN users a ON a.email='u19@mv.com'
                                           JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=23 WHERE l.email='u13@mv.com';

-- u14 likes: u15(15), u16(20), u17(25), u18(30), u19(19), u20(24)
INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no
FROM users l
         JOIN users a ON a.email='u15@mv.com'
         JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=15
WHERE l.email='u14@mv.com';

INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no
FROM users l
         JOIN users a ON a.email='u16@mv.com'
         JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=20
WHERE l.email='u14@mv.com';

INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no
FROM users l
         JOIN users a ON a.email='u17@mv.com'
         JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=25
WHERE l.email='u14@mv.com';

INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no
FROM users l
         JOIN users a ON a.email='u18@mv.com'
         JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=30
WHERE l.email='u14@mv.com';

INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no
FROM users l
         JOIN users a ON a.email='u19@mv.com'
         JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=19
WHERE l.email='u14@mv.com';

INSERT INTO likes (user_no, review_no)
SELECT l.user_no, r.review_no
FROM users l
         JOIN users a ON a.email='u20@mv.com'
         JOIN reviews r ON r.user_no=a.user_no AND r.movie_no=24
WHERE l.email='u14@mv.com';


