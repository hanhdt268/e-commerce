package com.example.graduationbe.repository;

import com.example.graduationbe.entities.commerce.Order;
import com.example.graduationbe.entities.commerce.ReturnOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReturnsOrderRepository extends JpaRepository<ReturnOrder, Long> {

    ReturnOrder findByOrder(Order order);
}
