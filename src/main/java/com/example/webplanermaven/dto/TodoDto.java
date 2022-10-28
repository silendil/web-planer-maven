package com.example.webplanermaven.dto;

import com.example.webplanermaven.model.entity.Todo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TodoDto {
    private Long id;
    @NotEmpty
    private String description;
    private String user;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate targetDate;

    public TodoDto(Todo todo) {
        id = todo.getId();
        description = todo.getDescription();
        user = todo.getUser().getUsername();
        targetDate = todo.getTargetDate();
    }
}
