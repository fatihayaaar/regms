package com.fayardev.regms.userservice.exceptions.handler;


import com.fayardev.regms.userservice.exceptions.UserException;
import com.fayardev.regms.userservice.exceptions.errors.ApiError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    DateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        List<String> errors = new ArrayList<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }

        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        ApiError apiError = new ApiError(
                getTimeStamp(),
                HttpStatus.BAD_REQUEST,
               " ex.getLocalizedMessage()",
                errors,
                "");

        return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ApiError apiError = new ApiError(
                getTimeStamp(),
                HttpStatus.BAD_REQUEST,
                "ex.getLocalizedMessage()",
                ex.getParameterName() + " parameter is missing",
                "",
                "");

        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {

        ApiError apiError = new ApiError(
                getTimeStamp(),
                HttpStatus.BAD_REQUEST,
                "ex.getLocalizedMessage()",
                ex.getName() + " should be of type " + ex.getRequiredType().getName(),
                "",
                "");

        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({IllegalStateException.class})
    public ResponseEntity<Object> illegalStateMismatch(IllegalStateException ex, WebRequest request) {

        ApiError apiError = new ApiError(
                getTimeStamp(),
                HttpStatus.BAD_REQUEST,
                "ex.getLocalizedMessage()",
                ex.toString(),
                "",
                "");

        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({UserException.class})
    public ResponseEntity<Object> handleMethodUser(UserException ex, WebRequest request) {

        ApiError apiError = new ApiError(
                getTimeStamp(),
                HttpStatus.BAD_REQUEST,
                "ex.getLocalizedMessage()",
                UserException.NAME,
                ex.getError().name(),
                ex.getErrorComponent().name());

        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    private String getTimeStamp() {
        return dateFormat.format(new Date());
    }
}