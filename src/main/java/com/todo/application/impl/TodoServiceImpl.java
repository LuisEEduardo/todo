package com.todo.application.impl;

import com.todo.api.exceptions.custom.NotFoundException;
import com.todo.application.interfaces.TodoService;
import com.todo.application.mapper.TodoMapper;
import com.todo.application.resources.CreateTodoResource;
import com.todo.application.resources.ResponseTodoResource;
import com.todo.application.resources.UpdateTodoResource;
import com.todo.business.Todo;
import com.todo.repository.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository repository;
    private TodoMapper mapper;

    public TodoServiceImpl(TodoRepository repository, TodoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Iterable<ResponseTodoResource> findAll() {
        log.info("find all todos");
        return mapper.todosToResponseTodoResources(repository.findAll());
    }

    @Override
    public ResponseTodoResource findById(UUID id) {
        log.info("find todo by id");
        return mapper.todoToResponseTodoResource(repository.findById(id).get());
    }

    @Override
    public ResponseTodoResource create(CreateTodoResource todo) {
        var model = new Todo(todo.getDescription());
        repository.save(model);

        log.info("create todo");

        return mapper.todoToResponseTodoResource(model);
    }

    @Override
    public ResponseTodoResource update(UpdateTodoResource todo) {
        var modelOptional = repository.findById(todo.getId());

        if (modelOptional.isEmpty()) {
            throw new NotFoundException("todo não encontrado");
        }

        var model = modelOptional.get();

        model.Edit(todo.getStatus(), todo.getDescription());
        repository.save(model);

        log.info("update todo");

        return mapper.todoToResponseTodoResource(model);
    }

    @Override
    public Boolean delete(UUID id) {
        var exists = repository.existsById(id);

        if (!exists) {
            throw new NotFoundException("todo não encontrado");
        }

        repository.deleteById(id);

        log.info("delete todo");

        return true;
    }
}
