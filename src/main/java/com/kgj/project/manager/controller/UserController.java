package com.kgj.project.manager.controller;

import com.kgj.project.manager.dto.UserDto;
import com.kgj.project.manager.entity.User;
import com.kgj.project.manager.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<User> signUp(@RequestBody UserDto user) {
        log.info("createUser: {}", user);
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<User> signIn(@RequestBody UserDto userDto) {
        log.info("getUser: {}", userDto);
        User user = userService.getUserByUserLoginId(userDto);
        if(user == null) {
            return ResponseEntity.ofNullable(null);
        } else {
            return ResponseEntity.ok(user);
        }
    }

}
