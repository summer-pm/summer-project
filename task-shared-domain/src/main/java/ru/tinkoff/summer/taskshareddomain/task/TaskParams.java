package ru.tinkoff.summer.taskshareddomain.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.tinkoff.summer.taskshareddomain.Type;


import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TaskParams {
    private List<Type> inputTypes;
    private Type outputType;
}
