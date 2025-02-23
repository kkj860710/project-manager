package com.kgj.project.manager.entity;

import com.kgj.project.manager.entity.listener.ProjectEntityListener;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_project")
@EntityListeners(ProjectEntityListener.class) // 엔티티 리스너 설정 추가
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project extends BaseEntity {

    @Id
    @Column(name="project_id")
    private String projectId;                   // 프로젝트 고유 ID (ex: PROJECT202502180001)

    @Column(nullable = false)
    private String name;                        // 프로젝트 이름

    @Column(columnDefinition = "TEXT")
    private String description;                 // 프로젝트 설명 (긴 텍스트 저장 가능)

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;                         // 프로젝트 소유자 (외래 키)
}
