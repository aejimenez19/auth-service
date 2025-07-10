package com.aejimenez.dev.auth_service.exeptions;

public class PasswordNotMatch extends RuntimeException{
    public PasswordNotMatch() {
        super("Incorrect password");
    }
}
