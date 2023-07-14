package ru.tinkoff.summer.taskshareddomain;

import lombok.Getter;
import lombok.ToString;
import ru.tinkoff.summer.taskshareddomain.task.TaskTestCase;


import java.util.List;

@Getter
@ToString
public class ExecutionResult {
<<<<<<< HEAD
=======

>>>>>>> abe64b06c86ae596b1dcb093b0d2d008782c59ce
    boolean success;
    double executionTimeNs;
    String actualResult;
    double memoryUsageMb;
    TaskTestCase testCase;

    public ExecutionResult(List<String> testOutput, TaskTestCase testCase) {
<<<<<<< HEAD
=======

>>>>>>> abe64b06c86ae596b1dcb093b0d2d008782c59ce
        this.actualResult= testOutput.get(testOutput.size()-3);
        success = testCase.getOutputValues().replace(" ","")
                .equals(actualResult.replace(" ",""));
        this.executionTimeNs = Double.parseDouble(testOutput.get(testOutput.size()-2));
        this.memoryUsageMb = Double.parseDouble(testOutput.get(testOutput.size()-1));
        this.testCase = testCase;
    }
}
