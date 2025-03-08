package com.kgj.project.manager.dto;

import lombok.Getter;

@Getter
public class ProjectDto {

    private String name;                        // 프로젝트 이름
    private String description;                 // 프로젝트 설명 (긴 텍스트 저장 가능)
    private String ownerId;                         // 프로젝트 소유자 (외래 키)

}
