package com.kgj.project.manager.entity;

import com.kgj.project.manager.entity.enums.UserRole;
import com.kgj.project.manager.entity.listener.UserEntityListener;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_user")
@EntityListeners(UserEntityListener.class)  // 엔티티 리스너 설정 추가
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    @Id
    @Column(name="user_id")
    private String userId;                      // 사용자 고유 ID (ex: USER202502180001)
   
    @Column(unique = true, nullable = false)
    private String username;                    // 사용자 이름 (ex: "john_doe")

    @Column(unique = true, nullable = false)
    private String email;                       // 사용자 이메일 (ex: "john@example.com"), 로그인시 사용

    @Column(name="password_hash",nullable = false)
    private String passwordHash;                // 암호화된 비밀번호 저장 (보안 목적)

    @Enumerated(EnumType.STRING)
    private UserRole role;                      // 사용자 역할 (ADMIN, MEMBER, READ_ONLY)

    // 생성시 자동 userId 부여
    @PrePersist
    public void prePersistRole() {
        if (this.role == null) {
            this.role = UserRole.MEMBER;
        }
    }

}
