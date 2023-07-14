package ru.tinkoff.summer.taskexecutor.domain.executor;


import ru.tinkoff.summer.taskshareddomain.AttemptDTO;
import ru.tinkoff.summer.taskshareddomain.ExecutionResult;
import ru.tinkoff.summer.taskexecutor.domain.ProgramLauncher;
import ru.tinkoff.summer.taskexecutor.domain.driver.DriverProcessor;
import ru.tinkoff.summer.taskexecutor.domain.driver.PythonDriverProcessor;
import ru.tinkoff.summer.taskshareddomain.Language;

import java.io.*;
import java.util.List;
import java.util.UUID;

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
    public List<ExecutionResult> execute(AttemptDTO attempt) {
        var code = driverProcessor.getPreparedCode(attempt);
        var file = writeTempCode(code);

        var tester = new ProgramLauncher();
        try {
            var result =
                    tester.testProgram(attempt, "python3", file.getPath());
            return result;

        } catch (RuntimeException e) {
            throw e;
        } finally {
            file.delete();
            file.getParentFile().delete();
        }

    }

    private File writeTempCode(String preparedDriver) {
        String path = PATH_TO_TMP + "/" + UUID.randomUUID() + "/" + PATH_TO_DRIVER;

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
