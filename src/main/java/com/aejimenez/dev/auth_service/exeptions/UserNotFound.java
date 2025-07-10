package com.aejimenez.dev.auth_service.exeptions;

public class UserNotFound extends RuntimeException{
    public UserNotFound(String username) {
        super("User not found with username: " + username);
    }
}
