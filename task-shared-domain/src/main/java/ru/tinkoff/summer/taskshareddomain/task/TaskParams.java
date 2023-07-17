package ru.tinkoff.summer.taskshareddomain.task;

import lombok.*;
import ru.tinkoff.summer.taskshareddomain.Type;


import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class TaskParams implements Serializable {
    private List<Type> inputTypes;
    private Type outputType;

    public TaskParams(List<Type> inputTypes, Type outputType) {
        this.inputTypes = inputTypes;
        this.outputType = outputType;
    }
}
