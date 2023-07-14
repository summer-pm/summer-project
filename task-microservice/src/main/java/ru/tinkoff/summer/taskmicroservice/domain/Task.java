package ru.tinkoff.summer.taskmicroservice.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.tinkoff.summer.taskshareddomain.task.TaskParams;
import ru.tinkoff.summer.taskshareddomain.task.TaskTestCase;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class Task {
    private long id;
    private String title;
    private String description;
     @JsonIgnore //TODO: DTO
    private String methodName;
    @JsonIgnore
    private TaskParams params;
    private Set<TaskTestCase> taskTestCases;
    private long timeLimitMs;
    private long volumeLimitMb;
}
