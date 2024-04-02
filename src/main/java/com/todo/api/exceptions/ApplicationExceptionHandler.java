package com.todo.api.exceptions;

import com.todo.api.controllers.genericResponses.ErrorResponse;
import com.todo.api.exceptions.custom.ForbiddenException;
import com.todo.api.exceptions.custom.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Slf4j
@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        log.error("handleMethodArgumentException => " + errors.toString());

        return new ErrorResponse(false, errors, LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleMethodNotFoundException(NotFoundException exception) {
        log.error("handleMethodNotFoundException => " + exception.getMessage());
        return new ErrorResponse(false, exception.getMessage(), LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleMethodNoSuchElementException(NoSuchElementException exception) {
        log.error("handleMethodNoSuchElementException => " + exception.getMessage());
        return new ErrorResponse(false, exception.getMessage(), LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleMethodException(Exception exception) {
        log.error("handleMethodException => " + exception.getMessage());
        return new ErrorResponse(false, "Internal server error", LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handleForbiddenException(ForbiddenException exception) {
        log.error("handleForbiddenException => " + exception.getMessage());
        return new ErrorResponse(false, exception.getMessage(), LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleUsernameNotFoundException(UsernameNotFoundException exception) {
        log.error("handleUsernameNotFoundException => " + exception.getMessage());
        return new ErrorResponse(false, exception.getMessage(), LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleBadCredentialsException(BadCredentialsException exception) {
        log.error("handleBadCredentialsException => " + exception.getMessage());
        return new ErrorResponse(false, "login or password invalid", LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
    }

}
