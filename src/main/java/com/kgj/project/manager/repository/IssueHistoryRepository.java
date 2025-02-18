package com.kgj.project.manager.repository;

import com.kgj.project.manager.entity.IssueHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueHistoryRepository extends JpaRepository<IssueHistory, String> {

    @Query("SELECT COUNT(i) FROM IssueHistory i  WHERE i.issueHistoryId LIKE :prefix%")
    int getNextSequence(@Param("prefix") String prefix);

}
