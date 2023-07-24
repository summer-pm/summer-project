package integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import ru.tinkoff.summer.taskexecutor.domain.ProgramLauncher;
import ru.tinkoff.summer.taskshareddomain.Language;
import ru.tinkoff.summer.taskshareddomain.Type;
import ru.tinkoff.summer.taskshareddomain.task.TaskParams;
import ru.tinkoff.summer.taskexecutor.domain.exceptions.TimeExceedException;
import ru.tinkoff.summer.taskexecutor.domain.executor.JavaExecutor;
import ru.tinkoff.summer.taskshareddomain.AttemptForExecuteDTO;
import ru.tinkoff.summer.taskshareddomain.task.TaskTestCase;


import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class LauncherProcessExecutionTimeLimitTest {
    ProgramLauncher launcher = new ProgramLauncher();

    private AttemptForExecuteDTO attempt;
    private JavaExecutor javaExecutor;
    @BeforeEach
    public void setUp(){

        attempt = new AttemptForExecuteDTO();
        attempt.setMethodName("sum");
        attempt.setTimeLimitMs(100);
        attempt.setParams(new TaskParams(List.of(Type.INTEGER, Type.INTEGER), Type.INTEGER));
        attempt.setTaskTestCases(
                Set.of(
                        new TaskTestCase(1L,List.of("-1", "1"), "0"),
                        new TaskTestCase(1L,List.of("0", "0"), "0")));
        attempt.setCode("class Solution {\n"
                                + "    public int sum(int a, int b) {\n"
                                + "return a + b;\n"
                                + "    }\n"
                                + "}");
        attempt.setLanguage( Language.JAVA);
        javaExecutor = new JavaExecutor();
    }

    @Test
    @Disabled //TODO: Убрал при переводе на gradle FIXME
    @Timeout(value = 3000, unit = TimeUnit.MILLISECONDS)
    public void timeOverThrowExceptionProcessEnd(){
        attempt.setCode("class Solution{" +
                " public int sum(int a, int b){\n" +
                "        try{\n" +
                "            Thread.sleep(10000);\n" +
                "        } catch (InterruptedException e) {\n" +
                "            throw new RuntimeException(e);\n" +
                "        }\n" +
                "        return 0;\n" +
                "    }}");
         assertThatThrownBy(() -> javaExecutor.execute(attempt))
                .isInstanceOf(TimeExceedException.class);
    }
     @Test
    public void timeNotOver(){
        attempt.setCode("class Solution{" +
                " public int sum(int a, int b){\n" +
                "        return 0;\n" +
                "    }}");
         javaExecutor.execute(attempt);
    }
     @Test
    public void runtimeExceptionWhileExecute(){
        attempt.setCode("class Solution{" +
                " public int sum(int a, int b){\n" +
                "   throw new RuntimeException();     \n" +
                "    }}");
          assertThatThrownBy(() -> javaExecutor.execute(attempt)) //TODO: ExactlyInstanceOf
                .isInstanceOf(RuntimeException.class);
    }
}
