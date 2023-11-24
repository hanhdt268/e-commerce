package com.example.graduationbe.entities.commerce;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class OrderProductQuantity implements Serializable {
    private Long pId;
    private Integer quantity;
}
