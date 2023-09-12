package com.ilkinmehdiyev.unitech_test_task.controller;

import com.ilkinmehdiyev.unitech_test_task.model.dto.UserRegisterRequest;
import com.ilkinmehdiyev.unitech_test_task.model.dto.UserRegisterResponse;
import com.ilkinmehdiyev.unitech_test_task.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponse> register(@RequestBody @Valid UserRegisterRequest request) {
        UserRegisterResponse registeredUser = userService.register(request);

        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }
}
