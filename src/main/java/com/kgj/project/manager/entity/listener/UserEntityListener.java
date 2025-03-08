package com.kgj.project.manager.entity.listener;

import com.kgj.project.manager.entity.User;
import com.kgj.project.manager.repository.UserRepository;
import jakarta.persistence.PrePersist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class UserEntityListener {

    private static UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository repository) {
        userRepository = repository;
    }

    @PrePersist
    public void prePersist(User user) {
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        if(userRepository !=null) {
            int sequence = userRepository.getNextSequence("USER") + 1;
            user.setUserId("USER" + date + String.format("%04d", sequence));
        } else {
            user.setUserId("USER" + date + String.format("%04d", 1));
        }

    }
}
