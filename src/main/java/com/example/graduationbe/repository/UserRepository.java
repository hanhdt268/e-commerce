package com.example.graduationbe.repository;

import com.example.graduationbe.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByUserID(Long userId);

    @Query(value = "select u.* from user u inner join user_role ur on u.userid = ur.user_userid inner join role on ur.role_role_id = role.role_id\n" +
            "where role.role_name='Shipper'", nativeQuery = true)
    List<User> findByUserRole();

    @Query("select u from User u where u.email=?1")
    User findByEmail(String email);

    @Query(value = "select u.* from user u inner  join  user_role ur on u.userid = ur.user_userid where role_role_id=2", nativeQuery = true)
    List<User> getAllUserByRoles();

    User findByResetPasswordToken(String token);
}
