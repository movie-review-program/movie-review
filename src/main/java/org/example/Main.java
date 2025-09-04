package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.example.controller.MovieController;
import org.example.util.DBManager;
import org.example.util.DBManagerImpl;

public class Main {
	public static void main(String[] args) throws ClassNotFoundException {
        // DB 접속 정보



        // DB 연결 확인
        try { 
    		DBManager n = new DBManagerImpl();
            Connection con = n.getConnection();
            System.out.println("✅ DB 연결 성공!");
            con.close();
        } catch (SQLException e) {
            System.out.println("❌ DB 연결 실패: " + e.getMessage());
        }

        // 기존 로직
        MovieController.fetchMovieWithGenre("20250830");
    }
}