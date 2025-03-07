package com.kgj.project.manager.entity.listener;

import com.kgj.project.manager.entity.Issue;
import com.kgj.project.manager.entity.IssueHistory;
import com.kgj.project.manager.repository.IssueHistoryRepository;
import com.kgj.project.manager.repository.IssueRepository;
import jakarta.persistence.PrePersist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class IssueHistoryEntityListener {

    private IssueHistoryRepository issueHistoryRepository;

    @Autowired
    public void setIssueHistoryRepository(IssueHistoryRepository repository) {
        issueHistoryRepository = repository;
    }

    @PrePersist
    public void prePersist(IssueHistory issueHistory) {
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        int sequence = issueHistoryRepository.getNextSequence("ISSUEHISTORY") + 1;
        issueHistory.setIssueHistoryId("ISSUEHISTORY" + date + String.format("%04d", sequence));
    }

}
