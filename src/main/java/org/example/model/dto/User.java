package org.example.model.dto;

import java.time.LocalDateTime;


public class User {

    
    private int userNo;

   
    private String email;

   
    private String password;

   
    private String name;


    private LocalDateTime joinDate;

  
    public User() {}


    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

   
    public User(int userNo, String email, String password, String name, LocalDateTime joinDate) {
        this.userNo = userNo;
        this.email = email;
        this.password = password;
        this.name = name;
        this.joinDate = joinDate;
    }

   

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
