package com.ilkinmehdiyev.unitech_test_task.service.impl;

import com.ilkinmehdiyev.unitech_test_task.model.dto.UserRegisterRequest;
import com.ilkinmehdiyev.unitech_test_task.model.entity.User;
import com.ilkinmehdiyev.unitech_test_task.service.AuthService;
import com.ilkinmehdiyev.unitech_test_task.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
}
