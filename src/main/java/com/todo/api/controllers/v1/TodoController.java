package com.todo.api.controllers.v1;


import com.todo.api.controllers.MainController;
import com.todo.application.interfaces.TodoService;
import com.todo.application.resources.CreateTodoResource;
import com.todo.application.resources.UpdateTodoResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    @Operation(summary = "Realiza um get", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Chamada realizada com sucesso"),
    })
    @GetMapping()
    public ResponseEntity<?> findAll() {
        return CustomResponse(todoService.findAll());
    }

    @Operation(summary = "Realiza um get", method = "GET")
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        return CustomResponse(todoService.findById(id));
    }

    @Operation(summary = "Realiza um get", method = "POST")
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody CreateTodoResource todo) {
        return CustomResponse(todoService.create(todo));
    }

    @Operation(summary = "Realiza um get", method = "PUT")
    @PutMapping()
    public ResponseEntity<?> update(@RequestBody UpdateTodoResource todo) {
        return CustomResponse(todoService.update(todo));
    }

    @Operation(summary = "Realiza um get", method = "DELETE")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        return CustomResponse(todoService.delete(id));
    }
}
