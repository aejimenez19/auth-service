package com.aejimenez.dev.auth_service.service.impl;

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
public class AuthServiceImplentTest {

        @Mock
        private UserRepository userRepository;

        @Mock
        private RolRepository rolRepository;

        @Mock
        private PasswordEncoder passwordEncoder;

        @InjectMocks
        private AuthServiceImpl userService;

        @Test
        void registerUserSuccessfully() {
            UserRegisterRequest request = UserRegisterRequest.builder()
                    .name("John Doe")
                    .phone("123456789")
                    .username("johndoe")
                    .password("password123")
                    .email("johndoe@example.com")
                    .rol("ROLE_USER")
                    .build();

            when(userRepository.existsByUsername("johndoe")).thenReturn(false);
            when(rolRepository.findByRol(Roles.valueOf("ROLE_USER"))).thenReturn(java.util.Optional.of(
                    Rol.builder().rol(Roles.ROLE_USER).build()));


            UserRegisterRequest response = userService.registerUser(request);

            assertEquals("John Doe", response.getName());
            assertEquals("123456789", response.getPhone());
            assertEquals("johndoe", response.getUsername());
            assertEquals("johndoe@example.com", response.getEmail());
            assertEquals("ROLE_USER", response.getRol());
        }

        @Test
        void registerUserFailsWhenRoleNotFound() {
            UserRegisterRequest request = UserRegisterRequest.builder()
                    .rol("ROLE_ADMINS")
                    .build();


            assertThrows(RoleNotFoundException.class, () -> userService.registerUser(request));
        }

        @Test
        void registerUserFailsWhenUsernameAlreadyExists() {
            UserRegisterRequest request = UserRegisterRequest.builder()
                    .username("existinguser")
                    .rol("ROLE_USER")
                    .build();

            when(userRepository.existsByUsername("existinguser")).thenReturn(true);

            assertThrows(UserAlreadyExists.class, () -> userService.registerUser(request));
        }

}
