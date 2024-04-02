package com.todo.api.controllers.v1;


import com.todo.api.controllers.MainController;
import com.todo.application.interfaces.TodoService;
import com.todo.application.resources.RequestCreateTodoResource;
import com.todo.application.resources.RequestUpdateTodoResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/v1/todo", produces = {"application/json"})
@Tag(name = "Todo")
public class TodoController extends MainController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @Operation(summary = "Get all todos", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Chamada realizada com sucesso"),
    })
    @GetMapping()
    public ResponseEntity<?> findAll() {
        return CustomResponse(todoService.findAll());
    }

    @Operation(summary = "get by id", method = "GET")
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        return CustomResponse(todoService.findById(id));
    }

    @Operation(summary = "create todo", method = "POST")
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody @Valid RequestCreateTodoResource todo) {
        return CustomResponse(todoService.create(todo));
    }

    @Operation(summary = "put todo", method = "PUT")
    @PutMapping()
    public ResponseEntity<?> update(@RequestBody @Valid RequestUpdateTodoResource todo) {
        return CustomResponse(todoService.update(todo));
    }

    @Operation(summary = "delete todo", method = "DELETE")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        return CustomResponse(todoService.delete(id));
    }
}
