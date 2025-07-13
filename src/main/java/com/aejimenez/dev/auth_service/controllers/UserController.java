package com.aejimenez.dev.auth_service.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public String getUsers() {
        // This method would typically return a list of users.
        // For now, it returns a simple message.
        return "List of users";
    }
}
