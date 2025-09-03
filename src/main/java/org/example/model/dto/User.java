package org.example.model.dto;

import java.time.LocalDateTime;

/**
 * User DTO (Data Transfer Object)
 * - 사용자 정보를 저장하고 전달하는 객체
 * - DB와 Controller/Service 간 데이터 전달 용도
 */
public class User {

    // 사용자 고유 번호 (DB PK)
    private int userNo;

    // 사용자 이메일 (로그인 ID 역할)
    private String email;

    // 사용자 비밀번호
    private String password;

    // 사용자 이름
    private String name;

    // 회원 가입 일시
    private LocalDateTime joinDate;

    // ================== 생성자 ==================

    // 기본 생성자
    public User() {}

    /**
     * 회원가입용 생성자
     * userNo, joinDate는 DB에서 자동 생성되므로 제외
     * @param email 사용자 이메일
     * @param password 사용자 비밀번호
     * @param name 사용자 이름
     */
    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    /**
     * 모든 필드를 포함한 생성자
     * @param userNo 사용자 번호
     * @param email 이메일
     * @param password 비밀번호
     * @param name 이름
     * @param joinDate 가입일
     */
    public User(int userNo, String email, String password, String name, LocalDateTime joinDate) {
        this.userNo = userNo;
        this.email = email;
        this.password = password;
        this.name = name;
        this.joinDate = joinDate;
    }

    // ================== Getter / Setter ==================

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDateTime joinDate) {
        this.joinDate = joinDate;
    }

    // ================== toString ==================
    @Override
    public String toString() {
        return "UserDTO{" +
                "userNo=" + userNo +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", joinDate=" + joinDate +
                '}';
    }
}
