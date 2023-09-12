package com.ilkinmehdiyev.unitech_test_task.service.impl;

import com.ilkinmehdiyev.unitech_test_task.exceptions.UserAlreadyExistsException;
import com.ilkinmehdiyev.unitech_test_task.model.dto.UserRegisterRequest;
import com.ilkinmehdiyev.unitech_test_task.model.dto.UserRegisterResponse;
import com.ilkinmehdiyev.unitech_test_task.model.entity.User;
import com.ilkinmehdiyev.unitech_test_task.repository.UserRepository;
import com.ilkinmehdiyev.unitech_test_task.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User save(User user) {
        log.info("Persisting user to DB.");
        return userRepository.save(user);
    }

    @Override
    public UserRegisterResponse register(UserRegisterRequest request) {
        log.info("Starting User Registration process");
        String userPin = request.pin();

        boolean userExist = userRepository.findByUsername(userPin).isPresent();
        if (userExist) {
            log.error("User with {} pin/username already exists.", userPin);
            throw new UserAlreadyExistsException();
        }

        User createdUser = createUser(request);
        log.info("User created successfully.");
        return new UserRegisterResponse(createdUser.getId());
    }

    private User createUser(UserRegisterRequest request) {
        User user = new User();
        user.setAccountNonExpired(true);
        user.setEnabled(true);
        user.setUsername(request.pin());
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setAuthorities(Collections.emptyList());
        return userRepository.save(user);
    }
}
