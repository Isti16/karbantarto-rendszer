package com.taskmanager.service;

import java.util.List;

import com.taskmanager.model.User;

public interface UserService {
    User createUser(User user);

    User changeRoleToAdmin(User user);
    
    User changeEducation(User user);

    List<User> findAll();

    User getUserByEmail(String email);

    boolean isUserEmailPresent(String email);

    User getUserById(Long userId);

    void deleteUser(Long id);
}
 