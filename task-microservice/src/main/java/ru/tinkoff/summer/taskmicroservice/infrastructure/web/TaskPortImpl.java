package ru.tinkoff.summer.taskmicroservice.infrastructure.web;

import org.springframework.stereotype.Repository;
import ru.tinkoff.summer.taskmicroservice.application.port.data.TaskPort;
import ru.tinkoff.summer.taskmicroservice.domain.Task;
import ru.tinkoff.summer.taskshareddomain.Type;
import ru.tinkoff.summer.taskshareddomain.task.TaskParams;
import ru.tinkoff.summer.taskshareddomain.task.TaskTestCase;

import java.util.List;
import java.util.Set;

@Repository
public class TaskPortImpl implements TaskPort {
    @Override
    public Task getTask(long taskId) {
        var task = new Task();
        task.setId(taskId);
        task.setTitle("Calc sum");
        task.setMethodName("add");
        task.setDescription("");
        task.setParams(new TaskParams(
                List.of(Type.INTEGER, Type.INTEGER)
                ,Type.INTEGER
        ));
        task.setTaskTestCases(Set.of(
                new TaskTestCase(
                        List.of("2","4"),"6"
                )
        ));
        return task;
    }
}
