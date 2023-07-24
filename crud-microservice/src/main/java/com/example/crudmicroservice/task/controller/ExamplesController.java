package com.example.crudmicroservice.task.controller;

import com.example.crudmicroservice.task.model.Examples;
import com.example.crudmicroservice.task.service.ExamplesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/examples")
public class ExamplesController {

    private final ExamplesService examplesService;

    public ExamplesController(ExamplesService examplesService) {
        this.examplesService = examplesService;
    }

    @PostMapping
    public ResponseEntity<Examples> createExample(@RequestBody Examples example) {
        Examples createdExample = examplesService.saveExample(example);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdExample);
    }

    @PatchMapping("/{exampleId}")
    public ResponseEntity<Examples> updateExample(@PathVariable Long exampleId, @RequestBody Examples example) {
        Examples updatedExample = examplesService.updateExample(exampleId, example);
        if (updatedExample != null) {
            return ResponseEntity.ok(updatedExample);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{exampleId}")
    public ResponseEntity<Examples> getExampleById(@PathVariable Long exampleId) {
        Examples example = examplesService.getExampleById(exampleId);
        if (example != null) {
            return ResponseEntity.ok(example);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{exampleId}")
    public ResponseEntity<Void> deleteExample(@PathVariable Long exampleId) {
        examplesService.deleteExample(exampleId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Examples>> getAllExamples() {
        List<Examples> examples = examplesService.getAllExamples();
        return ResponseEntity.ok(examples);
    }
}
