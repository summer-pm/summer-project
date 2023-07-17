package ru.tinkoff.summer.taskshareddomain.task;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class TaskTestCase implements Serializable {
    private List<String> inputValues;
    private String outputValues;

    public TaskTestCase(List<String> inputValues, String outputValues) {
        this.inputValues = inputValues;
        this.outputValues = outputValues;
    }
}
