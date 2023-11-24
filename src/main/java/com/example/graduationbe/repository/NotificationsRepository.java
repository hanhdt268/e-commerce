package com.example.graduationbe.repository;

import com.example.graduationbe.entities.commerce.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationsRepository extends JpaRepository<Notifications, Long> {
}
