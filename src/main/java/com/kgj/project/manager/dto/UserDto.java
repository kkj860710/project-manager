package com.kgj.project.manager.dto;

import com.kgj.project.manager.entity.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private String email;

    private String userId;

    private String password;

    private String username;

    private String role;

}
