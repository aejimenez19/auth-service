package com.aejimenez.dev.auth_service.exeptions;

public class UserAlreadyExists extends RuntimeException {
    public UserAlreadyExists(String username) {
        super("User already exists with username: " + username);
    }
}
