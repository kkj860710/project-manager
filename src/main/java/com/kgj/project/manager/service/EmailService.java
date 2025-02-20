package com.kgj.project.manager.service;

import com.kgj.project.manager.entity.Issue;
import com.kgj.project.manager.repository.EmailNotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EmailService {

    private final EmailNotificationRepository emailRepository;

    public void sendIssueAssignmentNotification(Issue savedIssue) {

    }

    public void sendIssueStatusChangeNotification(Issue issue) {
    }
}
