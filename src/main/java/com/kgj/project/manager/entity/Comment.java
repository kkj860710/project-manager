package com.kgj.project.manager.entity;

import com.kgj.project.manager.entity.listener.CommentEntityListener;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_comment")
@EntityListeners(CommentEntityListener.class) // 엔티티 리스너 설정 추가
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseEntity {

    @Id
    @Column(name ="comment_id", nullable = false)
    private String commentId;                           // 댓글 고유 ID (ex: COMMENT202502180001)

    @ManyToOne
    @JoinColumn(name = "issue_id", nullable = false)
    private Issue issue;                                // 댓글이 속한 이슈 (외래 키)

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;                                  // 댓글 작성자 (외래 키)

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;                             // 댓글 내용 (긴 텍스트 저장 가능)

}
