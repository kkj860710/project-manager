package com.kgj.project.manager.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;                // 데이터 생성 날짜 (자동 설정)

    @UpdateTimestamp
    private LocalDateTime updatedAt;                // 데이터 수정 날짜 (자동 설정)

    @Column(nullable = false, updatable = false)
    private String createdBy;                       // 생성한 사용자 ID (ex: "USER202502180001")

    private String updatedBy;                       // 마지막으로 데이터를 수정한 사용자 ID

}
