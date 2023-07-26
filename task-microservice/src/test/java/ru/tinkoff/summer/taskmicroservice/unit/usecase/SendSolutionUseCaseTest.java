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
import ru.tinkoff.summer.taskmicroservice.application.port.data.CrudPort;
import ru.tinkoff.summer.taskmicroservice.application.port.messaging.AttemptProducer;
import ru.tinkoff.summer.taskmicroservice.application.usecase.impl.SendSolutionUseCaseImpl;
import ru.tinkoff.summer.taskshareddomain.AttemptDTO;
import ru.tinkoff.summer.taskshareddomain.ExecutionStatus;
import ru.tinkoff.summer.taskshareddomain.Language;
import ru.tinkoff.summer.taskshareddomain.Type;
import ru.tinkoff.summer.taskshareddomain.task.TaskParams;
import ru.tinkoff.summer.taskshareddomain.task.TaskTestCase;
import ru.tinkoff.summer.taskshareddomain.task.dto.TaskForAttemptDTO;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.AdditionalAnswers.returnsLastArg;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SendSolutionUseCaseTest {
    @Mock
    private CrudPort crudPort;
    @Mock
    private AttemptProducer attemptProducer;
    @InjectMocks
    private SendSolutionUseCaseImpl sut;
    @Captor
    private ArgumentCaptor<AttemptDTO> attemptCaptor;
  private String email = "email@mail.com";
    @BeforeEach
    public void setUp() {
        TaskForAttemptDTO task = new TaskForAttemptDTO();
        task.setId(1L);
        task.setMethodName("methodName");
        task.setParams(new TaskParams(
                List.of(Type.INTEGER, Type.INTEGER), Type.INTEGER_ARR
        ));
        task.setTaskTestCases(Set.of(
                new TaskTestCase(10L,List.of("2", "5"), "7")
        ));
        when(crudPort.getTask(task.getId())).thenReturn(task);
    }

    @Test
    public void incorrectCode_Error() {
        var code = "";

        var solution = getSolution(code);

        assertThatThrownBy(() -> sut.execute(email, solution))
                .isInstanceOf(IllegalArgumentException.class);
        verify(crudPort, never()).save(any(String.class),any(long.class),any());
        verify(attemptProducer, never()).publishForExecute(any());
    }

    @Test
    public void sendSolution_StatusPending() {
        var code = "class Solution{" +
                "public int[] methodName(int a, int b){return a+b;}}";
        var solution = getSolution(code);
        when(crudPort.save(any(String.class),any(long.class), any())).then(returnsLastArg());

        sut.execute(email, solution);

        verify(crudPort).save(any(String.class),any(long.class),attemptCaptor.capture());
        AttemptDTO attemptDTO = attemptCaptor.getValue();

        assertThat(attemptDTO.getStatus()).isEqualTo(ExecutionStatus.PENDING);
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
