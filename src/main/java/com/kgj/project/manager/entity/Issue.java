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
@EntityListeners(IssueEntityListener.class) // 엔티티 리스너 설정 추가
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Issue extends BaseEntity {

    @Id
    @Column(name="issue_id")
    private String issueId;                             // 이슈 고유 ID (ex: ISSUE202502180001)

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;                            // 이슈가 속한 프로젝트 (외래 키)

    @Column(nullable = false)
    private String title;                               // 이슈 제목

    @Column(columnDefinition = "TEXT")
    private String description;                         // 이슈 상세 설명 (긴 텍스트 저장 가능)

    @Enumerated(EnumType.STRING)
    private IssueStatus status;                         // 이슈 상태 (TODO, IN_PROGRESS, DONE)

    @Enumerated(EnumType.STRING)
    private PriorityLevel priority;                     // 이슈 우선순위 (LOW, MEDIUM, HIGH, CRITICAL)

    @ManyToOne
    @JoinColumn(name = "assigned_to")
    private User assignedTo;                            // 이슈 담당자 (외래 키, Nullable)
}
