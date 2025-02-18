package com.kgj.project.manager.repository;

import com.kgj.project.manager.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, String> {

    @Query("SELECT COUNT(p) FROM Project p  WHERE p.projectId LIKE :prefix%")
    int getNextSequence(@Param("prefix") String prefix);

}
