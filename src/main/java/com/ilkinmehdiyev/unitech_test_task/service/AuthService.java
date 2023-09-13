package com.ilkinmehdiyev.unitech_test_task.service;

import com.ilkinmehdiyev.unitech_test_task.model.dto.UserLoginRequest;
import com.ilkinmehdiyev.unitech_test_task.model.dto.UserLoginResponse;

public interface AuthService {
    UserLoginResponse login(UserLoginRequest request);
}
