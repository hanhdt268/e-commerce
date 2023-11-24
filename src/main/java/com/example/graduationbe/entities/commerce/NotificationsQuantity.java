package com.example.graduationbe.entities.commerce;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class NotificationsQuantity implements Serializable {
    private int count;
    private List<OrderProductQuantity> oderProductQuantityList;
}
