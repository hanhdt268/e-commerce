package com.example.graduationbe.repository;

import com.example.graduationbe.entities.User;
import com.example.graduationbe.entities.commerce.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUser(User user);

    @Query(value = "select count(*) from cart where user_userid=?", nativeQuery = true)
    Long countCart(Long userId);

    @Query("select c from Cart c where c.user.userID=:userId and c.cartId in(:longs)")
    List<Cart> findBy(Long userId, List<Long> longs);
}
