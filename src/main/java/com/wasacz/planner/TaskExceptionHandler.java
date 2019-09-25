package com.wasacz.planner;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TaskExceptionHandler extends Exception {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    ResponseEntity emptyRequestBodyHandler() {
        return ResponseEntity.noContent().build();
    }

}
