package com.kgj.project.manager.entity.listener;

import com.kgj.project.manager.entity.Project;
import com.kgj.project.manager.repository.ProjectRepository;
import jakarta.persistence.PrePersist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ProjectEntityListener {

    private ProjectRepository projectRepository;

    @Autowired
    public void setProjectRepository(ProjectRepository repository) {
        projectRepository = repository;
    }

    @PrePersist
    public void prePersist(Project project) {
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        if(projectRepository != null) {
            int sequence = projectRepository.getNextSequence("PROJECT") + 1;
            project.setProjectId("PROJECT" + date + String.format("%04d", sequence));
        } else {
            project.setProjectId("PROJECT" + date + String.format("%04d", 1));
        }

    }

}
