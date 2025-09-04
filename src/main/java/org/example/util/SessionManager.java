package org.example.util;

import org.example.model.dto.User;


public interface SessionManager {
    
 
    void login(User user);

 
    void logout();

  
    User getCurrentUser();

   
    boolean isLoggedIn();

    
    String getCurrentUserName();
}
