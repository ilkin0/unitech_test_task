package com.ilkinmehdiyev.unitech_test_task.service;

import com.ilkinmehdiyev.unitech_test_task.model.dto.UserRegisterRequest;
import com.ilkinmehdiyev.unitech_test_task.model.dto.UserRegisterResponse;
import com.ilkinmehdiyev.unitech_test_task.model.entity.User;

public interface UserService {
    User save(User user);

    UserRegisterResponse register(UserRegisterRequest request);

}
