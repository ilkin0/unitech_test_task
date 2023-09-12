package com.ilkinmehdiyev.unitech_test_task.service.impl;

import com.ilkinmehdiyev.unitech_test_task.TestUtil;
import com.ilkinmehdiyev.unitech_test_task.exceptions.UserAlreadyExistsException;
import com.ilkinmehdiyev.unitech_test_task.model.dto.UserRegisterRequest;
import com.ilkinmehdiyev.unitech_test_task.model.dto.UserRegisterResponse;
import com.ilkinmehdiyev.unitech_test_task.model.entity.User;
import com.ilkinmehdiyev.unitech_test_task.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private User user;

    @BeforeEach
    void init() {
        user = new User();
        user.setUsername(TestUtil.TEST_PIN);
        user.setAccountNonExpired(true);
        user.setEnabled(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setAuthorities(Collections.emptyList());
    }


    @Test
    @DisplayName("Test when user saved successfully")
    public void testWhenUserSaveSuccess() {
        Mockito.when(userRepository.save(user)).thenReturn(user);

        User saved = userService.save(user);

        Assertions.assertNotNull(saved);
        Assertions.assertEquals(TestUtil.TEST_PIN, saved.getUsername());
        Mockito.verify(userRepository, Mockito.atMostOnce()).save(any(User.class));
    }

    @Test
    @DisplayName("Test when user register is success")
    public void testWhenUserRegisterSuccess() {
        UserRegisterRequest registerRequest = TestUtil.getUserRegisterRequest();
        user.setId(1L);

        Mockito.when(userRepository.findByUsername(registerRequest.pin())).thenReturn(Optional.empty());
        Mockito.when(userRepository.save(any(User.class))).thenReturn(user);

        UserRegisterResponse registered = userService.register(registerRequest);

        Assertions.assertNotNull(registered);
        Assertions.assertNotNull(registered.userId());
    }

    @Test
    @DisplayName("Test when user register is not success")
    public void testWhenUserRegisterPinAlreadyExistsThenThrowError() {
        UserRegisterRequest registerRequest = TestUtil.getUserRegisterRequest();

        Mockito.when(userRepository.findByUsername(registerRequest.pin())).thenReturn(Optional.of(user));

        UserAlreadyExistsException exception = Assertions.assertThrows(UserAlreadyExistsException.class, () -> userService.register(registerRequest));
        Assertions.assertNotNull(exception);
        Mockito.verify(userRepository, Mockito.never()).save(any());
    }
}