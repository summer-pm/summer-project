package com.example.crudmicroservice.task.controller;

import com.example.crudmicroservice.task.model.SolutionsAttempts;
import com.example.crudmicroservice.task.service.SolutionsAttemptsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/solutions-attempts")
public class SolutionsAttemptsController {

    private final SolutionsAttemptsService solutionsAttemptsService;

    public SolutionsAttemptsController(SolutionsAttemptsService solutionsAttemptsService) {
        this.solutionsAttemptsService = solutionsAttemptsService;
    }

    @PostMapping
    public ResponseEntity<SolutionsAttempts> createSolutionsAttempts(@RequestBody SolutionsAttempts solutionsAttempts) {
        SolutionsAttempts createdAttempts = solutionsAttemptsService.saveSolutionsAttempts(solutionsAttempts);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAttempts);
    }

    @PatchMapping("/{attemptId}")
    public ResponseEntity<SolutionsAttempts> updateSolutionsAttempts(@PathVariable Long attemptId,
                                                                     @RequestBody SolutionsAttempts solutionsAttempts) {
        SolutionsAttempts updatedAttempts = solutionsAttemptsService.
                updateSolutionsAttempts(attemptId, solutionsAttempts);
        if (updatedAttempts != null) {
            return ResponseEntity.ok(updatedAttempts);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{attemptId}")
    public ResponseEntity<SolutionsAttempts> getSolutionsAttemptsById(@PathVariable Long attemptId) {
        SolutionsAttempts solutionsAttempts = solutionsAttemptsService.getSolutionsAttemptsById(attemptId);
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

    @GetMapping
    public ResponseEntity<List<SolutionsAttempts>> getAllSolutionsAttempts() {
        List<SolutionsAttempts> solutionsAttempts = solutionsAttemptsService.getAllSolutionsAttempts();
        return ResponseEntity.ok(solutionsAttempts);
    }
}
