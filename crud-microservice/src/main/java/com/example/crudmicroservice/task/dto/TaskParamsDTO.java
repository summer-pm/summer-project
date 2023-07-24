package com.example.crudmicroservice.task.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.tinkoff.summer.taskshareddomain.Type;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskParamsDTO {
    @NotNull(message = "inputTypes не должен быть пустым")
    @NotEmpty(message = "inputTypes не должен быть пустым")
    private List<Type> inputTypes;
    @NotNull(message = "outputType не должен быть пустым")
    private Type outputType;

}
