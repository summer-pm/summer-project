package ru.tinkoff.summer.taskmicroservice.application.usecase;

import ru.tinkoff.summer.taskshareddomain.TotalExecutionResult;

public interface ApplyResultUseCase {
    void apply(TotalExecutionResult result);
}
