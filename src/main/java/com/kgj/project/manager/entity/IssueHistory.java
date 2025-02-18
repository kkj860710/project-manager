package com.kgj.project.manager.entity;

import com.kgj.project.manager.entity.enums.ChangeType;
import com.kgj.project.manager.entity.listener.IssueHistoryEntityListener;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_issue_history")
@EntityListeners(IssueHistoryEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IssueHistory extends BaseEntity {

    @Id
    private String issueHistoryId; // ex) ISSUEHISTORY202502180001

    @ManyToOne
    @JoinColumn(name = "issue_id", nullable = false)
    private Issue issue;

    @Enumerated(EnumType.STRING)
    private ChangeType changeType; // STATUS_CHANGE, ASSIGNEE_CHANGE, PRIORITY_CHANGE, COMMENT_ADDED

    private String oldValue;
    private String newValue;

}
