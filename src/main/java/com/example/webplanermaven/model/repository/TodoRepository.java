package com.example.webplanermaven.model.repository;

import com.example.webplanermaven.dto.TodoDto;
import com.example.webplanermaven.model.entity.Todo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TodoRepository extends CrudRepository<Todo, Long> {
    @Query("select new com.example.webplanermaven.dto.TodoDto(t) " +
            "from Todo t where t.user.id = :userId")
    List<TodoDto> findByUserId(@Param("userId") Long userId);
}
