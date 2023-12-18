package com.example.graduationbe.service.impl;

import com.example.graduationbe.dto.OrderDto;
import com.example.graduationbe.entities.User;
import com.example.graduationbe.entities.commerce.*;
import com.example.graduationbe.repository.*;
import com.example.graduationbe.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final String PLACE_ORDER = "Chờ xác nhận";
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final ModelMapper modelMapper;
    private final ShipperRepository shipperRepository;
    private final OrderDetailsRepository orderDetailsRepository;

    //    @CacheEvict(value = {"order", "cart", "Selling"}, allEntries = true)
    public void placeOrder1(OrderInput orderInput, Long userID, List<Long> cartId, boolean isCartCheckout) {
        List<Cart> cart = this.cartRepository.findBy(userID, cartId);
        int quantity = 0;
        double price = 0;
        User user = null;
        for (Cart item : cart) {
            quantity += item.getQuantity();
            price += item.getProduct().getDiscountPrice() * item.getQuantity();
            user = this.userRepository.findById(item.getUser().getUserID()).get();
        }
        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(new Date());
        order.setOrderStatus("Chờ xác nhận");
        order.setPaymentMethod("Thanh toán khi nhận hàng");
        order.setOrderFullName(orderInput.getFullName());
        order.setOrderContactNumber(orderInput.getContactNumber());
        order.setOrderFullOder(orderInput.getFullAddress());
        order.setNote(orderInput.getNote());
        order.setQuantity(quantity);
        order.setOrderAmount(price);
        order.setDateDelivered(null);
        List<OrderDetails> orderDetails = new ArrayList<>();

        for (Cart c : cart) {
            Product product = this.productRepository.findById(c.getProduct().getPId()).get();
            OrderDetails orderDetail = new OrderDetails(
            );
            orderDetail.setProduct(product);
            orderDetail.setQuantity(c.getQuantity());
            orderDetail.setOrder(order);
            if (!isCartCheckout) {
                List<Cart> carts = this.cartRepository.findBy(c.getUser().getUserID(), Collections.singletonList(c.getCartId()));
                carts.stream().forEach(x -> this.cartRepository.deleteById(x.getCartId()));
            }

            if (!isCartCheckout) {
                product.setQuantum(product.getQuantum() - c.getQuantity());
            }

            this.orderDetailsRepository.save(orderDetail);
            orderDetails.add(orderDetail);
        }
        order.setOrderDetails(orderDetails);
        this.orderRepository.save(order);
    }

    @Override
    public void placeOrder(OrderInput orderInput, boolean isCartCheckout) {

    }

    @Override
//    @Cacheable(value = "order", key = "{#user.userID,#status}")
    public List<OrderDto> getOrderByUser(User user, String status) {
        if (status.equals("All")) {
            List<Order> oderDetails1 = this.orderRepository.findByUser(user);
            List<OrderDto> orderDtos1 = oderDetails1.stream().map(order -> this.modelMapper.map(order, OrderDto.class)).collect(Collectors.toList());
            return orderDtos1;
        } else {
            List<Order> oderDetails = this.orderRepository.findOrderByOrderStatusAndUser(status, user);
            List<OrderDto> orderDtos = oderDetails.stream().map(order -> this.modelMapper.map(order, OrderDto.class)).collect(Collectors.toList());
            return orderDtos;
        }
    }

    //    @Cacheable(value = "order", key = "{#userId, #status}")
    public List<OrderDto> getOrdersForShipper(Long userId, String status) {
        if (status.equals("All")) {
            List<Order> oderDetails1 = this.orderRepository.findAllBy(userId);
            List<OrderDto> orderDtos1 = oderDetails1.stream().map(order -> this.modelMapper.map(order, OrderDto.class)).collect(Collectors.toList());
            return orderDtos1;
        } else {
            List<Order> oderDetails = this.orderRepository.findByShip(userId, status);
            List<OrderDto> orderDtos = oderDetails.stream().map(order -> this.modelMapper.map(order, OrderDto.class)).collect(Collectors.toList());
            return orderDtos;
        }
    }

    @Override
//    @Cacheable("order")
    public List<OrderDto> getOrders(String status) {
        if (status.equals("All")) {
            List<Order> oderDetails = this.orderRepository.findAll();
            List<OrderDto> orderDto = oderDetails.stream().map(order -> this.modelMapper.map(order, OrderDto.class)).collect(Collectors.toList());
            return orderDto;
        } else {
            List<Order> oderDetails1 = this.orderRepository.findOrderByOrderStatus(status);
            List<OrderDto> orderDto1 = oderDetails1.stream().map(order -> this.modelMapper.map(order, OrderDto.class)).collect(Collectors.toList());
            return orderDto1;
        }
    }

    //    @CacheEvict(value = "order", allEntries = true)
    public void insertShipper(Long orderId, Long userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("user not found"));
        Order order = this.orderRepository.findById(orderId).get();
        Shipper shipper = new Shipper();
        shipper.setOrder(order);
        shipper.setUser(user);

        this.shipperRepository.save(shipper);
    }

    //    @Override
