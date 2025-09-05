package org.example.model.service;

import java.sql.SQLException;
import org.example.model.dao.UserDAO;
import org.example.model.dao.UserDAOImpl;
import org.example.model.dto.User;

public class UserServiceImpl implements UserService {
    private UserDAO dao = new UserDAOImpl();

    @Override
    public User registerAndLogin(User user) throws SQLException, Exception {
        registerUser(user);
        return login(user.getEmail(), user.getPassword());
    }

    @Override
    public User login(String email, String password) throws SQLException, Exception {
        try {
            
            User user = dao.selectUserByUseremailAndPassword(email, password);
            if (user == null) {
                throw new Exception("이메일 또는 비밀번호가 올바르지 않습니다.");
            }
            return user;
        } catch (SQLException e) {
            throw new SQLException("DB 오류: 로그인 실패", e);
        }
    }

    @Override
    public User getUserByUserNo(int userNo) throws Exception {
        try {
            User user = dao.selectUserByUserNo(userNo);
            if (user == null) {
                throw new Exception("해당 회원을 찾을 수 없습니다.");
            }
            return user;
        } catch (SQLException e) {
            throw new SQLException("DB 오류: 회원 조회 실패", e);
        }
    }


    private boolean registerUser(User userDTO) throws SQLException, Exception {
        if (userDTO == null) throw new Exception("회원 정보가 비어있습니다.");
        if (userDTO.getEmail() == null || userDTO.getEmail().trim().isEmpty()) throw new Exception("이메일은 필수 입력 항목입니다.");
        if (!userDTO.getEmail().contains("@")) throw new Exception("올바른 이메일 형식이 아닙니다.");
        if (userDTO.getPassword() == null || userDTO.getPassword().length() < 4) throw new Exception("비밀번호는 4자리 이상이어야 합니다.");
        if (userDTO.getName() == null || userDTO.getName().length() < 2) throw new Exception("이름은 2글자 이상이어야 합니다.");

        try {
            if (dao.isEmailDuplicate(userDTO.getEmail())) {
                throw new Exception("이미 존재하는 이메일입니다.");
            }
            if (!dao.registerUser(userDTO)) {
                throw new SQLException("DB 오류: 회원가입 실패");
            }
        } catch (SQLException e) {
            throw new SQLException("DB 오류: 회원가입 처리 중 실패", e);
        }
        return true;
    }
}
