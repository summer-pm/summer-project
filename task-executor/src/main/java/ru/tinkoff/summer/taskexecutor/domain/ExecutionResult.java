package ru.tinkoff.summer.taskexecutor.domain;

import java.util.List;
import lombok.Getter;
import lombok.ToString;
import ru.tinkoff.summer.taskexecutor.domain.task.TaskTestCase;

@Getter
@ToString
public class ExecutionResult {
    boolean success;
    double executionTimeNs;
    String actualResult;
    double memoryUsageMb;
    TaskTestCase testCase;

    public ExecutionResult(List<String> testOutput, TaskTestCase testCase) {
        this.actualResult= testOutput.get(testOutput.size()-3);
        success = testCase.getOutputValues().replace(" ","")
                .equals(actualResult.replace(" ",""));
        this.executionTimeNs = Double.parseDouble(testOutput.get(testOutput.size()-2));
        this.memoryUsageMb = Double.parseDouble(testOutput.get(testOutput.size()-1));
        this.testCase = testCase;
    }
}
