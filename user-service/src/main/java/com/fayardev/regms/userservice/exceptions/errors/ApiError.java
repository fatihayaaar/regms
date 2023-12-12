package com.fayardev.regms.userservice.exceptions.errors;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class ApiError {

    private String timestamp;
    private HttpStatus status;
    private String message;
    private List<String> errors;
    private String errorCode;
    private String componentName;

    public ApiError(String timestamp, HttpStatus status, String message, List<String> errors, String errorCode) {
        super();
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.errors = errors;
        this.errorCode = errorCode;
    }

    public ApiError(String timestamp, HttpStatus status, String message, String error, String errorCode, String componentName) {
        super();
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.errors = Collections.singletonList(error);
        this.errorCode = errorCode;
        this.componentName = componentName;
    }
}