package com.example.TinyTask.controller;

import com.example.TinyTask.model.Todo;
import com.example.TinyTask.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<Todo> getAllTodos() {
        return todoService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> createTodo(@RequestBody Map<String, String> body) {
        try {
            String title = body.get("title");
            Todo newTodo = todoService.create(title);
            return new ResponseEntity<>(newTodo, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}/toggle")
    public ResponseEntity<?> toggleTodo(@PathVariable long id) {
        Optional<Todo> optionalTodo = todoService.toggle(id);
        if (optionalTodo.isPresent()) {
            return new ResponseEntity<>(optionalTodo.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Map.of("error", "Not found"), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable long id) {
        if (todoService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(Map.of("error", "Not found"), HttpStatus.NOT_FOUND);
        }
    }
}
