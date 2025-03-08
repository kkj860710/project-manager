package com.kgj.project.manager.entity.listener;

import com.kgj.project.manager.entity.Issue;
import com.kgj.project.manager.repository.IssueRepository;
import jakarta.persistence.PrePersist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class IssueEntityListener {

    private IssueRepository issueRepository;

    @Autowired
    public void setIssueRepository(IssueRepository repository) {
        issueRepository = repository;
    }

    @PrePersist
    public void prePersist(Issue issue) {
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        if(issueRepository != null) {
            int sequence = issueRepository.getNextSequence("ISSUE") + 1;
            issue.setIssueId("ISSUE" + date + String.format("%04d", sequence));
        } else {
//            int sequence = issueRepository.getNextSequence("ISSUE") + 1;
            issue.setIssueId("ISSUE" + date + String.format("%04d", 1));
        }

    }

}
