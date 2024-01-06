package com.todo.api.exceptions;

import com.todo.api.controllers.genericResponses.ErrorResponse;
import com.todo.api.exceptions.custom.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return new ErrorResponse(false, errors, LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleMethodNotFoundException(NotFoundException exception) {
        return new ErrorResponse(false, exception.getMessage(), LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
    }

//    java.util.NoSuchElementException: No value present
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleMethodNoSuchElementException(NoSuchElementException exception) {
        return new ErrorResponse(false, exception.getMessage(), LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
    }

}
