package com.todo.api.controllers;


import com.todo.application.interfaces.TodoService;
import com.todo.application.resources.CreateTodoResource;
import com.todo.application.resources.UpdateTodoResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/todo")
public class TodoController extends MainController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping()
    public ResponseEntity<?> findAll() {
        return CustomResponse(todoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        return CustomResponse(todoService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody CreateTodoResource todo) {
        return CustomResponse(todoService.create(todo));
    }

    @PutMapping()
    public ResponseEntity<?> update(@RequestBody UpdateTodoResource todo) {
        return CustomResponse(todoService.update(todo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        return CustomResponse(todoService.delete(id));
    }
}
