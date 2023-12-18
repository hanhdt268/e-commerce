package com.example.graduationbe.repository;

import com.example.graduationbe.entities.commerce.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByActive(boolean b);

    @Query(value = "select (sum(value)/count(re_id)) as rating  from review where product_p_id=?", nativeQuery = true)
    Float countBy(Long pId);

    @Query(value = "select count(re_id) as t from review where product_p_id=?", nativeQuery = true)
    Long countAllBy(Long pId);
}
