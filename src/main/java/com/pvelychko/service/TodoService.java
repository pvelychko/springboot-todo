package com.pvelychko.service;

import java.util.List;

import com.pvelychko.model.Todo;
import com.pvelychko.repository.Todos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    @Autowired
    private Todos todos;

    public Todo findById(Long id) {
        return todos.findOne(id);
    }

    public List<Todo> findAll() {
        return todos.findAll();
    }

    public Todo save(Todo todo) {
        return todos.save(todo);
    }

    public void delete(Long id) {
        todos.delete(id);
    }
}
