package com.ilkinmehdiyev.unitech_test_task.service.impl;

import com.ilkinmehdiyev.unitech_test_task.exceptions.UserNotFoundException;
import com.ilkinmehdiyev.unitech_test_task.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    log.error("User not found with {} username", username);
                    return new UserNotFoundException(String.format("User not found with %s username", username));
                });
    }
}
