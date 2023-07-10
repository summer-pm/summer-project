package ru.tinkoff.summer.taskshareddomain.task;

import lombok.*;

import java.util.List;

@Data
public class TaskTestCase {
    private List<String> inputValues;
    private String outputValues;

    public TaskTestCase(List<String> inputValues, String outputValues) {
        this.inputValues = inputValues;
        this.outputValues = outputValues;
    }
}
