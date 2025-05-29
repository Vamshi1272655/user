package com.task.user.exception;

import com.task.user.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ApiResponse<Void> handleGenericException(Exception ex) {
        return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.name(),HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), null);
    }
}
