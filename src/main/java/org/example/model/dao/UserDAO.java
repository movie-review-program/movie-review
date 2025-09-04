package org.example.model.dao;

import org.example.model.dto.User;
import java.sql.SQLException;

public interface UserDAO {
    boolean isEmailDuplicate(String email) throws SQLException;
    boolean registerUser(User user) throws SQLException;        
    User login(String email, String password) throws SQLException; 
}
