package com.aejimenez.dev.auth_service.controllers;

import com.aejimenez.dev.auth_service.dtos.RequestResponse;
import com.aejimenez.dev.auth_service.dtos.UserRegisterRequest;
import com.aejimenez.dev.auth_service.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping()
    public ResponseEntity<RequestResponse<UserRegisterRequest>> userRegister(@RequestBody UserRegisterRequest request) {
           UserRegisterRequest registeredUser = authService.registerUser(request);
           return ResponseEntity.ok(new RequestResponse<>("User registered successfully", registeredUser));
    }
}
