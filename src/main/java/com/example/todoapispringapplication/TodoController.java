package com.example.todoapispringapplication;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {
    private static List<Todo> todoList;

    public TodoController() {
        todoList = new ArrayList<>();
        todoList.add(new Todo(1, false, "Todo 1", 1));
        todoList.add(new Todo(2, true, "Todo 2", 2));
    }

    @GetMapping
    public ResponseEntity<List<Todo>> getTodos() {
        return ResponseEntity.ok().body(todoList);
    }

    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody Todo newTodo) {
        todoList.add(newTodo);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTodo);
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<Todo> getTodoById(@PathVariable int todoId) {
        List<Todo> filteredTodos = todoList.stream().filter(
                (t) -> t.getId() == todoId
        ).toList();
        if (!filteredTodos.isEmpty()) return ResponseEntity.ok(filteredTodos.get(0));
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<Todo> deleteTodoById(@PathVariable int todoId) {
        Todo todoToDelete = null;
        List<Todo> newTodoList = new ArrayList<>();
        for (Todo todo : todoList) {
            if (todo.getId() == todoId) todoToDelete = todo;
            else newTodoList.add(todo);
        }
        todoList = newTodoList;
        if (todoToDelete == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(todoToDelete);
    }

    @PutMapping("/{todoId}")
    public ResponseEntity<Todo> updateTodoById(@PathVariable int todoId, @RequestBody Todo newTodo) {
        List<Todo> newTodoList = new ArrayList<>();
        boolean isUpdated = false;
        for (Todo todo : todoList) {
            if (todo.getId() == todoId) {
                newTodoList.add(newTodo);
                isUpdated = true;
            } else newTodoList.add(todo);
        }
        todoList = newTodoList;
        if (isUpdated) return ResponseEntity.ok(newTodo);
        return ResponseEntity.notFound().build();
    }
}
