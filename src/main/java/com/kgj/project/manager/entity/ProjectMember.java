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

@Entity
@Table(name = "tb_project_member")
@EntityListeners(ProjectMemberEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectMember extends BaseEntity {

    @Id
    private String projectMemberId; // ex) PROJECTMEMBERID202502180001

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private UserRole role; // ADMIN, MEMBER, READ_ONLY

}
