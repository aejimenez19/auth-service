package com.aejimenez.dev.auth_service.controllers;

import com.aejimenez.dev.auth_service.dtos.RequestResponse;
import com.aejimenez.dev.auth_service.dtos.UserResponse;
import com.aejimenez.dev.auth_service.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserResponse> getUsers(Authentication authentication) {
        Map<String, Object> principal = (Map<String, Object>) authentication.getPrincipal();

        String userId = (String) principal.get("userId");

        return ResponseEntity.ok(userService.getUserById(userId));
    }


    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<UserResponse>> getAllUsers(Authentication authentication) {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
