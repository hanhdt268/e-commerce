package com.example.graduationbe.service;

import com.example.graduationbe.entities.User;
import com.example.graduationbe.entities.UserRole;

import java.util.Set;

public interface UserService {

    void updateResetPasswordToken(String token, String email);

    User createUser(User user, Set<UserRole> userRole) throws Exception;

    User getUsername(String username);

    //get user by id
    User getUserById(Long userId);

    //update user
    User updateUser(User user);

    //delete user
    void deleteById(Long userId);

    User getByResetPasswordToken(String token);

    void updatePassword(User user, String newPassword);

}
