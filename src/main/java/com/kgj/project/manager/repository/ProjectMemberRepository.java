package com.kgj.project.manager.repository;

import com.kgj.project.manager.entity.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectMemberRepository extends JpaRepository<ProjectMember, String> {

    @Query("SELECT COUNT(p) FROM ProjectMember p  WHERE p.projectMemberId LIKE :prefix%")
    int getNextSequence(@Param("prefix") String prefix);

}
