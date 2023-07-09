package ru.tinkoff.summer.taskshareddomain.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class TaskTestCase {
    private List<String> inputValues;
    private String outputValues;
}
