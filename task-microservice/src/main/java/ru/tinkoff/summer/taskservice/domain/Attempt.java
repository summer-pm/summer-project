package ru.tinkoff.summer.taskservice.domain;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Attempt {
    private String code;
    private Language language;
    private Task task;
    private Set<TaskTestCase> customTestCases;
}
