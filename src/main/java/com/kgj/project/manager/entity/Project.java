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
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_project")
@EntityListeners(ProjectEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Project extends BaseEntity {

    @Id
    private String projectId; // ex) PROJECT202502180001

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

}
