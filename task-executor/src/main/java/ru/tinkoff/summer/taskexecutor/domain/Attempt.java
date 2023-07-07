package ru.tinkoff.summer.taskexecutor.domain;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.tinkoff.summer.taskexecutor.domain.task.Task;
import ru.tinkoff.summer.taskexecutor.domain.task.TaskTestCase;

@Getter
@Setter
@AllArgsConstructor
public class Attempt {
    private String code;
    private Language language;
    private Task task;
    private Set<TaskTestCase> customTestCases;
}
