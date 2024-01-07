package com.todo.application;

import com.todo.application.interfaces.TodoService;
import com.todo.application.mapper.TodoMapper;
import com.todo.application.resources.CreateTodoResource;
import com.todo.application.resources.ResponseTodoResource;
import com.todo.application.resources.UpdateTodoResource;
import com.todo.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TodoServiceImplTest {

    @Mock
    private TodoRepository repository;

    @Mock
    private TodoMapper mapper;

    @Mock
    private TodoService todoService;

    private Iterable<ResponseTodoResource> mockedResponseList;
    private ResponseTodoResource mockedResponseTodoResource;
    private UUID id;

    @BeforeEach
    void setUp() {
        mockedResponseList = Arrays.asList(
                new ResponseTodoResource(),
                new ResponseTodoResource()
        );

        mockedResponseTodoResource = new ResponseTodoResource();
        id = UUID.randomUUID();
    }

    @Test
    @DisplayName("Should find all todo with success")
    void shouldFindAllTodosWithSuccess() {
        when(todoService.findAll()).thenReturn(mockedResponseList);

        Iterable<ResponseTodoResource> result = todoService.findAll();

        assertEquals(mockedResponseList, result);
    }

    @Test
    @DisplayName("Should find by id todo with success")
    void shouldFindByIdWithSuccess() {
        when(todoService.findById(id)).thenReturn(mockedResponseTodoResource);

        var result = todoService.findById(id);

        assertEquals(mockedResponseTodoResource, result);
    }

    @Test
    @DisplayName("Should create todo with success")
    void shouldCreateTodoWithSuccess() {
        var todo = new CreateTodoResource();
        todo.setDescription("description");

        mockedResponseTodoResource.setId(id);
        mockedResponseTodoResource.setStatus(false);
        mockedResponseTodoResource.setDescription("description");
        when(todoService.create(todo)).thenReturn(mockedResponseTodoResource);

        var result = todoService.create(todo);

        assertEquals(mockedResponseTodoResource, result);
        assertEquals(mockedResponseTodoResource.getDescription(), result.getDescription());
    }

    @Test
    @DisplayName("Should update todo with success")
    void shouldUpdateTodoWithSuccess() {
        var todo = new UpdateTodoResource();

        when(todoService.update(todo)).thenReturn(mockedResponseTodoResource);

        var result = todoService.update(todo);

        assertEquals(mockedResponseTodoResource, result);
    }


}
