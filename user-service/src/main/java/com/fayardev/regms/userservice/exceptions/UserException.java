package com.fayardev.regms.userservice.exceptions;

import com.fayardev.regms.userservice.exceptions.enums.ErrorComponents;
import com.fayardev.regms.userservice.exceptions.enums.Errors;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserException extends Exception {

    public static final String NAME = "UserException";
    private final String message;
    private ErrorComponents errorComponent;
    private Errors error;

    public UserException(String message) {
        this.message = message;
    }

    public UserException(String message, Errors error, ErrorComponents errorComponent) {
        this.message = message;
        this.error = error;
        this.errorComponent = errorComponent;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
