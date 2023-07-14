package ru.tinkoff.summer.taskexecutor.domain.executor;

<<<<<<< HEAD
import ru.tinkoff.summer.taskexecutor.domain.Attempt;
=======

import ru.tinkoff.summer.taskshareddomain.AttemptDTO;
>>>>>>> abe64b06c86ae596b1dcb093b0d2d008782c59ce
import ru.tinkoff.summer.taskshareddomain.ExecutionResult;
import ru.tinkoff.summer.taskexecutor.domain.ProgramLauncher;
import ru.tinkoff.summer.taskexecutor.domain.driver.DriverProcessor;
import ru.tinkoff.summer.taskexecutor.domain.driver.PythonDriverProcessor;
import ru.tinkoff.summer.taskshareddomain.Language;

import java.io.*;
<<<<<<< HEAD
=======
import java.util.List;
import java.util.UUID;
>>>>>>> abe64b06c86ae596b1dcb093b0d2d008782c59ce

public class PythonExecutor implements LanguageExecutor {
    private final String PATH_TO_DRIVER = "Driver.py";
    private final DriverProcessor driverProcessor;

    public PythonExecutor() {
        this.driverProcessor = new PythonDriverProcessor();
    }

    @Override
    public Language getLanguage() {
        return Language.PYTHON;
    }

    @Override
<<<<<<< HEAD
    public ExecutionResult execute(Attempt attempt) {
=======
    public List<ExecutionResult> execute(AttemptDTO attempt) {
>>>>>>> abe64b06c86ae596b1dcb093b0d2d008782c59ce
        var code = driverProcessor.getPreparedCode(attempt);
        var file = writeTempCode(code);

        var tester = new ProgramLauncher();
        try {
            var result =
<<<<<<< HEAD
                    tester.testProgram(attempt.getTask(), "python3", file.getPath());
=======
                    tester.testProgram(attempt, "python3", file.getPath());
>>>>>>> abe64b06c86ae596b1dcb093b0d2d008782c59ce
            return result;

        } catch (RuntimeException e) {
            throw e;
        } finally {
            file.delete();
            file.getParentFile().delete();
        }

    }

    private File writeTempCode(String preparedDriver) {
<<<<<<< HEAD
        String path = PATH_TO_TMP + "/" + System.currentTimeMillis() + "/" + PATH_TO_DRIVER;
=======
        String path = PATH_TO_TMP + "/" + UUID.randomUUID() + "/" + PATH_TO_DRIVER;
>>>>>>> abe64b06c86ae596b1dcb093b0d2d008782c59ce

        File file = new File(path);
        File parentDir = file.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }
        try {
            FileWriter writer = new FileWriter(path);
            writer.write(preparedDriver);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
