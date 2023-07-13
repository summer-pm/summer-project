package ru.tinkoff.summer.taskmicroservice.application.usecase;

import ru.tinkoff.summer.taskmicroservice.DTO.SolutionData;
import ru.tinkoff.summer.taskmicroservice.domain.Attempt;

public interface SendSolutionUseCase {
     Attempt execute(SolutionData solutionData);
}
