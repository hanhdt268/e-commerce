package com.example.graduationbe.service;

import com.example.graduationbe.entities.commerce.Order;
import com.example.graduationbe.entities.commerce.ReturnOrder;

public interface ReturnsOrderService {

    ReturnOrder createReturn(ReturnOrder returnOrder, Long orderId);

    ReturnOrder getReturnsOrderById(Order order);
}
