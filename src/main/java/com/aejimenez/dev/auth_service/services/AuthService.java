package com.aejimenez.dev.auth_service.services;

import com.aejimenez.dev.auth_service.dtos.UserRegisterRequest;


public interface AuthService {
    UserRegisterRequest registerUser(UserRegisterRequest user);
}
