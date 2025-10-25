package com.example.TinyTask.service;

import com.example.TinyTask.model.Todo;
import com.example.TinyTask.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    public Todo create(String title) {
        if (title == null || title.trim().length() < 3) {
            throw new IllegalArgumentException("Title is required and must be at least 3 characters long");
        }
        return todoRepository.save(title);
    }

    public Optional<Todo> toggle(long id) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        if (optionalTodo.isPresent()) {
            Todo todo = optionalTodo.get();
            todo.setDone(!todo.isDone());
        }
        return optionalTodo;
    }

    public boolean delete(long id) {
        return todoRepository.deleteById(id);
    }
}
