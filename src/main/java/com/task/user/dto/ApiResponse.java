package com.task.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class ApiResponse<T> {
    private String status;
    private int statusCode;
    private String message;
    private T data;
}
