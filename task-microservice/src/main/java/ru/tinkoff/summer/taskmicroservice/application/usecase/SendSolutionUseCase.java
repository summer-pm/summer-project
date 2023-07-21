package ru.tinkoff.summer.taskmicroservice.application.usecase;

import ru.tinkoff.summer.taskmicroservice.DTO.SolutionData;
import ru.tinkoff.summer.taskshareddomain.AttemptDTO;

public interface SendSolutionUseCase {
     AttemptDTO execute(String email, SolutionData solutionData);
}
