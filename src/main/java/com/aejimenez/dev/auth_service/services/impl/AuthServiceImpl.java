package com.aejimenez.dev.auth_service.services.impl;

import com.aejimenez.dev.auth_service.dtos.UserRegisterRequest;
import com.aejimenez.dev.auth_service.entities.Rol;
import com.aejimenez.dev.auth_service.entities.Roles;
import com.aejimenez.dev.auth_service.entities.User;
import com.aejimenez.dev.auth_service.exeptions.RoleNotFoundException;
import com.aejimenez.dev.auth_service.exeptions.UserAlreadyExists;
import com.aejimenez.dev.auth_service.repositories.RolRepository;
import com.aejimenez.dev.auth_service.repositories.UserRepository;
import com.aejimenez.dev.auth_service.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserRegisterRequest registerUser(UserRegisterRequest user) {
        validateRoleAndUsername(user);

        Rol newRol = rolRepository.findByRol(Roles.valueOf(user.getRol()))
                .orElseThrow(() -> new RoleNotFoundException(user.getRol()));

        User newUser = createUser(user, newRol);
        userRepository.save(newUser);

        return mapToUserRegisterRequest(newUser);
    }

    private void validateRoleAndUsername(UserRegisterRequest user) {
        try {
            Roles.valueOf(user.getRol());
        } catch (IllegalArgumentException e) {
            throw new RoleNotFoundException(user.getRol());
        }
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UserAlreadyExists(user.getUsername());
        }
    }

    private User createUser(UserRegisterRequest user, Rol newRol) {
        return User.builder()
                .name(user.getName())
                .phone(user.getPhone())
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .email(user.getEmail())
                .rol(newRol)
                .build();
    }

    private UserRegisterRequest mapToUserRegisterRequest(User newUser) {
        return UserRegisterRequest.builder()
                .name(newUser.getName())
                .phone(newUser.getPhone())
                .username(newUser.getUsername())
                .email(newUser.getEmail())
                .rol(newUser.getRol().getRol().name())
                .build();
    }
}
