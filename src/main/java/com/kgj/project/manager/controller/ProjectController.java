package com.kgj.project.manager.controller;

import com.kgj.project.manager.entity.Project;
import com.kgj.project.manager.entity.ProjectMember;
import com.kgj.project.manager.entity.User;
import com.kgj.project.manager.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {
    
    private final ProjectService projectService;
    
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Project> createProject(@Valid @RequestBody Project project) {
        return ResponseEntity.ok(projectService.createProject(project));
    }
    
    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(projectService.getAllProjects(user));
    }
    
    @GetMapping("/{projectId}")
    public ResponseEntity<Project> getProject(@PathVariable String projectId) {
        return ResponseEntity.ok(projectService.getProject(projectId));
    }
    
    @PostMapping("/{projectId}/members")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProjectMember> addProjectMember(
            @PathVariable String projectId,
            @RequestBody ProjectMember member) {
        return ResponseEntity.ok(projectService.addProjectMember(projectId, member));
    }
} 