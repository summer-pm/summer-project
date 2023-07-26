package ru.tinkoff.summer.taskshareddomain.task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.tinkoff.summer.taskshareddomain.task.TaskParams;
import ru.tinkoff.summer.taskshareddomain.task.TaskTestCase;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TaskForAttemptDTO {
    private Long id;
     private String methodName;
    private TaskParams params;
    private Set<TaskTestCase> taskTestCases;
    private long timeLimitMs;
    private long volumeLimitMs;
}
