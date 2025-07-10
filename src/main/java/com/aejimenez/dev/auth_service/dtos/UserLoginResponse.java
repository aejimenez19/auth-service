package com.aejimenez.dev.auth_service.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoginResponse {
    private String token;
    private String username;
}
