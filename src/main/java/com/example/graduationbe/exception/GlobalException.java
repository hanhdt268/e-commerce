package com.example.graduationbe.exception;

import com.example.graduationbe.entities.ResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ResponseObject> generalException(Exception exception) {
        return ResponseEntity.internalServerError().body(
                ResponseObject.builder()
                        .message(exception.getMessage())
                        .http(HttpStatus.INTERNAL_SERVER_ERROR)
                        .build()
        );
    }
}
