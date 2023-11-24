package com.example.graduationbe.entities.commerce;

import com.example.graduationbe.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;
    private String orderFullName;
    private String orderFullOder;
    private String orderContactNumber;

    private String note;

    private String orderStatus;
    private Double orderAmount;
    private Date dateDelivered;

    private Integer quantity;

    private Date orderDate;
    private String paymentMethod;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "order")
    private List<OrderDetails> orderDetails;

    
}
