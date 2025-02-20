package com.kgj.project.manager.entity;

import com.kgj.project.manager.entity.enums.UserRole;
import com.kgj.project.manager.entity.listener.ProjectMemberEntityListener;
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
import lombok.Builder;

@Entity
@Table(name = "tb_project_member")
@EntityListeners(ProjectMemberEntityListener.class) // 엔티티 리스너 설정 추가
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectMember extends BaseEntity {

    @Id
    private String projectMemberId;                     // 프로젝트 멤버 고유 ID (ex: PROJECTMEMBERID202502180001)

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;                            // 멤버가 속한 프로젝트 (외래 키)

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;                                  // 프로젝트 멤버 (외래 키)

    @Enumerated(EnumType.STRING)
    private UserRole role;                              // 프로젝트 내 역할 (ADMIN, MEMBER, READ_ONLY)
}
