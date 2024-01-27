package com.example.graduationbe.dto;

import com.example.graduationbe.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDto implements Serializable {
    private Long cartId;
    private Integer quantity;
    private ProductDto product;
    private User user;
}
