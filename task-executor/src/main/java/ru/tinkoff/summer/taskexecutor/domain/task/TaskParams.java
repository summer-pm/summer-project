package ru.tinkoff.summer.taskexecutor.domain.task;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.tinkoff.summer.taskexecutor.domain.Type;

@Getter
@Setter
@AllArgsConstructor
public class TaskParams {
    private List<Type> inputTypes;
    private Type outputType;
}
