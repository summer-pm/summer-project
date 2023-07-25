package ru.tinkoff.summer.taskmicroservice.application.usecase.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.tinkoff.summer.taskmicroservice.application.port.data.CrudPort;
import ru.tinkoff.summer.taskmicroservice.application.usecase.ApplyResultUseCase;
import ru.tinkoff.summer.taskshareddomain.ExecutionStatus;
import ru.tinkoff.summer.taskshareddomain.TotalExecutionResult;

@Service
@RequiredArgsConstructor
public class ApplyResultUseCaseImpl implements ApplyResultUseCase {
    Logger log = LoggerFactory.getLogger(ApplyResultUseCaseImpl.class);
    private final CrudPort port;
    @Override
    public void apply(TotalExecutionResult result) {
        log.info("Get result from executor {}", result);
        var attempt = port.getById(result.getAttemptId());
         log.info("Get attempt from crud {}", attempt);
        attempt.setStatus(result.getStatus());
        attempt.setErrorMessage(result.getErrorMessage());

        if(attempt.getStatus() != ExecutionStatus.ERROR){
            attempt.setExecutionTimeNs(result.getExecutionTimeNs());
            attempt.setMemoryUsageMb(result.getMemoryUsageMb());
        } if(attempt.getStatus() == ExecutionStatus.FAILURE){
            attempt.setActualResult(result.getActualResult());
            attempt.setTestCase(result.getTestCase());
        }
        log.info("Send attempt to crud {}", attempt);
        port.update(attempt.getId(),attempt);
    }
}
