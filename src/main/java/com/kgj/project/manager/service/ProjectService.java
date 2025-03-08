package com.kgj.project.manager.service;

import com.kgj.project.manager.dto.ProjectDto;
import com.kgj.project.manager.entity.Project;
import com.kgj.project.manager.entity.ProjectMember;
import com.kgj.project.manager.entity.User;
import com.kgj.project.manager.entity.enums.UserRole;
import com.kgj.project.manager.repository.ProjectMemberRepository;
import com.kgj.project.manager.repository.ProjectRepository;
import com.kgj.project.manager.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProjectService {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final ProjectMemberRepository memberRepository;
    
    public Project createProject(ProjectDto dto) {
        // 프로젝트 생성 시 owner를 자동으로 admin 멤버로 추가
        Project saveProject = new Project();
        User user = userRepository.findByUserId(dto.getOwnerId());
        saveProject.setName(dto.getName());
        saveProject.setDescription(dto.getDescription());
        saveProject.setOwner(user);
        projectRepository.save(saveProject);
        ProjectMember ownerMember = ProjectMember.builder()
            .project(saveProject)
            .user(user)
            .role(UserRole.ADMIN)
            .build();
        memberRepository.save(ownerMember);
        
        return saveProject;
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