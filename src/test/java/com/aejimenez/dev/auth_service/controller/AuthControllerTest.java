package com.aejimenez.dev.auth_service.controller;

import com.aejimenez.dev.auth_service.controllers.AuthController;
import com.aejimenez.dev.auth_service.dtos.RequestResponse;
import com.aejimenez.dev.auth_service.dtos.UserRegisterRequest;
import com.aejimenez.dev.auth_service.services.AuthService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    @Test
    void returnsSuccessResponseWhenUserIsRegistered() {
        UserRegisterRequest request = UserRegisterRequest.builder()
                .name("John Doe")
                .phone("123456789")
                .username("johndoe")
                .password("password123")
                .email("johndoe@example.com")
                .rol("USER")
                .build();

        UserRegisterRequest registeredUser = UserRegisterRequest.builder()
                .name("John Doe")
                .phone("123456789")
                .username("johndoe")
                .email("johndoe@example.com")
                .rol("USER")
                .build();

        when(authService.registerUser(request)).thenReturn(registeredUser);

        ResponseEntity<RequestResponse<UserRegisterRequest>> response = authController.userRegister(request);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("User registered successfully", response.getBody().getMessage());
        assertEquals(registeredUser, response.getBody().getData());
    }


}
