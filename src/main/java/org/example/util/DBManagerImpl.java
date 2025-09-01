package org.example.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBManagerImpl implements DBManager {
    private static final String URL;
    private static final String USER_ID;
    private static final String USER_PASS;
    private static final String DRIVER;

    /*
     * 로드
     * */
    static {
        Properties p = new Properties();
        try (InputStream in =
                     DBManagerImpl.class.getResourceAsStream("/application.properties")) {
            p.load(in);

        } catch (Exception e) {
            throw new ExceptionInInitializerError("프로퍼티 값을 불러오는데 실패하였습니다.: " + e.getMessage());
        }

        DRIVER    = p.getProperty("db.driver");
        URL       = p.getProperty("db.url");
        USER_ID   = p.getProperty("db.user");
        USER_PASS = p.getProperty("db.pass");

        try  {
            Class.forName(
                    DRIVER
            );
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("mysql 로드에 실패하였습니다." + e.getMessage());
        }
    }

    /*
     * 연결
     * */
    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                URL,
                USER_ID,
                USER_PASS
        );
    }

    /*
     * 닫기 (select 전용)
     * */
    void releaseConnection(Connection con, Statement st, ResultSet rs) {
        try {
            if (rs != null)
                rs.close();
            releaseConnection(con, st);
        } catch (SQLException e) {
            throw new RuntimeException("mysql 닫기에 실패하였습니다." + e.getMessage());
        }
    }

    /*
     * 닫기 (DML 전용)
     * */
    void releaseConnection(Connection con, Statement st) {
        try {
            if (st != null)
                st.close();
            if (con != null)
                con.close();
        } catch (SQLException e) {
            throw new RuntimeException("mysql 닫기에 실패하였습니다." + e.getMessage());
        }
    }


}

