package com.example.graduationbe.service;

import com.example.graduationbe.dto.CartDto;
import com.example.graduationbe.entities.User;
import com.example.graduationbe.entities.commerce.Cart;

import java.util.List;

public interface CartService {
    CartDto addToCart(Cart cart, Long pId);

    List<CartDto> getCartDetails(User user);

    void delete(Long cartId);

    Cart getCartById(Long cartId);
}
