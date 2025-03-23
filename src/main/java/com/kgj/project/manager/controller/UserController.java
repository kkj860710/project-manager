package com.kgj.project.manager.controller;

import com.kgj.project.manager.dto.UserDto;
import com.kgj.project.manager.dto.UserResponseDto;
import com.kgj.project.manager.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<UserDto> signUp(@RequestBody UserDto user) {
        log.info("createUser: {}", user);
        UserDto createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<UserDto> signIn(@RequestBody UserDto userDto) {
        log.info("getUser: {}", userDto);
        UserDto user = userService.getUserByUserLoginId(userDto);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> userResponseDto = userService.getAllUser();
        if(userResponseDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userResponseDto);
    }

}
