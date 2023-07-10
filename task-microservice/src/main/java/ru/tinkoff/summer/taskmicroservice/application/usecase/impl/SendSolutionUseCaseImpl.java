package ru.tinkoff.summer.taskmicroservice.application.usecase.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.summer.taskmicroservice.DTO.SolutionData;
import ru.tinkoff.summer.taskmicroservice.application.port.data.AttemptPort;
import ru.tinkoff.summer.taskmicroservice.application.port.data.TaskPort;
import ru.tinkoff.summer.taskmicroservice.application.port.messaging.ExecutionPort;
import ru.tinkoff.summer.taskmicroservice.application.usecase.SendSolutionUseCase;
import ru.tinkoff.summer.taskmicroservice.domain.Attempt;
import ru.tinkoff.summer.taskshareddomain.AttemptDTO;

@Service
@RequiredArgsConstructor
public class SendSolutionUseCaseImpl implements SendSolutionUseCase {

    private final TaskPort taskPort;
    private final ExecutionPort executionPort;
    private final AttemptPort attemptPort;

    @Override
    public long execute(SolutionData solutionData) {
        var task  = taskPort.getTask(solutionData.getTaskId());
        var attempt = Attempt.of(solutionData.getCode(), solutionData.getLanguage(), task);
        attempt = attemptPort.save(attempt);
        var dto = new AttemptDTO();
        dto.setLanguage(solutionData.getLanguage());
        dto.setCode(solutionData.getCode());
        dto.setId(attempt.getId());
        dto.setParams(task.getParams());
        dto.setTaskTestCases(task.getTaskTestCases());
        dto.setMethodName(task.getMethodName());
        executionPort.publishForExecute(dto);
        return attempt.getId();
    }
}
