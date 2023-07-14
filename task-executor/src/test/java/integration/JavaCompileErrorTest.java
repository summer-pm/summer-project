package integration;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
<<<<<<< HEAD
import ru.tinkoff.summer.taskexecutor.domain.Attempt;
import ru.tinkoff.summer.taskexecutor.domain.Language;
import ru.tinkoff.summer.taskexecutor.domain.Type;
import ru.tinkoff.summer.taskexecutor.domain.exceptions.JavaCompileException;
import ru.tinkoff.summer.taskexecutor.domain.executor.JavaExecutor;
import ru.tinkoff.summer.taskexecutor.domain.task.Task;
import ru.tinkoff.summer.taskexecutor.domain.task.TaskParams;
=======


import ru.tinkoff.summer.taskexecutor.domain.exceptions.JavaCompileException;
import ru.tinkoff.summer.taskexecutor.domain.executor.JavaExecutor;

import ru.tinkoff.summer.taskshareddomain.AttemptDTO;
import ru.tinkoff.summer.taskshareddomain.Language;
import ru.tinkoff.summer.taskshareddomain.Type;
import ru.tinkoff.summer.taskshareddomain.task.TaskParams;
>>>>>>> abe64b06c86ae596b1dcb093b0d2d008782c59ce


import java.util.List;


import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class JavaCompileErrorTest {

<<<<<<< HEAD
    private Task task;
    private Attempt attempt;
=======

    private AttemptDTO attempt;
>>>>>>> abe64b06c86ae596b1dcb093b0d2d008782c59ce
    private JavaExecutor javaExecutor;

    @BeforeEach
    public void setUp() {
        javaExecutor = new JavaExecutor();
<<<<<<< HEAD
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
=======
        attempt = new AttemptDTO();
        attempt.setMethodName("method");
        attempt.setParams(new TaskParams(
                List.of(Type.INTEGER, Type.INTEGER),
                Type.INTEGER
        ));
        attempt.setLanguage(Language.JAVA);
        attempt.setCode("");

>>>>>>> abe64b06c86ae596b1dcb093b0d2d008782c59ce
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
