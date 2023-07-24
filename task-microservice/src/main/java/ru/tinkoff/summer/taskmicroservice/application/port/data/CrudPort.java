package ru.tinkoff.summer.taskmicroservice.application.port.data;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.*;
import ru.tinkoff.summer.taskshareddomain.AttemptDTO;
import ru.tinkoff.summer.taskshareddomain.task.dto.TaskForAttemptDTO;


@FeignClient(name= "crud-microservice")

public interface CrudPort {

    @PostMapping("/api/v1/tasks/{taskId}/attempts")
    AttemptDTO save(@RequestHeader("loggedInUser") String email, @PathVariable("taskId") long taskId , @RequestBody  AttemptDTO attemptDTO);
    @PutMapping("/api/v1/attempts/{id}")
    AttemptDTO update(@PathVariable("id") long id, @RequestBody AttemptDTO attemptDTO);
    @GetMapping("/api/v1/attempts/{id}")
    AttemptDTO getById(@PathVariable("id") long id);
    @GetMapping("/api/v1/tasks/{taskId}/details")
    TaskForAttemptDTO getTask(@PathVariable("taskId") long taskId);
}
