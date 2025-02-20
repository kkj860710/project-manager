package com.kgj.project.manager.repository;

import com.kgj.project.manager.entity.Issue;
import com.kgj.project.manager.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, String> {

    @Query("SELECT COUNT(i) FROM Issue i  WHERE i.issueId LIKE :prefix%")
    int getNextSequence(@Param("prefix") String prefix);

    List<Issue> findByProject(Project project);

}
