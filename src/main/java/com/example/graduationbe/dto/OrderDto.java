package com.example.graduationbe.dto;

import com.example.graduationbe.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderDto implements Serializable {
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

    private User user;

    private List<OrderDetailsDto> orderDetails;

}
