package org.example.model.dao;

import java.util.ArrayList;
import java.util.List;
import org.example.model.dto.User;

/**
 * UserDAO 메모리 구현체
 * - UserDAO 인터페이스를 메모리 리스트로 구현
 */
public class UserDAOImpl implements UserDAO {
    
    private List<User> users = new ArrayList<>();
    
    @Override
    public boolean addUser(User user) {
        return users.add(user);
    }
    
    @Override
    public User findByEmail(String email) {
        return users.stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }
    
    @Override
    public boolean isEmailExist(String email) {
        return findByEmail(email) != null;
    }
}