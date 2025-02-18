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
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(nullable = false, updatable = false)
    private String createdBy; // 생성한 사용자 ID

    private String updatedBy; // 업데이트한 사용자 ID

    /**
     * ID 생성 로직 (YYYYMMDD + 0001 순차 증가)
     */
    public static String generateSequentialId(String prefix, int sequence) {
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        return prefix + date + String.format("%04d", sequence);
    }
}
