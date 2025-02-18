package com.kgj.project.manager.entity;

import com.kgj.project.manager.entity.enums.UserRole;
import com.kgj.project.manager.entity.listener.UserEntityListener;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
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
    private String userId; // ex) USER202502180001

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    private UserRole role; // ADMIN, MEMBER, READ_ONLY

}
