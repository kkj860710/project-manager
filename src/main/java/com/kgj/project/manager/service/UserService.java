package com.kgj.project.manager.service;

import com.kgj.project.manager.bcrypt.Bcrypt;
import com.kgj.project.manager.dto.UserDto;
import com.kgj.project.manager.entity.User;
import com.kgj.project.manager.entity.enums.UserRole;
import com.kgj.project.manager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final Bcrypt bcrypt;

    public User getUserByUserLoginId(UserDto userDto) {
        User getUser = new User();
        User user = userRepository.findByEmail(userDto.getEmail());
        if (user != null) {
            if (bcrypt.isMatch(userDto.getPassword(), user.getPasswordHash())) {
                getUser = user;
            }
        } else {
            getUser = null;
        }
        return getUser;
    }



    public User createUser(UserDto user) {
        User createdUser = new User();
        List<User> userList = userRepository.findAll();
        if( userList.isEmpty() ) {
            createdUser.setRole(UserRole.ADMIN);
        } else {
            createdUser.setRole(UserRole.valueOf(user.getRole()));
        }

        createdUser.setPasswordHash(bcrypt.encrypt(user.getPassword()));
        createdUser.setEmail(user.getEmail());
        createdUser.setUsername(user.getUsername());
        return userRepository.save(createdUser);
    }

}
