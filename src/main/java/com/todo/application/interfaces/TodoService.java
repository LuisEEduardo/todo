package com.todo.application.interfaces;

import com.todo.application.resources.CreateTodoResource;
import com.todo.application.resources.ResponseTodoResource;
import com.todo.application.resources.UpdateTodoResource;

import java.util.UUID;

public interface TodoService {
    Iterable<ResponseTodoResource> findAll();
    ResponseTodoResource findById(UUID id);
    ResponseTodoResource create(CreateTodoResource todo);
    ResponseTodoResource update(UpdateTodoResource todo);
    Boolean delete(UUID id);
}
