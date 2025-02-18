package com.kgj.project.manager.repository;

import com.kgj.project.manager.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueRepository extends JpaRepository<Issue, String> {

    @Query("SELECT COUNT(i) FROM Issue i  WHERE i.issueId LIKE :prefix%")
    int getNextSequence(@Param("prefix") String prefix);

}
