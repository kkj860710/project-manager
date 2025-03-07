package com.kgj.project.manager.service;

import com.kgj.project.manager.entity.Issue;
import com.kgj.project.manager.entity.IssueHistory;
import com.kgj.project.manager.entity.Project;
import com.kgj.project.manager.entity.User;
import com.kgj.project.manager.entity.enums.ChangeType;
import com.kgj.project.manager.entity.enums.IssueStatus;
import com.kgj.project.manager.repository.IssueHistoryRepository;
import com.kgj.project.manager.repository.IssueRepository;
import com.kgj.project.manager.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class IssueService {
    
    private final IssueRepository issueRepository;
    private final ProjectRepository projectRepository;
    private final IssueHistoryRepository historyRepository;
    private final EmailService emailService;
    
    public Issue createIssue(String projectId, Issue issue) {
        Project project = projectRepository.findById(projectId)
            .orElseThrow(() -> new NullPointerException("Project not found"));
            
        issue.setProject(project);
        Issue savedIssue = issueRepository.save(issue);
        
        // 이슈 생성 히스토리 기록
        createHistory(savedIssue, null, issue.getStatus().name(), ChangeType.CREATE);
        
        // 담당자가 지정된 경우 이메일 알림
        if (issue.getAssignedTo() != null) {
            emailService.sendIssueAssignmentNotification(savedIssue);
        }
        
        return savedIssue;
    }
    
    public Issue updateIssueStatus(String projectId, String issueId, IssueStatus newStatus, User user) {
        Issue issue = issueRepository.findById(issueId)
            .orElseThrow(() -> new NullPointerException("Issue not found"));
            
        IssueStatus oldStatus = issue.getStatus();
        issue.setStatus(newStatus);

        // 각각의 업데이트 별 타입 넣는것을 분기로 나눠야함
        // 예를 들어서 댓글추가시에는 ChangeType.COMMENT_ADDED 등으로 나눌수 있게 해야 히스토리 저장 가능

        // 상태 변경 히스토리 기록
        createHistory(issue, oldStatus.name(), newStatus.name(), ChangeType.STATUS_CHANGE);
        
        // 담당자에게 이메일 알림
        emailService.sendIssueStatusChangeNotification(issue);
        
        return issueRepository.save(issue);
    }
    
    private void createHistory(Issue issue, String oldValue, String newValue, ChangeType changeType) {
        IssueHistory history = IssueHistory.builder()
            .issue(issue)
            .changeType(changeType)
            .oldValue(oldValue)
            .newValue(newValue)
            .build();
        historyRepository.save(history);
    }
    
    @Transactional(readOnly = true)
    public List<Issue> getProjectIssues(String projectId, User user) {
        return issueRepository.findByProject(projectRepository.findByProjectId(projectId));
    }
} 