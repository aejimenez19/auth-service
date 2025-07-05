package com.aejimenez.dev.auth_service.repositories;

import com.aejimenez.dev.auth_service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByUsername(String username);

    boolean existsByUsername(String username);
}
