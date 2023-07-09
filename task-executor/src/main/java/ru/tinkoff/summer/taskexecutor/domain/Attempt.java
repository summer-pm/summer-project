package ru.tinkoff.summer.taskexecutor.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.tinkoff.summer.taskexecutor.domain.task.Task;

import ru.tinkoff.summer.taskshareddomain.Language;
import ru.tinkoff.summer.taskshareddomain.task.TaskTestCase;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class Attempt {
    private String code;
    private Language language;
    private Task task;
    private Set<TaskTestCase> customTestCases;
}
