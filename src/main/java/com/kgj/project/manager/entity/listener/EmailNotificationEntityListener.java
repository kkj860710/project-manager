package com.kgj.project.manager.entity.listener;

import com.kgj.project.manager.entity.EmailNotification;
import com.kgj.project.manager.repository.EmailNotificationRepository;
import jakarta.persistence.PrePersist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class EmailNotificationEntityListener {

    private static EmailNotificationRepository emailNotificationRepository;

    @Autowired
    public void setEmailNotificationRepository(EmailNotificationRepository repository) {
        emailNotificationRepository = repository;
    }

    @PrePersist
    public void prePersist(EmailNotification emailNotification) {
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        int sequence = emailNotificationRepository.getNextSequence("EMAILNOTIFICATION") + 1;
        emailNotification.setEmailNotificationId("EMAILNOTIFICATION" + date + String.format("%04d", sequence));
    }
}
