package ru.tinkoff.summer.taskmicroservice.infrastructure.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.summer.taskmicroservice.DTO.SolutionData;
import ru.tinkoff.summer.taskmicroservice.application.usecase.SendSolutionUseCase;
import ru.tinkoff.summer.taskshareddomain.AttemptDTO;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class AttemptController {
    private final SendSolutionUseCase sendSolutionUseCase;

    @PostMapping("/tasks/{id}/attempt")
    public ResponseEntity<AttemptDTO> createAttempt(@RequestHeader("loggedInUser") String email, @PathVariable long id, @RequestBody SolutionData data){
        return new ResponseEntity(sendSolutionUseCase.execute(email,data), HttpStatus.CREATED);
    }


}
