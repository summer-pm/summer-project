package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ru.tinkoff.summer.taskexecutor.domain.driver.DriverProcessor;
import ru.tinkoff.summer.taskexecutor.domain.driver.PythonDriverProcessor;
import ru.tinkoff.summer.taskshareddomain.AttemptDTO;
import ru.tinkoff.summer.taskshareddomain.Language;
import ru.tinkoff.summer.taskshareddomain.Type;
import ru.tinkoff.summer.taskshareddomain.task.TaskParams;


public class PythonDriverProcessorTest {
    DriverProcessor driverProcessor = new PythonDriverProcessor();
    // TODO: Вынести в отдельный класс
    static AttemptDTO oneIntTask;
    static AttemptDTO twoIntTask;

    @BeforeAll
    public static void setUp() {
        oneIntTask = new AttemptDTO();
        oneIntTask.setMethodName("sum");
        oneIntTask.setParams(new TaskParams(List.of(Type.INTEGER), Type.INTEGER));
        twoIntTask = new AttemptDTO();
        twoIntTask.setMethodName("sum");
        twoIntTask.setParams(new TaskParams(List.of(Type.INTEGER, Type.INTEGER), Type.INTEGER));
    }

    @Test
    public void processTaskWithOneIntegerContainsParser() throws Exception {
        var attempt = createAttempt(oneIntTask);
        var processedDriver = driverProcessor.getPreparedCode(attempt);
        assertThat(processedDriver).contains("= Solution.sum(s, param1)");
        assertThat(processedDriver).contains("param1 = " + Type.INTEGER.readFuncName + "()");
        assertThat(processedDriver).contains("any code");
    }

    @Test
    public void processTaskWithTwoIntegersContainsParser() throws Exception {
        var attempt = createAttempt(twoIntTask);
        var processedDriver = driverProcessor.getPreparedCode(attempt);
        assertThat(processedDriver).contains("= Solution.sum(s, param1, param2)");
        assertThat(processedDriver).contains("param1 = " + Type.INTEGER.readFuncName + "()\n");
        assertThat(processedDriver).contains("param2 = " + Type.INTEGER.readFuncName + "()");
        assertThat(processedDriver).contains("any code");
    }

   private static AttemptDTO createAttempt(AttemptDTO attempt) {
        attempt.setCode("any code");
        attempt.setLanguage(Language.PYTHON);
        return attempt;
    }
}
