package com.example.crudmicroservice.task.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.tinkoff.summer.taskshareddomain.Language;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskLangsDTO {
    private Language language;
    @NotNull(message = "solutionTemplate не должен быть пустым")
    @NotBlank(message = "solutionTemplate не должен быть пустым")
    private String solutionTemplate;
}
