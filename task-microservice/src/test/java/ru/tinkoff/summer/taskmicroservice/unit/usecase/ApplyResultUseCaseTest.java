package ru.tinkoff.summer.taskmicroservice.unit.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.tinkoff.summer.taskmicroservice.application.port.data.AttemptPort;
import ru.tinkoff.summer.taskmicroservice.application.usecase.impl.ApplyResultUseCaseImpl;
import ru.tinkoff.summer.taskmicroservice.domain.Attempt;
import ru.tinkoff.summer.taskmicroservice.domain.Task;
import ru.tinkoff.summer.taskshareddomain.ExecutionStatus;
import ru.tinkoff.summer.taskshareddomain.Language;
import ru.tinkoff.summer.taskshareddomain.TotalExecutionResult;
import ru.tinkoff.summer.taskshareddomain.Type;
import ru.tinkoff.summer.taskshareddomain.task.TaskParams;
import ru.tinkoff.summer.taskshareddomain.task.TaskTestCase;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApplyResultUseCaseTest {

    @Mock
    private AttemptPort port;

    @InjectMocks
    private ApplyResultUseCaseImpl sut;
    private Task task;
    private Attempt pendingAttempt;
    @Captor
    private ArgumentCaptor<Attempt> attemptCaptor;

    @BeforeEach
    public void setUp() {
        task = new Task();
        task.setId(1L);
        task.setMethodName("methodName");
        task.setParams(new TaskParams(
                List.of(Type.INTEGER, Type.INTEGER), Type.INTEGER_ARR
        ));
        task.setTaskTestCases(Set.of(
                new TaskTestCase(List.of("2", "5"), "7")
        ));
        pendingAttempt = Attempt.of("class Solution{" +
                "public int[] methodName(int a, int b){return 0;}}", Language.JAVA, task);
        pendingAttempt.setStatus(ExecutionStatus.PENDING);
        pendingAttempt.setId(1L);
        when(port.getById(1L)).thenReturn(pendingAttempt);
    }

    @Test
    public void resultError() {
        var totalResult = TotalExecutionResult.builder()
                .attemptId(pendingAttempt.getId())
                .errorMessage("error message")
                .status(ExecutionStatus.ERROR).build();

        sut.apply(totalResult);
        verify(port).save(attemptCaptor.capture());

        var attempt = attemptCaptor.getValue();
        assertThat(attempt.getStatus()).isEqualTo(ExecutionStatus.ERROR);
        assertThat(attempt.getErrorMessage()).isEqualTo(totalResult.getErrorMessage());
    }
    @Test
    public void resultFailure() {
        var totalResult = TotalExecutionResult.builder()
                .attemptId(pendingAttempt.getId())
                .actualResult("2")
                .executionTimeNs(20000)
                .memoryUsageMb(2.25125)
                .status(ExecutionStatus.FAILURE).build();

         sut.apply(totalResult);
        verify(port).save(attemptCaptor.capture());

        var attempt = attemptCaptor.getValue();
        assertThat(attempt.getStatus()).isEqualTo(ExecutionStatus.FAILURE);
        assertThat(attempt.getActualResult()).isEqualTo(totalResult.getActualResult());
        assertThat(attempt.getExecutionTimeNs()).isEqualTo(totalResult.getExecutionTimeNs());
        assertThat(attempt.getMemoryUsageMb()).isEqualTo(totalResult.getMemoryUsageMb());
        assertThat(attempt.getTestCase()).isEqualTo(totalResult.getTestCase());


    }
}
