package ru.tinkoff.summer.taskshareddomain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.tinkoff.summer.taskshareddomain.task.TaskTestCase;

import java.util.List;

@Data
@NoArgsConstructor
@ToString
@Builder
public class TotalExecutionResult {
    long attemptId;
    ExecutionStatus status;
    double executionTimeNs;
    String actualResult;
    double memoryUsageMb;
    TaskTestCase testCase;
    String errorMessage;

    public TotalExecutionResult(AttemptDTO attempt, List<ExecutionResult> results) {
        this.attemptId = attempt.getId();
        this.status = results.get(results.size() - 1).success ? ExecutionStatus.SUCCESS : ExecutionStatus.FAILURE;
        //При ошибке непрошедший тест на последнем ExecutionResult
        this.testCase = results.get(results.size() - 1).getTestCase();
        this.executionTimeNs = results.stream()
                .mapToDouble(ExecutionResult::getExecutionTimeNs)
                .max()
                .orElse(Double.MIN_VALUE);
        this.memoryUsageMb = results.stream()
                .mapToDouble(ExecutionResult::getMemoryUsageMb)
                .max()
                .orElse(Double.MIN_VALUE);
        this.actualResult = results.get(results.size() - 1).getActualResult();
    }

    public TotalExecutionResult(AttemptDTO attempt, String errorMessage) {
         this.attemptId = attempt.getId();
        this.errorMessage = errorMessage;
        this.status = ExecutionStatus.ERROR;
    }

    public TotalExecutionResult(long attemptId, ExecutionStatus status, double executionTimeNs, String actualResult, double memoryUsageMb, TaskTestCase testCase, String errorMessage) {
        this.attemptId = attemptId;
        this.status = status;
        this.executionTimeNs = executionTimeNs;
        this.actualResult = actualResult;
        this.memoryUsageMb = memoryUsageMb;
        this.testCase = testCase;
        this.errorMessage = errorMessage;
    }
}
