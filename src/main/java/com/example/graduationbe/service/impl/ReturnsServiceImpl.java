package com.example.graduationbe.service.impl;

import com.example.graduationbe.entities.commerce.Order;
import com.example.graduationbe.entities.commerce.OrderDetails;
import com.example.graduationbe.entities.commerce.Product;
import com.example.graduationbe.entities.commerce.ReturnOrder;
import com.example.graduationbe.repository.OrderRepository;
import com.example.graduationbe.repository.ProductRepository;
import com.example.graduationbe.repository.ReturnsOrderRepository;
import com.example.graduationbe.service.ReturnsOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReturnsServiceImpl implements ReturnsOrderService {
    private final ReturnsOrderRepository repository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Override
    public ReturnOrder createReturn(ReturnOrder returnOrder, Long orderId) {
        Order order = this.orderRepository.findById(orderId).get();
        List<OrderDetails> orderDetails = order.getOrderDetails();
        for (OrderDetails or : orderDetails) {
            Product product = this.productRepository.findById(or.getProduct().getPId()).get();
            product.setQuantum(product.getQuantum() + or.getProduct().getQuantum());
        }
        ReturnOrder returnOrder1 = new ReturnOrder();
        order.setOrderStatus("Trả hàng");
        returnOrder1.setOrder(order);
        returnOrder1.setDateReturn(new Date());
        returnOrder1.setNote(returnOrder.getNote());
        return this.repository.save(returnOrder1);
    }

    @Override
    public ReturnOrder getReturnsOrderById(Order order) {
        return this.repository.findByOrder(order);
    }
}
