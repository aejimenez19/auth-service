package com.aejimenez.dev.auth_service.services;

import com.aejimenez.dev.auth_service.dtos.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse getUserById(String id);

    List<UserResponse> getAllUsers();
}
