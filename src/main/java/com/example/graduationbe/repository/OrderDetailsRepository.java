package com.example.graduationbe.repository;

import com.example.graduationbe.entities.commerce.Order;
import com.example.graduationbe.entities.commerce.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
    OrderDetails findByOrder(Order order);
}
