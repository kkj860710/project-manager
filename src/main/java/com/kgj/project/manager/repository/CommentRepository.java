package com.kgj.project.manager.repository;

import com.kgj.project.manager.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {

    @Query("SELECT COUNT(c) FROM Comment c  WHERE c.commentId LIKE :prefix%")
    int getNextSequence(@Param("prefix") String prefix);

}
