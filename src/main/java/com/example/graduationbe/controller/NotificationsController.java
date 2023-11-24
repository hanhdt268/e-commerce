package com.example.graduationbe.controller;

import com.example.graduationbe.dto.NotificationsDto;
import com.example.graduationbe.entities.commerce.Notifications;
import com.example.graduationbe.entities.commerce.NotificationsQuantity;
import com.example.graduationbe.repository.NotificationsRepository;
import com.example.graduationbe.service.impl.NotificationsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notify")
@RequiredArgsConstructor
@CrossOrigin("*")
public class NotificationsController {
    private final SimpMessagingTemplate template;
    private final NotificationsServiceImpl notificationsService;
    private final NotificationsRepository repo;
    private final ModelMapper modelMapper;

    @PostMapping("/add")
    public void createNotify(@RequestBody NotificationsQuantity notifications) {
        notificationsService.createNotifies(notifications);
    }

    @GetMapping("/notifies")
    public List<NotificationsDto> notifies() {
        List<Notifications> notifications1 = repo.findAll();
        notifications1.stream().forEach(x -> x.setCount(0));
        List<Notifications> notifications2 = repo.saveAll(notifications1);
        List<NotificationsDto> notificationsDtos = notifications2.stream().map(notifications -> this.modelMapper.map(notifications, NotificationsDto.class)).collect(Collectors.toList());
        getNotification();
        return notificationsDtos;
    }


    @GetMapping("/notify")
    public List<NotificationsDto> getNotification() {
        List<Notifications> notifications1 = repo.findAll();
        List<NotificationsDto> notificationsDtos = notifications1.stream().map(notifications -> this.modelMapper.map(notifications, NotificationsDto.class)).collect(Collectors.toList());
        // Increment Notification by one
        // Push notifications to front-end
        template.convertAndSend("/topic/notification", notificationsDtos);
        return notificationsDtos;
    }
}
