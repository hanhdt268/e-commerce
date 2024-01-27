package com.example.graduationbe.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor

public class OrderDetailsDto implements Serializable {
    private Long id;
    private int quantity;
    private boolean isReviewed = false;
    private ProductDto product;


}
