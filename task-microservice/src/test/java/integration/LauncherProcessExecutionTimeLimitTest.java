package integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import ru.tinkoff.summer.taskservice.domain.*;
import ru.tinkoff.summer.taskservice.domain.exceptions.TimeExceedException;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class LauncherProcessExecutionTimeLimitTest {
    ProgramLauncher launcher = new ProgramLauncher();
    private Task task;
    private Attempt attempt;
    private JavaExecutor javaExecutor;
    @BeforeEach
    public void setUp(){

        task = new Task();
        task.setTitle("Sum");
        task.setMethodName("sum");
        task.setTimeLimitMs(100);
        task.setParams(new TaskParams(List.of(Type.INTEGER, Type.INTEGER), Type.INTEGER));
        task.setTaskTestCases(
                Set.of(
                        new TaskTestCase(List.of("-1", "1"), "0"),
                        new TaskTestCase(List.of("0", "0"), "0")));
        attempt =
                new Attempt(
                        "class Solution {\n"
                                + "    public int sum(int a, int b) {\n"
                                + "return a + b;\n"
                                + "    }\n"
                                + "}",
                        Language.JAVA,
                        task,
                        null);
        javaExecutor = new JavaExecutor();
    }

    @Test
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
