package com.example.graduationbe.entities.commerce;

import com.example.graduationbe.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cart implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartId;
    private Integer quantity;

    @OneToOne
    private Product product;


    @OneToOne
    private User user;


    public Cart(Integer quantity, Product product, User user) {
        this.quantity = quantity;
        this.product = product;
        this.user = user;
    }
}
