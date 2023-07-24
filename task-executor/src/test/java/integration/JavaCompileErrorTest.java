package integration;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import ru.tinkoff.summer.taskexecutor.domain.exceptions.JavaCompileException;
import ru.tinkoff.summer.taskexecutor.domain.executor.JavaExecutor;

import ru.tinkoff.summer.taskshareddomain.AttemptForExecuteDTO;
import ru.tinkoff.summer.taskshareddomain.Language;
import ru.tinkoff.summer.taskshareddomain.Type;
import ru.tinkoff.summer.taskshareddomain.task.TaskParams;


import java.util.List;


import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class JavaCompileErrorTest {


    private AttemptForExecuteDTO attempt;
    private JavaExecutor javaExecutor;

    @BeforeEach
    public void setUp() {
        javaExecutor = new JavaExecutor();
        attempt = new AttemptForExecuteDTO();
        attempt.setMethodName("method");
        attempt.setParams(new TaskParams(
                List.of(Type.INTEGER, Type.INTEGER),
                Type.INTEGER
        ));
        attempt.setLanguage(Language.JAVA);
        attempt.setCode("");

    }

    @Test
    public void javaCompileSolutionNotFoundError() {
        attempt.setCode("Just code without Solution class");
        assertThatThrownBy(() -> javaExecutor.execute(attempt))
                .isInstanceOf(JavaCompileException.class)
                .hasMessageStartingWith("error: ")
                .hasMessageNotContaining("Driver.java")
                .hasMessageNotContaining("tmp/");
    }
     @Test
    public void javaCompileSolutionNotFoundMethodError() {
        attempt.setCode("class Solution {" +
                "}");
        assertThatThrownBy(() -> javaExecutor.execute(attempt))
                .isInstanceOf(JavaCompileException.class)
                .hasMessageStartingWith("error: ")
                .hasMessageNotContaining("Driver.java")
                .hasMessageNotContaining("tmp/");

    }
    @Test
    public void javaCompilePublicSolutionError() {
        attempt.setCode("public class Solution {" +
                "}");
        assertThatThrownBy(() -> javaExecutor.execute(attempt))
                .isInstanceOf(JavaCompileException.class)
                .hasMessageStartingWith("error: ")
                .hasMessageNotContaining("Driver.java")
                .hasMessageNotContaining("tmp/");
    }
 @Test
    public void javaCompileMethodTypeError() {
        attempt.setCode("class Solution { public int[] twoSum(int nums, int target) {}" +
                "}");
        assertThatThrownBy(() -> javaExecutor.execute(attempt))
                .isInstanceOf(JavaCompileException.class)
                .hasMessageStartingWith("error: ")
                .hasMessageNotContaining("Driver.java")
                .hasMessageNotContaining("tmp/");
    }
}
