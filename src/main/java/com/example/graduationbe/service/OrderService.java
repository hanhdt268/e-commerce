package com.example.graduationbe.service;

import com.example.graduationbe.dto.OrderDto;
import com.example.graduationbe.entities.User;
import com.example.graduationbe.entities.commerce.OrderInput;

import java.util.List;

public interface OrderService {

    void placeOrder(OrderInput orderInput, boolean isCartCheckout);


    //    List<OrderDto> getAllOrders();
//
    List<OrderDto> getOrderByUser(User user, String status);

    List<OrderDto> getOrders(String status);
//
//    List<OrderDto> getOrderDetailsByStatus(String status);
//
//    OrderDto getOrderById(Long orderId);


}
