package com.kgj.project.manager.service;

import com.kgj.project.manager.entity.Project;
import com.kgj.project.manager.entity.ProjectMember;
import com.kgj.project.manager.entity.User;
import com.kgj.project.manager.entity.enums.UserRole;
import com.kgj.project.manager.repository.ProjectMemberRepository;
import com.kgj.project.manager.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProjectService {
    
    private final ProjectRepository projectRepository;
    private final ProjectMemberRepository memberRepository;
    
    public Project createProject(Project project) {
        // 프로젝트 생성 시 owner를 자동으로 admin 멤버로 추가
        Project savedProject = projectRepository.save(project);
        
        ProjectMember ownerMember = ProjectMember.builder()
            .project(savedProject)
            .user(project.getOwner())
            .role(UserRole.ADMIN)
            .build();
        memberRepository.save(ownerMember);
        
        return savedProject;
    }
    
    @Transactional(readOnly = true)
    public List<Project> getAllProjects(User user) {
        if (user.getRole() == UserRole.ADMIN) {
            return projectRepository.findAll();
        }
        return projectRepository.findByOwner(user);
    }
    
    public Project getProject(String projectId) {
        return projectRepository.findById(projectId)
            .orElseThrow(() -> new NullPointerException("Project not found"));
    }
    
    public ProjectMember addProjectMember(String projectId, ProjectMember member) {
        Project project = getProject(projectId);
        member.setProject(project);
        return memberRepository.save(member);
    }
} 