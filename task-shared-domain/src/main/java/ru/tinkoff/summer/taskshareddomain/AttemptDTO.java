package ru.tinkoff.summer.taskshareddomain;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.tinkoff.summer.taskshareddomain.task.TaskParams;
import ru.tinkoff.summer.taskshareddomain.task.TaskTestCase;

import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
public class AttemptDTO implements Serializable {
     private Long id;
     private String code;
    private Language language;
     private String methodName;
    private TaskParams params;
    private Set<TaskTestCase> taskTestCases;
    private long timeLimitMs;

}
