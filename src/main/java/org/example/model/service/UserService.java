package org.example.model.service;

import org.example.model.dto.User;


public interface UserService {
 
    User login(String email, String password) throws Exception;
    User registerAndLogin(User user) throws Exception; 
}
