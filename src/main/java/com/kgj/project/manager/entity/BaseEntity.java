package com.kgj.project.manager.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {

    @CreationTimestamp
    @Column(name="created_at",updatable = false)
    private LocalDateTime createdAt;                // 데이터 생성 날짜 (자동 설정)

    @UpdateTimestamp
    @Column(name="updated_at")
    private LocalDateTime updatedAt;                // 데이터 수정 날짜 (자동 설정)

    @Column(name="created_by", updatable = false)
    private String createdBy;                       // 생성한 사용자 ID (ex: "USER202502180001")

    @Column(name="updated_by")
    private String updatedBy;                       // 마지막으로 데이터를 수정한 사용자 ID

}
