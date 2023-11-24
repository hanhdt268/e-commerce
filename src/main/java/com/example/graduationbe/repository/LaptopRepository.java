package com.example.graduationbe.repository;

import com.example.graduationbe.entities.commerce.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaptopRepository extends JpaRepository<Laptop, Long> {
}
