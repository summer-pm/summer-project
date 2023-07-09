package ru.tinkoff.summer.taskexecutor.domain.executor;

import ru.tinkoff.summer.taskexecutor.domain.Attempt;
import ru.tinkoff.summer.taskshareddomain.ExecutionResult;
import ru.tinkoff.summer.taskexecutor.domain.Language;
import ru.tinkoff.summer.taskexecutor.domain.ProgramLauncher;
import ru.tinkoff.summer.taskexecutor.domain.driver.DriverProcessor;
import ru.tinkoff.summer.taskexecutor.domain.driver.PythonDriverProcessor;

import java.io.*;

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
    public ExecutionResult execute(Attempt attempt) {
        var code = driverProcessor.getPreparedCode(attempt);
        var file = writeTempCode(code);

        var tester = new ProgramLauncher();
        try {
            var result =
                    tester.testProgram(attempt.getTask(), "python3", file.getPath());
            return result;

        } catch (RuntimeException e) {
            throw e;
        } finally {
            file.delete();
            file.getParentFile().delete();
        }

    }

    private File writeTempCode(String preparedDriver) {
        String path = PATH_TO_TMP + "/" + System.currentTimeMillis() + "/" + PATH_TO_DRIVER;

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