//    public List<OrderDto> getAllOrders() {
//        List<OrderDetails> orderDetails = this.orderRepository.findAll();
//        List<OrderDto> orderDtos = orderDetails.stream().map(x -> this.modelMapper.map(x,
//                OrderDto.class)).collect(Collectors.toList());
//        return orderDtos;
//    }
//
//    @Override
//    @Cacheable(value = "order", key = "{#user.userID, #status}")
//    public List<OrderDto> getOrderByUser(User user, String status) {
//        if (status.equals("All")) {
//            List<OrderDetails> oderDetails1 = this.orderRepository.findByUser(user);
//            List<OrderDto> orderDtos1 = oderDetails1.stream().map(order -> this.modelMapper.map(order, OrderDto.class)).collect(Collectors.toList());
//            return orderDtos1;
//        } else {
//            List<OrderDetails> oderDetails = this.orderRepository.findOrderByOderStatusAndUser(status, user);
//            List<OrderDto> orderDtos = oderDetails.stream().map(order -> this.modelMapper.map(order, OrderDto.class)).collect(Collectors.toList());
//            return orderDtos;
//        }
//    }
//
//    @Override
//    @Cacheable(value = "order")
//    public List<OrderDto> getOrderDetailsByStatus(String status) {
//        if (status.equals("All")) {
//            List<OrderDetails> oderDetails = this.orderRepository.findAll();
//            List<OrderDto> orderDto = oderDetails.stream().map(order -> this.modelMapper.map(order, OrderDto.class)).collect(Collectors.toList());
//            return orderDto;
//        } else {
//            List<OrderDetails> oderDetails1 = this.orderRepository.findOrderByOderStatus(status);
//            List<OrderDto> orderDto1 = oderDetails1.stream().map(order -> this.modelMapper.map(order, OrderDto.class)).collect(Collectors.toList());
//            return orderDto1;
//        }
//    }
//
//    public List<OrderDto> getOrderDetailsByStatusByShipper(String status) {
//        if (status.equals("All")) {
//            List<OrderDetails> oderDetails = this.orderRepository.findOrderByShipper();
//            List<OrderDto> orderDto = oderDetails.stream().map(order -> this.modelMapper.map(order, OrderDto.class)).collect(Collectors.toList());
//            return orderDto;
//        } else {
//            List<OrderDetails> oderDetails1 = this.orderRepository.findOrderByOderStatus(status);
//            List<OrderDto> orderDto1 = oderDetails1.stream().map(order -> this.modelMapper.map(order, OrderDto.class)).collect(Collectors.toList());
//            return orderDto1;
//        }
//    }
//
//    @Override
//    public OrderDto getOrderById(Long orderId) {
//        return this.modelMapper.map(this.orderRepository.findById(orderId).orElseThrow(() ->
//                new UsernameNotFoundException("order not found")), OrderDto.class);
//    }
//
//    @CacheEvict(value = "order", allEntries = true)
    public void markOrderAsConfirm(Long orderId) {
        Order orderDetails = this.orderRepository.findById(orderId).orElseThrow(() ->
                new UsernameNotFoundException("order not found"));
        if (orderDetails != null) {
            orderDetails.setOrderStatus("Chờ lấy hàng");
            this.orderRepository.save(orderDetails);
        }
    }

    //    @CacheEvict(value = "order", allEntries = true)
    public void markOrderAsDelivering(Long orderId) {
        Order orderDetails = this.orderRepository.findById(orderId).orElseThrow(() ->
                new UsernameNotFoundException("order not found"));
        if (orderDetails != null) {
            orderDetails.setOrderStatus("Đang giao");
            this.orderRepository.save(orderDetails);
        }
    }

    //    @CacheEvict(value = "order", allEntries = true)
    public void markOrderAsDestroy(Long orderId) {
        Order orderDetails = this.orderRepository.findById(orderId).orElseThrow(() ->
                new UsernameNotFoundException("order not found"));
        if (orderDetails != null) {
            orderDetails.setOrderStatus("Đã hủy");
            this.orderRepository.save(orderDetails);
        }
    }

    //
//    @CacheEvict(value = "order", allEntries = true)
    public void markOrderAsDelivered(Long orderId) {
        Order orderDetails = this.orderRepository.findById(orderId).orElseThrow(() ->
                new UsernameNotFoundException("order not found"));
        if (orderDetails != null) {
            orderDetails.setOrderStatus("Đã giao");
            orderDetails.setDateDelivered(new Date());
            this.orderRepository.save(orderDetails);
        }
    }

    //
//    @CacheEvict(value = "order", allEntries = true)
    public void markOrderAsReceived(Long orderId) {
        Order orderDetails = this.orderRepository.findById(orderId).orElseThrow(() ->
                new UsernameNotFoundException("order not found"));
        if (orderDetails != null) {
            orderDetails.setOrderStatus("Hoàn thành");
            this.orderRepository.save(orderDetails);
        }
    }

    //
//    @CacheEvict(value = "order", allEntries = true)
    public void markOrderAsReviewsProduct(Long orderId) {
        Order orderDetails = this.orderRepository.findById(orderId).orElseThrow(() ->
                new UsernameNotFoundException("order not found"));
        if (orderDetails != null) {
            orderDetails.setOrderStatus("Đánh giá");
            this.orderRepository.save(orderDetails);
        }
    }
}
