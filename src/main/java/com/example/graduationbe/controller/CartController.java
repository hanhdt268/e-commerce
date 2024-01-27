package com.example.graduationbe.controller;


import com.example.graduationbe.dto.CartDto;
import com.example.graduationbe.entities.User;
import com.example.graduationbe.entities.commerce.Cart;
import com.example.graduationbe.repository.CartRepository;
import com.example.graduationbe.service.impl.CartServiceImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CartController {

    private final CartServiceImpl cartService;

    private final CartRepository cartRepository;
    private final ModelMapper modelMapper;


    @PostMapping("/{pId}")
    public ResponseEntity<CartDto> addToCart(@RequestBody Cart cart, @PathVariable("pId") Long pId) {
        return ResponseEntity.ok(this.cartService.addToCart(cart, pId));
    }

    @GetMapping("/getCartByUser/{id}")
    public List<CartDto> getCartDetails(@PathVariable("id") Long id) {
        User user = new User();
        user.setUserID(id);
        return cartService.getCartDetails(user);
    }


    @DeleteMapping("/{cartId}")
    public void deleteCartById(@PathVariable("cartId") Long cartId) {
        this.cartService.delete(cartId);
    }

    @GetMapping("/count/{id}")
    public Long countCart(@PathVariable("id") Long id) {
        return cartRepository.countCart(id);
    }

    @PatchMapping("/{cartId}/{plus}")
    public ResponseEntity<CartDto> updateQuantity(@PathVariable("cartId") Long cartId,
                                                  @PathVariable("plus") String plus) {
        Cart cart = this.cartService.getCartById(cartId);
        try {
            if (cart != null) {
                if (plus.equals("plus")) {
                    cart.setQuantity(cart.getQuantity() + 1);
                }
                if (plus.equals("sub")) {
                    cart.setQuantity(cart.getQuantity() - 1);
                }
                return ResponseEntity.ok(this.modelMapper.map(this.cartRepository.save(cart), CartDto.class));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/delete/{cartId}")
//    @CacheEvict(value = "manufacturer", allEntries = true)
    public void delete(@PathVariable List<Long> cartId) {
        this.cartRepository.deleteByIdIn(cartId);
    }
}
