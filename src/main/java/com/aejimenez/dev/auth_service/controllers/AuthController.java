package com.aejimenez.dev.auth_service.controllers;

import com.aejimenez.dev.auth_service.dtos.RequestResponse;
import com.aejimenez.dev.auth_service.dtos.UserLoginRequest;
import com.aejimenez.dev.auth_service.dtos.UserLoginResponse;
import com.aejimenez.dev.auth_service.dtos.UserRegisterRequest;
import com.aejimenez.dev.auth_service.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    /**
     * Endpoint to register a new user.
     *
     * @param request the user registration request containing user details
     * @return a response entity containing the registered user details
     */
    @PostMapping()
    public ResponseEntity<RequestResponse<UserRegisterRequest>> userRegister(@RequestBody UserRegisterRequest request) {
           UserRegisterRequest registeredUser = authService.registerUser(request);
           return ResponseEntity.ok(new RequestResponse<>("User registered successfully", registeredUser));
    }

    /**
     * Endpoint to log in a user.
     *
     * @param userLoginRequest the user login request containing login credentials
     * @return a response entity containing the login response with user details
     */
    @GetMapping
    public ResponseEntity<RequestResponse<UserLoginResponse>> userLogin(@RequestBody UserLoginRequest userLoginRequest) {
        UserLoginResponse userLoginResponse = authService.loginUser(userLoginRequest);
        return ResponseEntity.ok(new RequestResponse<>("User successfully logged in", userLoginResponse));
    }
}
