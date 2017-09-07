package com.pvelychko.repository;

import com.pvelychko.model.Todo;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface Todos extends Repository<Todo, Long> {

    Todo findOne(Long id);

    List<Todo> findAll();

    Todo save(Todo todo);

    void delete(Long id);
}
