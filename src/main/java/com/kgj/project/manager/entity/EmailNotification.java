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
@EntityListeners(EmailNotificationEntityListener.class) // 엔티티 리스너 설정 추가
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailNotification extends BaseEntity {

    @Id
    private String emailNotificationId;                 // 이메일 알림 고유 ID (ex: EMAILNOTIFICATION202502180001)

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;                                  // 이메일을 받을 사용자 (외래 키)

    @ManyToOne
    @JoinColumn(name = "issue_id", nullable = false)
    private Issue issue;                                // 관련된 이슈 (외래 키)

    @Column(nullable = false)
    private String email;                               // 수신 이메일 주소

    @Column(columnDefinition = "TEXT", nullable = false)
    private String message;                             // 이메일 내용 (긴 텍스트 저장 가능)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmailStatus status = EmailStatus.PENDING;   // 이메일 상태 (기본값: PENDING)

    private LocalDateTime sentAt;                       // 이메일이 전송된 시간
}
