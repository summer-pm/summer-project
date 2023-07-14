package ru.tinkoff.summer.taskmicroservice.unit.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.tinkoff.summer.taskmicroservice.domain.Attempt;
import ru.tinkoff.summer.taskmicroservice.domain.Task;
import ru.tinkoff.summer.taskshareddomain.Language;
import ru.tinkoff.summer.taskshareddomain.Type;
import ru.tinkoff.summer.taskshareddomain.task.TaskParams;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;


public class AttemptTest {
    private static Task task;
    @BeforeAll
    public static void setUp(){
        task = new Task();
        task.setMethodName("methodName");
        task.setParams(
                new TaskParams(
                        List.of(Type.INTEGER, Type.INTEGER_ARR),
                        Type.INTEGER_ARR
                )
        );
    }

    @Test
    public void codeIsBlank(){
        assertThatThrownBy(() -> {var attempt = Attempt.of("", Language.JAVA,task);})
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Код не может быть пустым");
         assertThatThrownBy(() -> {var attempt = Attempt.of("", Language.PYTHON,task);})
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Код не может быть пустым");
    }
    @Test
    public void codeNotContainsSolutionClass(){
        assertThatThrownBy(() -> {var attempt = Attempt.of("class NotSolution", Language.JAVA,task);})
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Код должен содержать класс Solution");
        assertThatThrownBy(() -> {var attempt = Attempt.of("class NotSolution", Language.PYTHON,task);})
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Код должен содержать класс Solution");
    }
    @Test
    public void codeMustContainMethodName(){
        assertThatThrownBy(() -> {var attempt = Attempt.of("class Solution{}", Language.JAVA,task);})
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Код должен содержать public метод " + task.getMethodName());
        assertThatThrownBy(() -> {var attempt = Attempt.of("class Solution{}", Language.PYTHON,task);})
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Код должен содержать public метод " + task.getMethodName());
    }
    @Test
    public void codeJavaWrongInputTypes(){
          assertThatThrownBy(() -> {var attempt = Attempt.of("class Solution{" +
                  "public int[] methodName(int i, int b)}", Language.JAVA,task);})
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Входные параметры  в methodName должны быть типов [INTEGER, INTEGER_ARR]");
    }

    @Test
    public void correctCode(){
        var attempt = Attempt.of("class Solution{" +
                "public int[] methodName(int i, int[] b)}}", Language.JAVA,task);
         var attemptPy = Attempt.of("class Solution" +
                "def methodName(a,b)", Language.PYTHON,task);
         assertThat(attempt.getId()).isNotNull();
    }
}
