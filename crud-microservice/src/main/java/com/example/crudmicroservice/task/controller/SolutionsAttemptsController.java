package com.example.crudmicroservice.task.controller;

import com.example.crudmicroservice.task.dto.TaskAttemptsDTO;
import com.example.crudmicroservice.task.model.SolutionsAttempts;
import com.example.crudmicroservice.task.service.SolutionsAttemptsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.summer.taskshareddomain.AttemptDTO;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class SolutionsAttemptsController {

    private final SolutionsAttemptsService solutionsAttemptsService;

    public SolutionsAttemptsController(SolutionsAttemptsService solutionsAttemptsService) {
        this.solutionsAttemptsService = solutionsAttemptsService;
    }

    @PostMapping("/tasks/{id}/attempts")
    public ResponseEntity<AttemptDTO> createSolutionsAttempts(@RequestHeader("loggedInUser") String email, @PathVariable("id") Long taskId, @RequestBody AttemptDTO attemptDTO) {
        AttemptDTO createdAttempts = solutionsAttemptsService.saveSolutionsAttempts(email, taskId, attemptDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAttempts);
    }

    @PutMapping("/attempts/{attemptId}")
    public ResponseEntity<AttemptDTO> updateSolutionsAttempts(@PathVariable Long attemptId,
                                                              @RequestBody AttemptDTO dto) {
        AttemptDTO updatedAttempts = solutionsAttemptsService.
                updateSolutionsAttempts(attemptId, dto);
        if (updatedAttempts != null) {
            return ResponseEntity.ok(updatedAttempts);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/attempts/{attemptId}")
    public ResponseEntity<AttemptDTO> getSolutionsAttemptsById(@PathVariable Long attemptId) {
        AttemptDTO solutionsAttempts = solutionsAttemptsService.getSolutionsAttemptsById(attemptId);
        if (solutionsAttempts != null) {
            System.out.println("yes");
            return ResponseEntity.ok(solutionsAttempts);
        } else {
            System.out.println("no");
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{attemptId}")
    public ResponseEntity<Void> deleteSolutionsAttempts(@PathVariable Long attemptId) {
        solutionsAttemptsService.deleteSolutionsAttempts(attemptId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tasks/{id}/attempts")
    public ResponseEntity<TaskAttemptsDTO> getAttemptsByUserAndTask(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int limit,
            @RequestHeader("loggedInUser") String email, @PathVariable("id") Long taskId) {

        return ResponseEntity.ok(solutionsAttemptsService.getAttempts(email, taskId, page, limit));
    }

}
