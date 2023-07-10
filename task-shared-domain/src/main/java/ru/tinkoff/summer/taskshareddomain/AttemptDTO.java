package ru.tinkoff.summer.taskshareddomain;

import lombok.Data;
import ru.tinkoff.summer.taskshareddomain.task.TaskParams;
import ru.tinkoff.summer.taskshareddomain.task.TaskTestCase;

import java.io.Serializable;
import java.util.Set;

@Data

public class AttemptDTO implements Serializable {
     private String id;
     private String code;
    private Language language;
     private String methodName;
    private TaskParams params;
    private Set<TaskTestCase> taskTestCases;
    private long timeLimitMs;

}
