package com.ilkinmehdiyev.unitech_test_task.service.impl;

import com.ilkinmehdiyev.unitech_test_task.exceptions.UserUnauthorizedException;
import com.ilkinmehdiyev.unitech_test_task.model.dto.UserLoginRequest;
import com.ilkinmehdiyev.unitech_test_task.model.dto.UserLoginResponse;
import com.ilkinmehdiyev.unitech_test_task.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;


    @Override
    public UserLoginResponse login(UserLoginRequest request) {
        log.info("Authentication requested by {}", request.pin());
        var authenticationToken = new UsernamePasswordAuthenticationToken(request.pin(), request.password());

        Authentication authenticated;
        try {
            authenticated = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            log.info("User successfully authenticated.");
        } catch (AuthenticationException e) {
            log.error("User not Authenticated. Username or Password is incorrect");
            throw new UserUnauthorizedException("User not Authenticated. Username or Password is incorrect");
        }
        SecurityContextHolder.getContext().setAuthentication(authenticated);

        return new UserLoginResponse();
    }
}
