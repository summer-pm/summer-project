package ru.tinkoff.summer.taskservice.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TaskParams {
    private List<Type> inputTypes;
    private Type outputType;
}
