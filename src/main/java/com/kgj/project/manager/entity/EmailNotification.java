package com.kgj.project.manager.entity;

import com.kgj.project.manager.entity.enums.EmailStatus;
import com.kgj.project.manager.entity.listener.EmailNotificationEntityListener;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_email_notification")
@EntityListeners(EmailNotificationEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailNotification extends BaseEntity{

    @Id
    private String emailNotificationId; // ex) EMAILNOTIFICATION202502180001

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // 이메일을 받을 사용자

    @ManyToOne
    @JoinColumn(name = "issue_id", nullable = false)
    private Issue issue; // 관련된 이슈

    @Column(nullable = false)
    private String email; // 수신 이메일

    @Column(columnDefinition = "TEXT", nullable = false)
    private String message; // 이메일 내용

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmailStatus status = EmailStatus.PENDING; // 기본값 PENDING

    private LocalDateTime sentAt; // 이메일이 전송된 시간

}
