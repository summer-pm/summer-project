package ru.tinkoff.summer.taskmicroservice.application.usecase;

import ru.tinkoff.summer.taskmicroservice.DTO.SolutionData;

public interface SendSolutionUseCase {
     long execute(SolutionData solutionData);
}
