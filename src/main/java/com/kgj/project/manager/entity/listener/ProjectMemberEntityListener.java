package com.kgj.project.manager.entity.listener;

import com.kgj.project.manager.entity.ProjectMember;
import com.kgj.project.manager.repository.ProjectMemberRepository;
import jakarta.persistence.PrePersist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ProjectMemberEntityListener {

    private ProjectMemberRepository projectMemberRepository;

    @Autowired
    public void setProjectMemberRepository(ProjectMemberRepository repository) {
        projectMemberRepository = repository;
    }

    @PrePersist
    public void prePersist(ProjectMember projectMember) {
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        int sequence = projectMemberRepository.getNextSequence("PROJECT_MEMBER_ID") + 1;
        projectMember.setProjectMemberId("PROJECTMEMBERID" + date + String.format("%04d", sequence));
    }

}
