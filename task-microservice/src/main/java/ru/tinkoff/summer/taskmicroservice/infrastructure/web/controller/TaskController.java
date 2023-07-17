package ru.tinkoff.summer.taskmicroservice.infrastructure.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.summer.taskmicroservice.application.port.data.TaskPort;
import ru.tinkoff.summer.taskmicroservice.domain.Task;

@RequiredArgsConstructor
@RestController
public class TaskController {

    //TODO:QueryUseCase
    public final TaskPort taskPort;

    @GetMapping("/task/{id}")
    public Task getTask(@PathVariable long id){
        return taskPort.getTask(id);
    }
}
