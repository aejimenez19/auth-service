package com.aejimenez.dev.auth_service.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    private String name;
    private String phone;
    private String username;
    private String email;
    private String rolName;
}
