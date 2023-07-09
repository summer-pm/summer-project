package ru.tinkoff.summer.taskexecutor.domain.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.tinkoff.summer.taskshareddomain.task.TaskParams;
import ru.tinkoff.summer.taskshareddomain.task.TaskTestCase;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private String title;
    private String methodName;
    private TaskParams params;
    private Set<TaskTestCase> taskTestCases;
    private long timeLimitMs;
}
