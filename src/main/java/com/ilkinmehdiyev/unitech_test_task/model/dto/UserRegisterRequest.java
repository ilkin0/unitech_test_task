package com.ilkinmehdiyev.unitech_test_task.model.dto;

import jakarta.validation.constraints.NotEmpty;

public record UserRegisterRequest(@NotEmpty String pin, @NotEmpty String password) {
}
