package com.ilkinmehdiyev.unitech_test_task;

import com.ilkinmehdiyev.unitech_test_task.model.dto.UserRegisterRequest;

public class TestUtil {
    public static final String TEST_PIN = "123456";
    public static final String TEST_PASSWORD = "abcd123456";

    public static UserRegisterRequest getUserRegisterRequest() {
        return new UserRegisterRequest(TEST_PIN, TEST_PASSWORD);
    }
}
