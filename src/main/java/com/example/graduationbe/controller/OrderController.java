package com.example.graduationbe.controller;

import com.example.graduationbe.dto.OrderDto;
import com.example.graduationbe.dto.ShipperDto;
import com.example.graduationbe.entities.User;
import com.example.graduationbe.entities.commerce.OrderInput;
import com.example.graduationbe.entities.commerce.Shipper;
import com.example.graduationbe.repository.OrderRepository;
import com.example.graduationbe.repository.ShipperRepository;
import com.example.graduationbe.repository.UserRepository;
import com.example.graduationbe.service.OrderService;
import com.example.graduationbe.service.impl.OrderServiceImpl;
import com.example.graduationbe.service.impl.PaypalService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@CrossOrigin("*")
public class OrderController {

    private final OrderService orderService;

    private final OrderServiceImpl orderServiceImpl;

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ShipperRepository shipperRepository;

    private final PaypalService paypalService;

    @PostMapping("/{isCartCheckout}")
    public void placeOrder(@RequestBody OrderInput orderInput, @PathVariable("isCartCheckout") boolean isCartCheckout) {
        this.orderService.placeOrder(orderInput, isCartCheckout);
    }

    @PostMapping("/od/{userId}/{cartId}/{isCartCheckout}")
    public void placeOrder1(@RequestBody OrderInput orderInput,
                            @PathVariable("userId") Long userId,
                            @PathVariable("cartId") List<Long> cartId,
                            @PathVariable("isCartCheckout") boolean isCartCheckout) {
        this.orderServiceImpl.placeOrder1(orderInput, userId, cartId, isCartCheckout);
    }

//    @GetMapping("/")
//    public ResponseEntity<List<OrderDto>> getOrders() {
//        return ResponseEntity.ok(this.orderService.getAllOrders());
//    }

    @GetMapping("/{id}/{status}")
    public List<OrderDto> getOderDetails(@PathVariable("id") Long id
            , @PathVariable("status") String status
    ) {

        User user = new User();
        user.setUserID(id);
        return this.orderService.getOrderByUser(user, status);
    }

    @GetMapping("/user")
    public List<User> getShipper() {
        return this.userRepository.findByUserRole();
    }

    @GetMapping("/shipper/{userId}/{status}")
    public List<ShipperDto> getOrderByShipper(@PathVariable("userId") Long userId,
                                              @PathVariable("status") String status) {
        return this.orderServiceImpl.getOrdersForShipper(userId, status);
    }

    @GetMapping("/shipping/{userId}")
    public List<ShipperDto> getOrderByShipping(@PathVariable("userId") Long userId) {
        List<Shipper> shippers = this.shipperRepository.countBy(userId);
        List<ShipperDto> shipperDto = shippers.stream().map(shipper ->
                this.modelMapper.map(shipper, ShipperDto.class)).collect(Collectors.toList());
        return shipperDto;
    }

    @GetMapping("/insert/{orderId}/{userId}")
    public void insertShipper(@PathVariable("orderId") Long orderId,
                              @PathVariable("userId") Long userId) {
        this.orderServiceImpl.insertShipper(orderId, userId);
    }

    @GetMapping("/getAllOrderDetails/{status}")
    public List<OrderDto> getAllOrderDetails(@PathVariable("status") String status) {
        return this.orderServiceImpl.getOrders(status);
    }

    //
//    @GetMapping("/getOrder/{status}")
//    public List<OrderDto> getAllOrder(@PathVariable("status") String status) {
//        return this.orderServiceImpl.getOrderDetailsByStatusByShipper(status);
//    }
//
    @GetMapping("/presentCategory")
    public List<Map<Object, String>> getPresentOrderByCategory() {
        return this.orderRepository.getPresentOrderByCategory();
    }

    //
    @GetMapping("/total")
    public Float getTotalAmount() {
        return this.orderRepository.selectTotals();
    }

    //
    @GetMapping("/quantity")
    public BigDecimal getTotalQuantity() {
        return this.orderRepository.selectQuantity();
    }

    //
    @GetMapping("/amountMonth")
    public List<Map<Object, String>> getAmountByMonth() {
        return this.orderRepository.getMonthOrder();
    }

    //
    @GetMapping("/status/{orderStatus}")
    public Long getCountStatus(@PathVariable("orderStatus") String orderStatus) {
        return this.orderRepository.countBy(orderStatus);
    }

    @GetMapping("/totalDelivered")
    public Long getCountStatus() {
        return this.orderRepository.countAllBy();
    }

    //
//    @GetMapping("/{orderId}")
//    public OrderDto getOrderById(@PathVariable("orderId") Long orderId) {
//        return this.orderService.getOrderById(orderId);
//    }
//
//
    @GetMapping("/confirm/{orderId}")
    private void markOrderAsConfirm(@PathVariable("orderId") Long orderId) {
        this.orderServiceImpl.markOrderAsConfirm(orderId);
    }

    @GetMapping("/markOrderAsReviewsProduct/{orderId}")
    private void markOrderAsReviewsProduct(@PathVariable("orderId") Long orderId) {
        this.orderServiceImpl.markOrderAsReviewsProduct(orderId);
    }

    @GetMapping("/cancel/{orderId}")
    private void markOrderAsCancel(@PathVariable("orderId") Long orderId) {
        this.orderServiceImpl.markOrderAsDestroy(orderId);
    }

    @GetMapping("/delivering/{orderId}")
    private void markOrderAsDelivering(@PathVariable("orderId") Long orderId) {
        this.orderServiceImpl.markOrderAsDelivering(orderId);
    }

    @GetMapping("/delivered/{orderId}/{shipId}")
    private void markOrderAsDelivered(@PathVariable("orderId") Long orderId,
                                      @PathVariable("shipId") Long shipId) {

        this.orderServiceImpl.markOrderAsDelivered(orderId, shipId);
    }

    @GetMapping("/receive/{orderId}")
    private void markOrderAsReceived(@PathVariable("orderId") Long orderId) {
        this.orderServiceImpl.markOrderAsReceived(orderId);
    }

    //statistics shipper
    @GetMapping("/totalShipper/{userId}")
    public Float getTotalAmountForShipper(@PathVariable("userId") Long userId) {
        return this.orderRepository.sumAmount(userId);
    }

    @GetMapping("/quantityShipper/{userId}")
    public BigDecimal getQuantityForShipper(@PathVariable("userId") Long userId) {
        return this.orderRepository.countQuantity(userId);
    }

    @GetMapping("/deliveringShipper/{userId}")
    public Long getDeliveringForShipper(@PathVariable("userId") Long userId) {
        return this.orderRepository.countDelivering(userId);
    }


    @GetMapping("/deliveredShipper/{userId}")
    public Long getDeliveredForShipper(@PathVariable("userId") Long userId) {
        return this.orderRepository.countDelivery(userId);
    }

    @GetMapping("/countCancel")
    public Long getCountCancel() {
        return this.orderRepository.countCancel();
    }


//    @PostMapping("/createPaypal")
//    public TransactionDetails createPaypal(@RequestParam("method") String method,
//                                           @RequestParam("description") String description) throws PayPalRESTException {
//        return this.paypalService.createPayment("sale", method, description);
//    }
}
