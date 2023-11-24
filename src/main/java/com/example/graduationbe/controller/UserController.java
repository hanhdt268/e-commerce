package com.example.graduationbe.controller;


import com.example.graduationbe.entities.Role;
import com.example.graduationbe.entities.User;
import com.example.graduationbe.entities.UserRole;
import com.example.graduationbe.entities.commerce.Email;
import com.example.graduationbe.repository.UserRepository;
import com.example.graduationbe.service.UserService;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("user")
@CrossOrigin("*")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String sender;

    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User user) throws Exception {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAvatar("https://udayananetworking.unud.ac.id/assets/frontend/images/user-m.png");
        Set<UserRole> roleSet = new HashSet<>();
        Role role = new Role();
        role.setRoleId(2L);
        role.setRoleName("User");
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        roleSet.add(userRole);
        return ResponseEntity.ok(this.userService.createUser(user, roleSet));
    }

    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String username) {
        return this.userService.getUsername(username);
    }

    //update username
    @PutMapping("/")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User u = this.userService.getUserById(user.getUserID());
        if (u == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(u);
        } else {
            user.setUserRoles(u.getUserRoles());
            user.setPassword(u.getPassword());
            user.setUsername(u.getUsername());
            return ResponseEntity.ok(this.userService.updateUser(user));
        }
    }


    @PutMapping("/avatar")
    public ResponseEntity<User> changeAvatar(@RequestBody User user) {
        User u = this.userService.getUserById(user.getUserID());
        if (u == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(u);
        } else {
            user.setUserRoles(u.getUserRoles());
            user.setPassword(u.getPassword());
            user.setUsername(u.getUsername());
            user.setAddress(u.getAddress());
            user.setEmail(u.getEmail());
            user.setFullName(u.getFullName());
            user.setPhone(u.getPhone());
            return ResponseEntity.ok(this.userService.updateUser(user));
        }
    }

    @GetMapping("/getAllUser")
    public List<User> getAllUser() {
        return this.userRepository.getAllUserByRoles();
    }

    @GetMapping("/lockAccount/{userId}")
    public void lockAccount(@PathVariable("userId") Long userId) {
        User user = this.userService.getUserById(userId);
        if (user != null) {
            user.setEnabled(false);
            this.userRepository.save(user);
        }
    }

    @GetMapping("/unLockAccount/{userId}")
    public void unLockAccount(@PathVariable("userId") Long userId) {
        User user = this.userService.getUserById(userId);
        if (user != null) {
            user.setEnabled(true);
            this.userRepository.save(user);
        }
    }

    @GetMapping("/getUserByEmail/{email}")
    public User test(@PathVariable("email") String email) {
        return this.userRepository.findByEmail(email);
    }

    //delete by id
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        this.userService.deleteById(id);
    }

    @PostMapping("/forgetPassword/")
    public void processForgotPassword(@RequestBody Email email) throws MessagingException {
        String token = RandomString.make(30);
        try {
            this.userService.updateResetPasswordToken(token, email.getEmail());
            String resetPasswordLink = "http://localhost:4200/reset-password?token=" + token;
            email(email.getEmail(), resetPasswordLink);
        } catch (UsernameNotFoundException us) {
            us.getMessage();
        }
    }

    @PostMapping("/reset-password/{token}")
    public User resetPassword(@PathVariable("token") String token, @RequestBody User user) {
        User u = this.userRepository.findByResetPasswordToken(token);
        if (u != null) {
            this.userService.updatePassword(u, user.getPassword());
        } else {
            throw new UsernameNotFoundException("User not found");
        }
        return u;
    }

    public String email(String recipientEmail, String link) throws MessagingException {
        MimeMessage mailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mailMessage);
        mailMessage.setFrom(sender);
        mailMessage.setRecipients(MimeMessage.RecipientType.TO, recipientEmail);
        mailMessage.setSubject("Here's the link to reset your password");
        mailMessage.setContent("<p>Hello,</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + link + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>", "text/html; charset=utf-8");
        this.javaMailSender.send(mailMessage);
        return "send mail success";
    }
}
