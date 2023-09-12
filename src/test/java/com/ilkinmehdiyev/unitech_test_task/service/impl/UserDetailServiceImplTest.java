package com.ilkinmehdiyev.unitech_test_task.service.impl;

import com.ilkinmehdiyev.unitech_test_task.TestUtil;
import com.ilkinmehdiyev.unitech_test_task.exceptions.UserNotFoundException;
import com.ilkinmehdiyev.unitech_test_task.model.dto.UserRegisterRequest;
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

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class UserDetailServiceImplTest {

    @InjectMocks
    private UserDetailServiceImpl userDetailService;

    @Mock
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void init() {
        user = new User();
        user.setUsername(TestUtil.TEST_PIN);
    }

    @Test
    @DisplayName("Test when user load by username success")
    public void testWhenUserFoundByUserName() {
        UserRegisterRequest registerRequest = TestUtil.getUserRegisterRequest();

        Mockito.when(userRepository.findByUsername(registerRequest.pin())).thenReturn(Optional.of(user));

        User byUsername = (User) userDetailService.loadUserByUsername(registerRequest.pin());

        Assertions.assertNotNull(byUsername);
        Assertions.assertEquals(user.getUsername(), byUsername.getUsername());
    }


    @Test
    @DisplayName("Test when user load by username not success")
    public void testWhenUserNotFoundByUserName() {
        Mockito.when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());

        UserNotFoundException notFoundException = Assertions.assertThrows(UserNotFoundException.class, () -> userDetailService.loadUserByUsername(anyString()));

        Assertions.assertNotNull(notFoundException);
    }

}