package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
<<<<<<< HEAD
import ru.tinkoff.summer.taskexecutor.domain.Attempt;
import ru.tinkoff.summer.taskexecutor.domain.Language;
import ru.tinkoff.summer.taskexecutor.domain.Type;
import ru.tinkoff.summer.taskexecutor.domain.driver.DriverProcessor;
import ru.tinkoff.summer.taskexecutor.domain.driver.JavaDriverProcessor;
import ru.tinkoff.summer.taskexecutor.domain.task.Task;
import ru.tinkoff.summer.taskexecutor.domain.task.TaskParams;
=======

import ru.tinkoff.summer.taskexecutor.domain.driver.DriverProcessor;
import ru.tinkoff.summer.taskexecutor.domain.driver.JavaDriverProcessor;

import ru.tinkoff.summer.taskshareddomain.AttemptDTO;
import ru.tinkoff.summer.taskshareddomain.Language;
import ru.tinkoff.summer.taskshareddomain.Type;
import ru.tinkoff.summer.taskshareddomain.task.TaskParams;
>>>>>>> abe64b06c86ae596b1dcb093b0d2d008782c59ce


public class JavaDriverProcessorTest {
    DriverProcessor processor = new JavaDriverProcessor();

<<<<<<< HEAD
    static Task oneIntTask;
    static Task twoIntTask;

    @BeforeAll
    public static void setUp() {
        oneIntTask = new Task();
        oneIntTask.setMethodName("sum");
        oneIntTask.setParams(new TaskParams(List.of(Type.INTEGER), Type.INTEGER));
        twoIntTask = new Task();
=======
    static AttemptDTO oneIntTask;
    static AttemptDTO twoIntTask;

    @BeforeAll
    public static void setUp() {
        oneIntTask = new AttemptDTO();
        oneIntTask.setMethodName("sum");
        oneIntTask.setParams(new TaskParams(List.of(Type.INTEGER), Type.INTEGER));
        twoIntTask = new AttemptDTO();
>>>>>>> abe64b06c86ae596b1dcb093b0d2d008782c59ce
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

<<<<<<< HEAD
    private static Attempt createAttempt(Task task) {
        return new Attempt("any code", Language.JAVA, task, null);
=======
    private static AttemptDTO createAttempt(AttemptDTO attempt) {
        attempt.setCode("any code");
        attempt.setLanguage(Language.JAVA);
        return attempt;
>>>>>>> abe64b06c86ae596b1dcb093b0d2d008782c59ce
    }
}
