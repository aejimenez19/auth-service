package com.aejimenez.dev.auth_service.exeptions;


import com.aejimenez.dev.auth_service.dtos.RequestResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<RequestResponse> handleRoleNotFoundException(RoleNotFoundException ex) {
        RequestResponse response = new RequestResponse<>(ex.getMessage(), null);
        return ResponseEntity.status(404).body(response);
    }


    @ExceptionHandler(UserAlreadyExists.class)
    public ResponseEntity<RequestResponse> handleUserAlreadyExists(UserAlreadyExists ex) {
        RequestResponse response = new RequestResponse<>(ex.getMessage(), null);
        return ResponseEntity.status(409).body(response);
    }


    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<RequestResponse> handleUserNotFound(UserNotFound ex) {
        RequestResponse response = new RequestResponse<>(ex.getMessage(), null);
        return ResponseEntity.status(404).body(response);
    }

    @ExceptionHandler(PasswordNotMatch.class)
    public ResponseEntity<RequestResponse> handlePasswordNotMatch(PasswordNotMatch ex) {
        RequestResponse response = new RequestResponse<>(ex.getMessage(), null);
        return ResponseEntity.status(401).body(response);
    }
}
