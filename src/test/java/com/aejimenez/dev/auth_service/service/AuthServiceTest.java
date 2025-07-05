package com.aejimenez.dev.auth_service.service;

import com.aejimenez.dev.auth_service.dtos.UserRegisterRequest;
import com.aejimenez.dev.auth_service.entities.Rol;
import com.aejimenez.dev.auth_service.entities.Roles;
import com.aejimenez.dev.auth_service.exeptions.RoleNotFoundException;
import com.aejimenez.dev.auth_service.exeptions.UserAlreadyExists;
import com.aejimenez.dev.auth_service.repositories.RolRepository;
import com.aejimenez.dev.auth_service.repositories.UserRepository;
import com.aejimenez.dev.auth_service.services.impl.AuthServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RolRepository rolRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthServiceImpl userService;

    @Test
    void registersUserWhenRoleAndUsernameAreValid() {
        UserRegisterRequest request = UserRegisterRequest.builder()
                .name("Alice")
                .phone("987654321")
                .username("alice123")
                .password("securePass")
                .email("alice@example.com")
                .rol("ROLE_USER")
                .build();

        when(userRepository.existsByUsername("alice123")).thenReturn(false);
        when(rolRepository.findByRol(Roles.valueOf("ROLE_USER"))).thenReturn(java.util.Optional.of(
                Rol.builder().rol(Roles.ROLE_USER).build()));

        UserRegisterRequest response = userService.registerUser(request);

        assertEquals("Alice", response.getName());
        assertEquals("987654321", response.getPhone());
        assertEquals("alice123", response.getUsername());
        assertEquals("alice@example.com", response.getEmail());
        assertEquals("ROLE_USER", response.getRol());
    }

    @Test
    void throwsExceptionWhenRoleDoesNotExist() {
        UserRegisterRequest request = UserRegisterRequest.builder()
                .rol("INVALID_ROLE")
                .build();


        assertThrows(RoleNotFoundException.class, () -> userService.registerUser(request));
    }

    @Test
    void throwsExceptionWhenUsernameAlreadyExists() {
        UserRegisterRequest request = UserRegisterRequest.builder()
                .username("existingUser")
                .rol("ROLE_USER")
                .build();

        when(userRepository.existsByUsername("existingUser")).thenReturn(true);

        assertThrows(UserAlreadyExists.class, () -> userService.registerUser(request));
    }
}