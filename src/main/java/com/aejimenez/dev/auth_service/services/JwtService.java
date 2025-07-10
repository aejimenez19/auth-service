package com.aejimenez.dev.auth_service.services;

import com.aejimenez.dev.auth_service.entities.User;

public interface JwtService {

    String generateToken(User user);

}
