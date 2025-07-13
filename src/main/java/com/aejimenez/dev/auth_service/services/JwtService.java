package com.aejimenez.dev.auth_service.services;

import com.aejimenez.dev.auth_service.entities.User;

public interface JwtService {

    String generateToken(User user);

    String getRoleFromToken(String token);
    String getUserIdFromToken(String token);

    Boolean isTokenValid(String token);

}
