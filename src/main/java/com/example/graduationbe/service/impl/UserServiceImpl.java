package com.example.graduationbe.service.impl;

import com.example.graduationbe.entities.User;
import com.example.graduationbe.entities.UserRole;
import com.example.graduationbe.repository.RoleRepository;
import com.example.graduationbe.repository.UserRepository;
import com.example.graduationbe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder encoder;

    @Override
    public void updateResetPasswordToken(String token, String email) {
        User user = this.userRepository.findByEmail(email);
        if (user != null) {
            user.setResetPasswordToken(token);
            this.userRepository.save(user);
        } else {
            throw new UsernameNotFoundException("Could not find any customer with the email " + email);
        }
    }

    @Override
    public User createUser(User user, Set<UserRole> userRole) throws Exception {
        User local = this.userRepository.findByUsername(user.getUsername());
        if (local != null) {
            System.out.println("User is already there!!!");
            throw new Exception();
        } else {
            for (UserRole ur : userRole) {
                this.roleRepository.save(ur.getRole());
            }
            user.getUserRoles().addAll(userRole);
            local = this.userRepository.save(user);
        }
        return local;
    }

    @Override
    public User getUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public User getUserById(Long userId) {
        return this.userRepository.findById(userId).get();
    }

    @Override
    public User updateUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public void deleteById(Long userId) {
        this.userRepository.deleteById(userId);
    }

    @Override
    public User getByResetPasswordToken(String token) {
        return this.userRepository.findByResetPasswordToken(token);
    }

    @Override
    public void updatePassword(User user, String newPassword) {
        String encode = encoder.encode(newPassword);
        user.setPassword(encode);
        user.setResetPasswordToken(null);
        this.userRepository.save(user);
    }
}
