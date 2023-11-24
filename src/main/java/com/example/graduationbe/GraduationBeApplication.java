package com.example.graduationbe;

import com.example.graduationbe.repository.RoleRepository;
import com.example.graduationbe.repository.UserRepository;
import com.example.graduationbe.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class GraduationBeApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(GraduationBeApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("start server");

//        User user = new User();
//
//        user.setUsername("shipper2");
//        user.setPassword(this.passwordEncoder.encode("123"));
//        user.setPhone("0312458965");
//        user.setFullName("Lê Văn Minh");
//        user.setAddress("da nang");
//        user.setEmail("shipper123@gmail.com");
//        Set<UserRole> userRoleSet = new HashSet<>();
//        Role role = new Role();
//        role.setRoleId(3L);
//        role.setRoleName("Shipper");
//        UserRole userRole = new UserRole();
//        userRole.setUser(user);
//        userRole.setRole(role);
//        userRoleSet.add(userRole);
//        this.userService.createUser(user, userRoleSet);
    }
}
