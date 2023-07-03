package ru.tinkoff.summer.taskservice.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class TaskTestCase {
    private List<String> inputValues;
    private String outputValues;
}
