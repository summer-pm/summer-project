package ru.tinkoff.summer.taskshareddomain.task;

<<<<<<< HEAD
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
=======
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
>>>>>>> abe64b06c86ae596b1dcb093b0d2d008782c59ce
}
