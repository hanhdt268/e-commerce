package com.example.graduationbe.repository;

import com.example.graduationbe.entities.User;
import com.example.graduationbe.entities.commerce.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);

    List<Order> findOrderByOrderStatusAndUser(String oderStatus, User user);

    List<Order> findOrderByOrderStatus(String status);

    @Query(value = "select o.* from shipper s inner join orders o on s.order_order_id = o.order_id where s.user_userid=?",
            nativeQuery = true)
    List<Order> findAllBy(Long userID);

    @Query(value = "select o.* from shipper s inner join orders o on s.order_order_id = o.order_id where s.user_userid=:userId and order_status=:orderStatus",
            nativeQuery = true)
    List<Order> findByShip(Long userId, String orderStatus);

    //
//    @Query(value = "select oder_id,product_p_id,title,images,amount,T  from\n" +
//            "            (select oder_id, product_p_id ,title,images,sum(oder_amount) AS amount,\n" +
//            "            sum(quantity) AS T from order_details o  inner join product p on o.product_p_id = p.p_id group by product_p_id)\n" +
//            "            as order_details  where T = (select max(T) from order_details)  order by t DESC", nativeQuery = true)
//    List<Map<Object, String>> selectProductBestSelling(Pageable pageable);
//
//
    @Query(value = "select sum(order_amount) from orders where  order_status in ('Đã giao','Hoàn thành', 'Đánh giá')", nativeQuery = true)
    Float selectTotals();

    //
//
    @Query(value = "select c.title, sum(od.quantity * p.discount_price) as amount from order_detail od join product p on od.product_p_id = p.p_id\n" +
            "join category c on p.category_cate_id = c.cate_id  join orders o on od.order_id = o.order_id\n" +
            "                                                           where o.order_status in ('Đã giao','Hoàn thành', 'Đánh giá') group by c.title", nativeQuery = true)
    List<Map<Object, String>> getPresentOrderByCategory();

    //
//
    @Query(value = "select {fn MONTHNAME(order_date)} as mothName, sum(order_amount) as mount from orders\n" +
            "  where (YEAR(order_date) = 2023) and orders.order_status in ('Đã giao','Hoàn thành', 'Đánh giá') group by {fn MONTHNAME(order_date)}, MONTH(order_date), year(order_date)\n" +
            "   order by year(order_date), month(order_date)", nativeQuery = true)
    List<Map<Object, String>> getMonthOrder();

    //
    @Query("select count(orderId) from Order where orderStatus=:orderStatus")
    Long countBy(String orderStatus);


    @Query(value = "select count(order_id) from orders where order_status in ('Đã giao','Hoàn thành', 'Đánh giá')", nativeQuery = true)
    Long countAllBy();

    //
//
    @Query("select sum(quantity) from Order")
    BigDecimal selectQuantity();
//
//    @Query(value = "select * from order_details where NOT oder_status='Chờ xác nhận'", nativeQuery = true)
//    List<Order> findOrderByShipper();


    @Query(value = "select sum(quantity) from orders o join shipper s on o.order_id = s.order_order_id\n" +
            "where order_status in ('Đã giao','Hoàn thành', 'Đánh giá') and  s.user_userid=?", nativeQuery = true)
    BigDecimal countQuantity(Long userId);

    @Query(value = "select count(order_id) from orders o join shipper s on o.order_id = s.order_order_id\n" +
            "where order_status = 'Đang giao' and  s.user_userid=?", nativeQuery = true)
    Long countDelivering(Long userId);

    @Query(value = "select count(order_id) from orders o join shipper s on o.order_id = s.order_order_id\n" +
            "where order_status in ('Đã giao','Hoàn thành', 'Đánh giá') and  s.user_userid=?", nativeQuery = true)
    Long countDelivery(Long userId);


    @Query(value = "select sum(order_amount) from orders o join shipper s on o.order_id = s.order_order_id\n" +
            "where  order_status in ('Đã giao','Hoàn thành', 'Đánh giá') and s.user_userid=?", nativeQuery = true)
    Float sumAmount(Long userId);


}
