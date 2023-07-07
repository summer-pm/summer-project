package ru.tinkoff.summer.taskexecutor.domain.task;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
