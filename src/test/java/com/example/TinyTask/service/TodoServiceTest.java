package com.example.TinyTask.service;

import com.example.TinyTask.model.Todo;
import com.example.TinyTask.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoService todoService;

    private Todo todo;

    @BeforeEach
    void setUp() {
        todo = new Todo(1L, "Learn testing");
    }

    @Test
    void create_should_save_todo_when_title_is_valid() {
        when(todoRepository.save("Learn testing")).thenReturn(todo);

        Todo result = todoService.create("Learn testing");

        assertNotNull(result);
        assertEquals("Learn testing", result.getTitle());
        verify(todoRepository).save("Learn testing");
    }

    @Test
    void create_should_throw_exception_when_title_is_invalid() {
        assertThrows(IllegalArgumentException.class, () -> todoService.create("a"));
        assertThrows(IllegalArgumentException.class, () -> todoService.create(null));
        assertThrows(IllegalArgumentException.class, () -> todoService.create("  "));
    }

    @Test
    void toggle_should_change_done_status_when_todo_exists() {
        when(todoRepository.findById(1L)).thenReturn(Optional.of(todo));

        Optional<Todo> result = todoService.toggle(1L);

        assertTrue(result.isPresent());
        assertTrue(result.get().isDone());
    }

    @Test
    void toggle_should_return_empty_when_todo_does_not_exist() {
        when(todoRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Todo> result = todoService.toggle(1L);

        assertFalse(result.isPresent());
    }

    @Test
    void delete_should_return_true_when_todo_is_deleted() {
        when(todoRepository.deleteById(1L)).thenReturn(true);

        boolean result = todoService.delete(1L);

        assertTrue(result);
    }

    @Test
    void delete_should_return_false_when_todo_does_not_exist() {
        when(todoRepository.deleteById(1L)).thenReturn(false);

        boolean result = todoService.delete(1L);

        assertFalse(result);
    }
}
