package com.example.crudmicroservice.task.controller;


import com.example.crudmicroservice.task.dto.CreateTaskDTO;
import com.example.crudmicroservice.task.dto.TestDTO;
import com.example.crudmicroservice.task.model.Level;
import com.example.crudmicroservice.task.model.Task;
import com.example.crudmicroservice.task.service.TaskCreateService;
import com.example.crudmicroservice.task.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.summer.taskshareddomain.task.dto.TaskDetailsFrontendDTO;
import ru.tinkoff.summer.taskshareddomain.task.dto.TaskForAttemptDTO;
import ru.tinkoff.summer.taskshareddomain.task.dto.TaskFrontendDTO;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final TaskCreateService taskCreateService;


    @PostMapping("/{id}/tests")
    public ResponseEntity<Task> addTests(@PathVariable long id, @Valid @RequestBody List<TestDTO> dto){
        taskCreateService.addTests(id, dto);
        return ResponseEntity.created(URI.create("")).build();
    }
    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody CreateTaskDTO dto){
        taskCreateService.create(dto);
        return ResponseEntity.created(URI.create("")).build();
    }
    @GetMapping("/{id}/tests")
    public ResponseEntity<List<TestDTO>> getTests(@PathVariable long id){
        return ResponseEntity.ok(taskCreateService.getTests(id));
    }

     @DeleteMapping("/{id}/tests")
    public ResponseEntity<?> deleteAllTests(@PathVariable long id){
        taskCreateService.deleteAllTests(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}/tests/{testId}")
    public ResponseEntity<?> deleteTestById(@PathVariable long testId){
        taskCreateService.deleteTest(testId);
        return ResponseEntity.noContent().build();
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
    @PostMapping("/{id}/publish")
    public void publishTask(@PathVariable Long id) {
        taskService.setPublish(id, true);
    }
     @PostMapping("/{id}/un-publish")
    public void unPublishTask(@PathVariable Long id) {
        taskService.setPublish(id, false);
    }

    @GetMapping
    public Page<TaskFrontendDTO> getTasks(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int limit,
                                          @RequestParam(defaultValue = "taskId") String sortBy,
                                          @RequestParam(defaultValue = "true") boolean publish,
                                          @RequestParam(name="title", required = false) String title,
                                          @RequestParam(name="level", required = false) Level level) {
        return taskService.getTasks(page, limit,title,level, sortBy, publish);
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
