package com.kgj.project.manager.controller;

import com.kgj.project.manager.entity.Issue;
import com.kgj.project.manager.entity.User;
import com.kgj.project.manager.entity.enums.IssueStatus;
import com.kgj.project.manager.service.IssueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/projects/{projectId}/issues")
@RequiredArgsConstructor
public class IssueController {
    
    private final IssueService issueService;
    private final SimpMessagingTemplate messagingTemplate;
    
    @PostMapping
    public ResponseEntity<Issue> createIssue(
            @PathVariable String projectId,
            @Valid @RequestBody Issue issue,
            @AuthenticationPrincipal User user) {
        issue.setCreatedBy(user.getUsername());
        Issue savedIssue = issueService.createIssue(projectId, issue);
        
        // WebSocket으로 실시간 업데이트 전송
        messagingTemplate.convertAndSend(
            "/topic/projects/" + projectId + "/issues",
            Map.of("type", "ISSUE_CREATED", "data", savedIssue)
        );
        
        return ResponseEntity.ok(savedIssue);
    }
    
    @PutMapping("/{issueId}/status")
    public ResponseEntity<Issue> updateIssueStatus(
            @PathVariable String projectId,
            @PathVariable String issueId,
            @RequestBody Map<String, String> statusUpdate,
            @AuthenticationPrincipal User user) {
        
        Issue updatedIssue = issueService.updateIssueStatus(
            projectId, 
            issueId, 
            IssueStatus.valueOf(statusUpdate.get("status")),
            user
        );
        
        messagingTemplate.convertAndSend(
            "/topic/projects/" + projectId + "/issues",
            Map.of("type", "ISSUE_UPDATED", "data", updatedIssue)
        );
        
        return ResponseEntity.ok(updatedIssue);
    }
    
    @GetMapping
    public ResponseEntity<List<Issue>> getProjectIssues(
            @PathVariable String projectId,
            @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(issueService.getProjectIssues(projectId, user));
    }
} 