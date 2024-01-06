package com.todo.api.controllers;

import com.todo.api.controllers.genericResponses.SuccessResponse;
import org.springframework.http.ResponseEntity;


public class MainController {

    protected ResponseEntity<Object> CustomResponse(Object data) {
        return ResponseEntity.ok(new SuccessResponse(true, data));
    }

}
