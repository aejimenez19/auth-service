package com.aejimenez.dev.auth_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestResponse<T> {
    private String message;
    private T data;
}
