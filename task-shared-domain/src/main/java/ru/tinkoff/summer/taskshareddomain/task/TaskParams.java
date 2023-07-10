package ru.tinkoff.summer.taskshareddomain.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.tinkoff.summer.taskshareddomain.Type;


import java.util.List;

@Data
public class TaskParams {
    private List<Type> inputTypes;
    private Type outputType;

    public TaskParams(List<Type> inputTypes, Type outputType) {
        this.inputTypes = inputTypes;
        this.outputType = outputType;
    }
}
