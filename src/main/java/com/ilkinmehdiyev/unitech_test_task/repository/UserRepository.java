package com.ilkinmehdiyev.unitech_test_task.repository;

import com.ilkinmehdiyev.unitech_test_task.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
