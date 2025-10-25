package com.example.TinyTask.repository;

import com.example.TinyTask.model.Todo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TodoRepository {
    private final Map<Long, Todo> todos = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    public List<Todo> findAll() {
        return new ArrayList<>(todos.values());
    }

    public Todo save(String title) {
        long newId = idCounter.incrementAndGet();
        Todo newTodo = new Todo(newId, title);
        todos.put(newId, newTodo);
        return newTodo;
    }

    public Optional<Todo> findById(long id) {
        return Optional.ofNullable(todos.get(id));
    }

    public boolean deleteById(long id) {
        return todos.remove(id) != null;
    }
}
