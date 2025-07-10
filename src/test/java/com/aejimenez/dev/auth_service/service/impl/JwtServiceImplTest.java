package com.aejimenez.dev.auth_service.service.impl;

import com.aejimenez.dev.auth_service.entities.Rol;
import com.aejimenez.dev.auth_service.entities.Roles;
import com.aejimenez.dev.auth_service.entities.User;
import com.aejimenez.dev.auth_service.services.impl.JwtServiceImpl;
import io.jsonwebtoken.security.WeakKeyException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JwtServiceImplTest {

    @Mock
    private User user;

    @InjectMocks
    private JwtServiceImpl jwtService;


    @Test
    void generatesTokenWithValidUser() {
        UUID userId = UUID.randomUUID();

        // Prepara usuario real
        User user = new User();
        user.setId(userId);
        user.setRol(Rol.builder().rol(Roles.ROLE_USER).build());
        ReflectionTestUtils.setField(jwtService, "secretKey", "V3ryS3cur3JWTSecretKeyExAmpl3!123456");
        // Ejecuta
        String token = jwtService.generateToken(user);

        // Verifica
        assertNotNull(token);
        assertTrue(token.contains("."));
    }

    @Test
    void throwsExceptionWhenSecretKeyIsInvalid() {

        UUID userId = UUID.randomUUID();

        User user = new User();
        user.setId(userId);
        user.setRol(Rol.builder().rol(Roles.ROLE_USER).build());

        ReflectionTestUtils.setField(jwtService, "secretKey", "short");

        assertThrows(WeakKeyException.class, () -> jwtService.generateToken(user));
    }

}
