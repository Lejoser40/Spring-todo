package com.example.todo.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todo.dto.TodoInputDTO;
import com.example.todo.dto.TodoOutputDTO;
import com.example.todo.model.Todo;
import com.example.todo.repository.TodoRepository;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public List<TodoOutputDTO> getAllTodos() {
        return todoRepository.findAll().stream()
                .map(todo -> new TodoOutputDTO(todo.getId(), todo.getTitle(), todo.getDescription(),
                        todo.isCompleted()))
                .collect(Collectors.toList());
    }

    public TodoOutputDTO getTodoById(Long id) {
        Todo todo = todoRepository.findById(id).orElse(null);
        if (todo != null) {
            return new TodoOutputDTO(todo.getId(), todo.getTitle(), todo.getDescription(), todo.isCompleted());
        }
        return null;
    }

    public TodoOutputDTO createTodo(TodoInputDTO input) {
        Todo todo = new Todo(input.getTitle(), input.getDescription(), input.isCompleted());
        Todo saved = todoRepository.save(todo);
        return new TodoOutputDTO(saved.getId(), saved.getTitle(), saved.getDescription(), saved.isCompleted());
    }

    public TodoOutputDTO updateTodo(Long id, TodoInputDTO input) {
        Todo todo = todoRepository.findById(id).orElse(null);
        if (todo != null) {
            todo.setTitle(input.getTitle());
            todo.setDescription(input.getDescription());
            todo.setCompleted(input.isCompleted());
            Todo updated = todoRepository.save(todo);
            return new TodoOutputDTO(updated.getId(), updated.getTitle(),
                    updated.getDescription(), updated.isCompleted());
        }
        return null;
    }

    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }

}