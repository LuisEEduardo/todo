package com.todo.api.exceptions;

import com.todo.api.controllers.GenericErrorResponse;
import com.todo.api.controllers.GenericSuccessResponse;
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

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GenericErrorResponse handleMethodArgumentException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return new GenericErrorResponse(false, errors, LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
    }

    @ExceptionHandler(NotFoundException.class)
    public GenericErrorResponse handleMethodNotFoundException(NotFoundException exception) {
        return new GenericErrorResponse(false, exception.getMessage(), LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
    }

}
