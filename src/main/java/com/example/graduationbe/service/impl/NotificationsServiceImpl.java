package com.example.graduationbe.service.impl;

import com.example.graduationbe.entities.commerce.Notifications;
import com.example.graduationbe.entities.commerce.NotificationsQuantity;
import com.example.graduationbe.entities.commerce.OrderProductQuantity;
import com.example.graduationbe.entities.commerce.Product;
import com.example.graduationbe.repository.NotificationsRepository;
import com.example.graduationbe.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationsServiceImpl {

    private final NotificationsRepository notificationsRepo;

    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;

    public void createNotifies(NotificationsQuantity list) {
        List<OrderProductQuantity> oderProductQuantityList = list.getOderProductQuantityList();
        for (OrderProductQuantity o : oderProductQuantityList) {
            Product product = productRepository.findById(o.getPId()).get();
            Notifications notifications = new Notifications(1, new Date(), product);
            this.notificationsRepo.save(notifications);
        }
    }
}
