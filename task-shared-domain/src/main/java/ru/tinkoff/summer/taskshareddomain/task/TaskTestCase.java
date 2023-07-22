package ru.tinkoff.summer.taskshareddomain.task;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskTestCase implements Serializable {
    private Long id;
    private List<String> inputValues;
    private String outputValues;


}
