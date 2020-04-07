package com.example.siqpik.service;

import com.example.siqpik.domain.User;
import com.example.siqpik.repositories.NotificationRepository;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepo;

    public NotificationService(NotificationRepository notificationRepo) {
        this.notificationRepo = notificationRepo;
    }

    public Integer getNumberOfNewNotification(User user) {
        return notificationRepo.countByViewedEqualsAndUserIs(false, user);
    }
}
