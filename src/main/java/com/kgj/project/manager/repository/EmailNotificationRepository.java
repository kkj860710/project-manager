package com.kgj.project.manager.repository;

import com.kgj.project.manager.entity.EmailNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmailNotificationRepository extends JpaRepository<EmailNotification, String> {

    @Query("SELECT COUNT(e) FROM EmailNotification e  WHERE e.emailNotificationId LIKE :prefix%")
    int getNextSequence(@Param("prefix") String prefix);

}