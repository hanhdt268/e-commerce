package com.example.graduationbe.entities.commerce;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderInput implements Serializable {
    private String fullName;
    private String fullAddress;
    private String contactNumber;
    private String note;
    private Integer quantity;
    private Date orderDate;
    private List<OrderProductQuantity> oderProductQuantityList;
}
