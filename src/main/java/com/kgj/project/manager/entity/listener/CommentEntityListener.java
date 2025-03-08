package com.kgj.project.manager.entity.listener;

import com.kgj.project.manager.entity.Comment;
import com.kgj.project.manager.repository.CommentRepository;
import jakarta.persistence.PrePersist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class CommentEntityListener {

    private CommentRepository commentRepository;

    @Autowired
    public void setCommentRepository(CommentRepository repository) {
        commentRepository = repository;
    }

    @PrePersist
    public void prePersist(Comment comment) {
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        if(commentRepository !=null){
            int sequence = commentRepository.getNextSequence("COMMENT") + 1;
            comment.setCommentId("COMMENT" + date + String.format("%04d", sequence));
        } else {
            comment.setCommentId("COMMENT" + date + String.format("%04d", 1));
        }
    }

}
