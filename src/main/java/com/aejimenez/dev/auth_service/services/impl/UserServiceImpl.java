package com.aejimenez.dev.auth_service.services.impl;

import com.aejimenez.dev.auth_service.dtos.UserResponse;
import com.aejimenez.dev.auth_service.repositories.UserRepository;
import com.aejimenez.dev.auth_service.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    public UserResponse getUserById(String id) {
    return userRepository.findById(UUID.fromString(id))
            .map(user -> UserResponse.builder()
                    .name(user.getName())
                    .phone(user.getPhone())
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .rolName(user.getRol().getRol().name())
                    .build())
            .orElse(null);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> UserResponse.builder()
                        .name(user.getName())
                        .phone(user.getPhone())
                        .username(user.getUsername())
                        .email(user.getEmail())
                        .rolName(user.getRol().getRol().name())
                        .build())
                .toList();
    }
}
