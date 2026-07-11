package com.th.service;

import java.util.List;
import com.th.model.User;

public interface UserService {

    // Login
    User login(String email, String password);

    // Register
    boolean registerUser(User user);

    boolean isEmailExists(String email);

    // Admin
    List<User> getAllUsers();

    boolean updateUserStatus(
            int userId,
            String status);

    int getTotalUsers();

    List<User> getAllDeliveryAgents();
    
    boolean updateProfile(User user);

}