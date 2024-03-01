package com.example.graduationbe.repository;

import com.example.graduationbe.entities.commerce.Shipper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipperRepository extends JpaRepository<Shipper, Long> {

    @Query(value = "select * from shipper where user_userid=?", nativeQuery = true)
    List<Shipper> countBy(Long userId);

    @Query(value = "select s.* from shipper s join orders o on s.order_order_id = o.order_id\n" +
            "where s.user_userid=:userId and o.order_status=:orderStatus", nativeQuery = true)
    List<Shipper> findby(Long userId, String orderStatus);


}
