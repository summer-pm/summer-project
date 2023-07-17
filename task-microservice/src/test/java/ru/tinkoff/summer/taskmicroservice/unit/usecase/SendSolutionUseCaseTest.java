package ru.tinkoff.summer.taskmicroservice.unit.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.tinkoff.summer.taskmicroservice.DTO.SolutionData;
import ru.tinkoff.summer.taskmicroservice.application.port.data.AttemptPort;
import ru.tinkoff.summer.taskmicroservice.application.port.data.TaskPort;
import ru.tinkoff.summer.taskmicroservice.application.port.messaging.AttemptProducer;
import ru.tinkoff.summer.taskmicroservice.application.usecase.impl.SendSolutionUseCaseImpl;
import ru.tinkoff.summer.taskmicroservice.domain.Attempt;
import ru.tinkoff.summer.taskmicroservice.domain.Task;
import ru.tinkoff.summer.taskshareddomain.ExecutionStatus;
import ru.tinkoff.summer.taskshareddomain.Language;
import ru.tinkoff.summer.taskshareddomain.Type;
import ru.tinkoff.summer.taskshareddomain.task.TaskParams;
import ru.tinkoff.summer.taskshareddomain.task.TaskTestCase;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SendSolutionUseCaseTest {
    @Mock
    private AttemptPort attemptPort;
    @Mock
    private TaskPort taskPort;
    @Mock
    private AttemptProducer attemptProducer;
    @InjectMocks
    private SendSolutionUseCaseImpl sut;
    private Task task;
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
        when(taskPort.getTask(task.getId())).thenReturn(task);
    }

    @Test
    public void incorrectCode_Error() {
        var code = "";
        var solution = getSolution(code);
        assertThatThrownBy(() -> sut.execute(solution))
                .isInstanceOf(IllegalArgumentException.class);
        verify(attemptPort, never()).save(any());
        verify(attemptProducer, never()).publishForExecute(any());
    }

    @Test
    public void sendSolution_StatusPending() {
        var code = "class Solution{" +
                "public int[] methodName(int a, int b){return a+b;}}";
        var solution = getSolution(code);
        when(attemptPort.save(any())).then(returnsFirstArg());

        sut.execute(solution);

        verify(attemptPort).save(attemptCaptor.capture());
        Attempt attempt = attemptCaptor.getValue();

        assertThat(attempt.getStatus()).isEqualTo(ExecutionStatus.PENDING);
        verify(attemptProducer).publishForExecute(any());
    }

    private SolutionData getSolution(String code) {
        var solutionData = new SolutionData();
        solutionData.setLanguage(Language.JAVA);
        solutionData.setTaskId(1L);
        solutionData.setCode(code);
        return solutionData;
    }
}
