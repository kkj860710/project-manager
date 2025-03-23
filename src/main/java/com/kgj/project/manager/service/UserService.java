package com.kgj.project.manager.service;

import com.kgj.project.manager.bcrypt.Bcrypt;
import com.kgj.project.manager.dto.UserDto;
import com.kgj.project.manager.dto.UserResponseDto;
import com.kgj.project.manager.entity.User;
import com.kgj.project.manager.entity.enums.UserRole;
import com.kgj.project.manager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final Bcrypt bcrypt;

    public UserDto getUserByUserLoginId(UserDto userDto) {
        UserDto returnDto = new UserDto();
        User user = userRepository.findByEmail(userDto.getEmail());
        if (user != null && bcrypt.isMatch(userDto.getPassword(), user.getPasswordHash())) {
            String userRole = String.valueOf(user.getRole());
            returnDto.setEmail(user.getEmail());
            returnDto.setUsername(user.getUsername());
            returnDto.setUserId(user.getUserId());
//            returnDto.setPassword(user.getPasswordHash());
            returnDto.setRole(userRole);
        } else {
            returnDto = null;
        }
        return returnDto;
    }

    public UserDto createUser(UserDto userDto) {
        UserDto returnDto = new UserDto();
        User createdUser = new User();
        if (userRepository.count() == 0) {
            createdUser.setRole(UserRole.ADMIN);
        } else {
            try {
                createdUser.setRole(
                        Optional.ofNullable(userDto.getRole())
                                .filter(role -> !role.isEmpty())
                                .map(UserRole::valueOf)
                                .orElse(UserRole.MEMBER)
                );
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("잘못된 역할(Role) 값이 입력되었습니다: " + userDto.getRole());
            }
        }

        createdUser.setPasswordHash(bcrypt.encrypt(userDto.getPassword()));
        createdUser.setEmail(userDto.getEmail());
        createdUser.setUsername(userDto.getUsername());

        User savedUser = userRepository.save(createdUser);

        returnDto.setEmail(savedUser.getEmail());
        returnDto.setUsername(savedUser.getUsername());
        returnDto.setUserId(savedUser.getUserId());
//        returnDto.setPassword(savedUser.getPasswordHash());
        returnDto.setRole(String.valueOf(savedUser.getRole()));

        return returnDto;
    }


    public List<UserResponseDto> getAllUser () {
        List<User> userList = userRepository.findAll();
        List<UserResponseDto> userResponseDtoList = new ArrayList<>();
        for(User user : userList) {
            UserResponseDto userResponseDto = new UserResponseDto();
            userResponseDto.setUsername(user.getUsername());
            userResponseDto.setUserId(user.getUserId());
            userResponseDtoList.add(userResponseDto);
        }
        return userResponseDtoList;
    }

}
