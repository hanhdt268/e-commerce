package com.example.graduationbe.service.impl;

import com.example.graduationbe.config.JwtAuthenticationFilter;
import com.example.graduationbe.dto.CartDto;
import com.example.graduationbe.entities.User;
import com.example.graduationbe.entities.commerce.Cart;
import com.example.graduationbe.entities.commerce.Product;
import com.example.graduationbe.repository.CartRepository;
import com.example.graduationbe.repository.ProductRepository;
import com.example.graduationbe.repository.UserRepository;
import com.example.graduationbe.service.CartService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;


    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    private final UserRepository userRepository;

    @Override
    @CacheEvict(value = "cart", allEntries = true)
    public CartDto addToCart(Long pId) {
        Product product = productRepository.findById(pId).get();
        String username = JwtAuthenticationFilter.USER_CURRENT;
        User user = null;
        if (username != null) {
            user = userRepository.findByUsername(username);
        }

        List<Cart> cartList = cartRepository.findByUser(user);
        List<Cart> filterCart = cartList.stream().filter(x -> x.getProduct().getPId() == pId).collect(Collectors.toList());

        if (filterCart.size() > 0) {
            return null;
        }

        if (product != null && user != null) {
            Cart cart = new Cart(1, product, user);
            return this.modelMapper.map(this.cartRepository.save(cart), CartDto.class);
        }
        return null;
    }

    @Override
    @Cacheable(value = "cart", key = "#user.userID")
    public List<CartDto> getCartDetails(User user) {
        List<Cart> carts = cartRepository.findByUser(user);
        List<CartDto> cartDtos = carts.stream().map(cart -> this.modelMapper.map(cart, CartDto.class)).collect(Collectors.toList());
        return cartDtos;
    }

    @Override
    public void delete(Long cartId) {
        this.cartRepository.deleteById(cartId);
    }

    @Override
    @CacheEvict(value = "cart", allEntries = true)
    public Cart getCartById(Long cartId) {
        return this.cartRepository.findById(cartId).get();
    }


}
