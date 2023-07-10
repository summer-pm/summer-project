package ru.tinkoff.summer.taskmicroservice.application.usecase.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.summer.taskmicroservice.DTO.SolutionData;
import ru.tinkoff.summer.taskmicroservice.application.port.jpa.TaskPort;
import ru.tinkoff.summer.taskmicroservice.application.port.messaging.ExecutionPort;
import ru.tinkoff.summer.taskmicroservice.application.usecase.SendSolutionUseCase;
import ru.tinkoff.summer.taskmicroservice.domain.Attempt;
@Service
@RequiredArgsConstructor
public class SendSolutionUseCaseImpl implements SendSolutionUseCase {

    private final TaskPort taskPort;
    private final ExecutionPort executionPort;

    @Override
    public String execute(SolutionData solutionData) {
        var task  = taskPort.getTask(solutionData.getTaskId());
        var attempt = Attempt.of(solutionData.getCode(), solutionData.getLanguage(), task);
        executionPort.publishForExecute(attempt);
        return attempt.getId();
    }
}
