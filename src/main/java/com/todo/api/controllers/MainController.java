package com.todo.api.controllers;

import org.springframework.http.ResponseEntity;


public class MainController {

    protected ResponseEntity<Object> CustomResponse(Object data) {
        return ResponseEntity.ok(new GenericSuccessResponse(true, data));
    }

}
