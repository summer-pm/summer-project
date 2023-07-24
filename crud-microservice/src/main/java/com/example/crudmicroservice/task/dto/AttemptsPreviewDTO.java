package com.example.crudmicroservice.task.dto;

import com.example.crudmicroservice.task.model.SolutionsAttempts;
import com.example.crudmicroservice.task.model.Test;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.tinkoff.summer.taskshareddomain.AttemptDTO;
import ru.tinkoff.summer.taskshareddomain.ExecutionStatus;
import ru.tinkoff.summer.taskshareddomain.Language;
import ru.tinkoff.summer.taskshareddomain.task.TaskTestCase;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttemptsPreviewDTO {
    private long id;
    private boolean status;
    private LocalDateTime date;
    private Language language;
    private String code;
    private Double timeResult;
    private Double volumeResult;
    private TaskTestCase testCase;

    public static AttemptsPreviewDTO fromEntity(SolutionsAttempts attempts){
        return  AttemptsPreviewDTO.builder()
                .id(attempts.getAttemptId())
                .code(attempts.getCode())
                .status(attempts.getStatus().equals(ExecutionStatus.SUCCESS.toString()))
                .language(Language.valueOf(attempts.getLanguage().getLanguage()))
                .date(attempts.getCreationDate())
                .testCase(getTestCaseFromEntity(attempts.getFailedTest()))
                .timeResult(attempts.getExecutionTime())
                .volumeResult(attempts.getSolutionsVolume())
                .build();

    }

    private static TaskTestCase getTestCaseFromEntity(Test failedTest) {
        return new TaskTestCase(failedTest.getTestId(), failedTest.getInputParameters(),failedTest.getOutputParameters());
    }
}
