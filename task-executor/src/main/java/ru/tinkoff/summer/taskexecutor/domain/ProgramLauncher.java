package ru.tinkoff.summer.taskexecutor.domain;

<<<<<<< HEAD
import ru.tinkoff.summer.taskexecutor.domain.exceptions.JavaCompileException;
import ru.tinkoff.summer.taskexecutor.domain.task.Task;
import ru.tinkoff.summer.taskshareddomain.ExecutionResult;
import ru.tinkoff.summer.taskshareddomain.task.TaskTestCase;


=======
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tinkoff.summer.taskexecutor.domain.exceptions.JavaCompileException;
import ru.tinkoff.summer.taskshareddomain.AttemptDTO;
import ru.tinkoff.summer.taskshareddomain.ExecutionResult;
import ru.tinkoff.summer.taskshareddomain.task.TaskTestCase;

>>>>>>> abe64b06c86ae596b1dcb093b0d2d008782c59ce
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
<<<<<<< HEAD

public class ProgramLauncher {
    private static final double TIME_LIMIT_MULTIPLY = 3;
=======
//TODO: РЕФАКТОР
public class ProgramLauncher {
    private static final long TIME_LIMIT_MS = 2000;
    private static final Logger log = LoggerFactory.getLogger(ProgramLauncher.class);


>>>>>>> abe64b06c86ae596b1dcb093b0d2d008782c59ce
    public void compileProgram(String... commands) {
        try {
            ProcessBuilder compileProcessBuilder = new ProcessBuilder(commands);
            Process compileProcess = compileProcessBuilder.start();

            var errors = readErrors(compileProcess);

            int exitCode = compileProcess.waitFor();

            if (exitCode != 0) {
                throw new JavaCompileException(errors.toString());
            }
        } catch (IOException | InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    private StringBuilder readErrors(Process process) throws IOException {
        InputStream errorStream = process.getErrorStream();
        BufferedReader errorReader = new BufferedReader(new InputStreamReader(errorStream));
        String line;
        var errors = new StringBuilder();
        while ((line = errorReader.readLine()) != null) {
            errors.append(line).append("\n");
        }
<<<<<<< HEAD
        return errors;
    }

    public ExecutionResult testProgram(Task task, String... commands) {
=======

        return errors;
    }

    public List<ExecutionResult> testProgram(AttemptDTO attempt, String... commands) {
>>>>>>> abe64b06c86ae596b1dcb093b0d2d008782c59ce
        ProcessBuilder builder = new ProcessBuilder(commands);
        List<ExecutionResult> results = new LinkedList<>();

        try {
<<<<<<< HEAD
            for (TaskTestCase testCase : task.getTaskTestCases()) {

                Process process = builder.start();
                long timeoutInMillis = (long) (task.getTimeLimitMs() * TIME_LIMIT_MULTIPLY);
//                Timer timer = new Timer(true);
//                timer.schedule(new TimerTask() {
//                    @Override
//                    public void run() {
//                        process.destroy();
//                        cancel();
//                    }
//                }, timeoutInMillis);
=======
            for (TaskTestCase testCase : attempt.getTaskTestCases()) {

                Process process = builder.start();


                Timer timer = new Timer(true);
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        log.warn("FinishTimer {}",attempt.getId());
                        process.destroy();
                        cancel();
                    }
                }, TIME_LIMIT_MS);

>>>>>>> abe64b06c86ae596b1dcb093b0d2d008782c59ce
                inputTestData(testCase, process);

                var errorOutput = readErrors(process);

                ArrayList<String> output = readOutputData(process);

                int exitCode = process.waitFor();
                if (exitCode != 0) {
<<<<<<< HEAD
                    throw new RuntimeException(errorOutput.toString());
                } else {
                    var testCaseResult = new ExecutionResult(output, testCase);
                    results.add(testCaseResult);
                    if (!testCaseResult.isSuccess())
                        return testCaseResult; // TODO: Возможно исключениями
                }
//                timer.cancel();
=======
                    if (errorOutput.toString().isBlank()) {//Процесс завершается с кодом 1 без ошибки при оканчивании таймера
                        throw new RuntimeException("Превышено время ожидания");
                    } else {
                        timer.cancel();
                        throw new RuntimeException(errorOutput.toString());
                    }
                } else {
                    timer.cancel();
                    var testCaseResult = new ExecutionResult(output, testCase);
                    results.add(testCaseResult);
                    if (!testCaseResult.isSuccess())
                        return results; // TODO: Возможно исключениями
                }

>>>>>>> abe64b06c86ae596b1dcb093b0d2d008782c59ce
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
<<<<<<< HEAD
        return results.get(results.size() - 1); // TODO: Возможно среднее время и память
=======
        return results;
>>>>>>> abe64b06c86ae596b1dcb093b0d2d008782c59ce
    }

    private static ArrayList<String> readOutputData(Process process) throws IOException {
        BufferedReader outputReader =
                new BufferedReader(new InputStreamReader(process.getInputStream()));

        var output = new ArrayList<String>(3);
        String line;
        while ((line = outputReader.readLine()) != null) {
            output.add(line);
        }
        outputReader.close();
        return output;
    }

    private static void inputTestData(TaskTestCase testCase, Process process) throws IOException {
        for (String input : testCase.getInputValues()) {
            process.getOutputStream().write((input + "\n").getBytes());
        }
        try {
            process.getOutputStream().close();
        } catch (IOException ignore) {
            // Если процесс завершится с ошибкой мы не сможем закрыть и прочитать ошибки
        }
    }
}
