package com.example.siqpik.repositories;

import com.example.siqpik.domain.Notification;
import com.example.siqpik.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    Integer countByViewedEqualsAndUserIs(Boolean viewed, User user);
}
