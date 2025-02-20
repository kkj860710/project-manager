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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_issue_history")
@EntityListeners(IssueHistoryEntityListener.class) // 엔티티 리스너 설정 추가
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IssueHistory extends BaseEntity {

    @Id
    private String issueHistoryId;                      // 이슈 변경 기록 고유 ID (ex: ISSUEHISTORY202502180001)

    @ManyToOne
    @JoinColumn(name = "issue_id", nullable = false)
    private Issue issue;                                // 변경된 이슈 (외래 키)

    @Enumerated(EnumType.STRING)
    private ChangeType changeType;                      // 변경 유형 (STATUS_CHANGE, ASSIGNEE_CHANGE, PRIORITY_CHANGE, COMMENT_ADDED)

    private String oldValue;                            // 변경 이전 값

    private String newValue;                            // 변경 이후 값
}
