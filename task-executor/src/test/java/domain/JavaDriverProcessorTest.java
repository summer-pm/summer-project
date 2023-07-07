package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.tinkoff.summer.taskexecutor.domain.Attempt;
import ru.tinkoff.summer.taskexecutor.domain.Language;
import ru.tinkoff.summer.taskexecutor.domain.Type;
import ru.tinkoff.summer.taskexecutor.domain.driver.DriverProcessor;
import ru.tinkoff.summer.taskexecutor.domain.driver.JavaDriverProcessor;
import ru.tinkoff.summer.taskexecutor.domain.task.Task;
import ru.tinkoff.summer.taskexecutor.domain.task.TaskParams;


public class JavaDriverProcessorTest {
    DriverProcessor processor = new JavaDriverProcessor();

    static Task oneIntTask;
    static Task twoIntTask;

    @BeforeAll
    public static void setUp() {
        oneIntTask = new Task();
        oneIntTask.setMethodName("sum");
        oneIntTask.setParams(new TaskParams(List.of(Type.INTEGER), Type.INTEGER));
        twoIntTask = new Task();
        twoIntTask.setMethodName("sum");
        twoIntTask.setParams(new TaskParams(List.of(Type.INTEGER, Type.INTEGER), Type.INTEGER));
    }

    @Test
    public void processOneInteger() throws Exception {
        var attempt = createAttempt(oneIntTask);
        var processedDriver = processor.getPreparedCode(attempt);
        assertThat(processedDriver)
                .contains(
                        "int param1 = READ_INTEGER();");
        assertThat(processedDriver)
                .contains(
                        Language.JAVA.getTypeName(Type.INTEGER)
                                + " result = new Solution().sum(param1);");
    }

    @Test
    public void processTwoInteger() throws Exception {
        var attempt = createAttempt(twoIntTask);
        var processedDriver = processor.getPreparedCode(attempt);
        assertThat(processedDriver)
                .contains(
                        "int param1 = READ_INTEGER();");
        assertThat(processedDriver)
                .contains(
                       "int param2 = READ_INTEGER();");
        assertThat(processedDriver)
                .contains(
                        Language.JAVA.getTypeName(Type.INTEGER)
                                + " result = new Solution().sum(param1, param2)");
    }

    private static Attempt createAttempt(Task task) {
        return new Attempt("any code", Language.JAVA, task, null);
    }
}
