package com.example.crudmicroservice.task.controller;


import com.example.crudmicroservice.task.model.Level;
import com.example.crudmicroservice.task.model.Task;
import com.example.crudmicroservice.task.service.TaskService;
import org.springframework.data.domain.Page;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.summer.taskshareddomain.task.dto.TaskDetailsFrontendDTO;
import ru.tinkoff.summer.taskshareddomain.task.dto.TaskForAttemptDTO;
import ru.tinkoff.summer.taskshareddomain.task.dto.TaskFrontendDTO;


@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }



    @GetMapping("/{id}")
    public ResponseEntity<TaskDetailsFrontendDTO> getTaskById(@PathVariable Long id) {
        TaskDetailsFrontendDTO task = taskService.getTaskById(id);
        if (task != null) {
            return ResponseEntity.ok(task);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
     @GetMapping("/{id}/details")
    public ResponseEntity<TaskForAttemptDTO> getTaskDetailsById(@PathVariable Long id) {
        TaskForAttemptDTO task = taskService.getTaskDetailsById(id);
        if (task != null) {
            return ResponseEntity.ok(task);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public Page<TaskFrontendDTO> getTasks(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int limit,
                                          @RequestParam(defaultValue = "taskId") String sortBy,
                                          @RequestParam(name="title", required = false) String title,
                                          @RequestParam(name="level", required = false) Level level) {
        return taskService.getTasks(page, limit,title,level, sortBy);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        Task updatedTask = taskService.updateTask(id, task);
        if (updatedTask != null) {
            return ResponseEntity.ok(updatedTask);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
