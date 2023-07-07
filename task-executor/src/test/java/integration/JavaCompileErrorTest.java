package integration;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.tinkoff.summer.taskexecutor.domain.Attempt;
import ru.tinkoff.summer.taskexecutor.domain.Language;
import ru.tinkoff.summer.taskexecutor.domain.Type;
import ru.tinkoff.summer.taskexecutor.domain.exceptions.JavaCompileException;
import ru.tinkoff.summer.taskexecutor.domain.executor.JavaExecutor;
import ru.tinkoff.summer.taskexecutor.domain.task.Task;
import ru.tinkoff.summer.taskexecutor.domain.task.TaskParams;


import java.util.List;


import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class JavaCompileErrorTest {

    private Task task;
    private Attempt attempt;
    private JavaExecutor javaExecutor;

    @BeforeEach
    public void setUp() {
        javaExecutor = new JavaExecutor();
        task = new Task();
        task.setMethodName("method");
        task.setParams(new TaskParams(
                List.of(Type.INTEGER, Type.INTEGER),
                Type.INTEGER
        ));
        attempt = new Attempt(
                "",
                Language.JAVA,
                task,
                null
        );
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
