package ru.tinkoff.summer.taskmicroservice.infrastructure.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.summer.taskmicroservice.DTO.SolutionData;
import ru.tinkoff.summer.taskmicroservice.application.port.data.AttemptPort;
import ru.tinkoff.summer.taskmicroservice.application.usecase.SendSolutionUseCase;
import ru.tinkoff.summer.taskmicroservice.application.usecase.impl.SendSolutionUseCaseImpl;
import ru.tinkoff.summer.taskmicroservice.domain.Attempt;

@RequiredArgsConstructor
@RestController
public class AttemptController {
    private final SendSolutionUseCase sendSolutionUseCase;
    //TODO:Query
    private final AttemptPort attemptPort;
    @PostMapping("/task/{id}/attempt")
    public ResponseEntity<Long> createAttempt(@PathVariable long id, @RequestBody SolutionData data){
        return new ResponseEntity(sendSolutionUseCase.execute(data), HttpStatus.CREATED);
    }

    @GetMapping("/task/{taskId}/attempt/{attemptId}")
    public ResponseEntity<Attempt> getAttempt(@PathVariable long taskId, @PathVariable long attemptId ){
        return ResponseEntity.ok().body(attemptPort.getById(attemptId));
    }
}
