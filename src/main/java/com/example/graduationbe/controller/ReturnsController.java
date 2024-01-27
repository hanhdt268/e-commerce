package com.example.graduationbe.controller;

import com.example.graduationbe.entities.commerce.Order;
import com.example.graduationbe.entities.commerce.ReturnOrder;
import com.example.graduationbe.service.impl.ReturnsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/returns")
@CrossOrigin("*")
public class ReturnsController {

    private final ReturnsServiceImpl returnsService;

    @PostMapping("/{orderId}")
    public ReturnOrder createReturns(@RequestBody ReturnOrder returnOrder,
                                     @PathVariable("orderId") Long orderId) {
        return this.returnsService.createReturn(returnOrder, orderId);
    }

    @GetMapping("/{orderId}")
    public ReturnOrder getReturnsOrderByOrderId(@PathVariable("orderId") Long orderId) {
        Order order = new Order();
        order.setOrderId(orderId);
        return this.returnsService.getReturnsOrderById(order);
    }
}
