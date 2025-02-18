package com.kgj.project.manager.entity;

import com.kgj.project.manager.entity.enums.IssueStatus;
import com.kgj.project.manager.entity.enums.PriorityLevel;
import com.kgj.project.manager.entity.listener.IssueEntityListener;
import jakarta.persistence.Column;
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
@Table(name = "tb_issue")
@EntityListeners(IssueEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Issue extends BaseEntity{

    @Id
    private String issueId; // ex) ISSUE202502180001

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    private IssueStatus status; // TODO, IN_PROGRESS, DONE

    @Enumerated(EnumType.STRING)
    private PriorityLevel priority; // LOW, MEDIUM, HIGH, CRITICAL

    @ManyToOne
    @JoinColumn(name = "assigned_to")
    private User assignedTo;

}
