package ru.tinkoff.summer.taskexecutor.domain.executor;

<<<<<<< HEAD
import ru.tinkoff.summer.taskexecutor.domain.Attempt;
=======

import ru.tinkoff.summer.taskshareddomain.AttemptDTO;
>>>>>>> abe64b06c86ae596b1dcb093b0d2d008782c59ce
import ru.tinkoff.summer.taskshareddomain.ExecutionResult;
import ru.tinkoff.summer.taskexecutor.domain.ProgramLauncher;
import ru.tinkoff.summer.taskexecutor.domain.driver.DriverProcessor;
import ru.tinkoff.summer.taskexecutor.domain.driver.JavaDriverProcessor;
import ru.tinkoff.summer.taskexecutor.domain.exceptions.JavaCompileException;
import ru.tinkoff.summer.taskshareddomain.Language;


import java.io.*;
<<<<<<< HEAD
=======
import java.util.List;
import java.util.UUID;
>>>>>>> abe64b06c86ae596b1dcb093b0d2d008782c59ce

public class JavaExecutor implements LanguageExecutor {
    private final DriverProcessor driverProcessor;
    private final String PATH_TO_DRIVER = "Driver.java";

    public JavaExecutor() {
        this.driverProcessor = new JavaDriverProcessor();
    }

    @Override
    public Language getLanguage() {
        return Language.JAVA;
    }

    @Override
<<<<<<< HEAD
    public ExecutionResult execute(Attempt attempt) {
=======
    public List<ExecutionResult> execute(AttemptDTO attempt) {
>>>>>>> abe64b06c86ae596b1dcb093b0d2d008782c59ce
        var code = driverProcessor.getPreparedCode(attempt);
        var file = writeTempCode(code);
        try {

            var launcher = new ProgramLauncher();

            launcher.compileProgram("javac", file.getParent() + "/" + PATH_TO_DRIVER);

            return launcher.testProgram(
<<<<<<< HEAD
                    attempt.getTask(),
=======
                    attempt,
>>>>>>> abe64b06c86ae596b1dcb093b0d2d008782c59ce
                    "java",
                    "-cp",
                    file.getParent(),
                    "Driver");

        } catch (JavaCompileException e) { //TODO: Может CompileException, + добавить TestExecutionException
            throw e;
        } finally {
            deleteFolder(file.getParentFile());
        }
    }

    public static void deleteFolder(File folder) {
        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        file.delete();
                    }
                }
            }
            folder.delete();
        }
    }

    private File writeTempCode(String preparedDriver) {
<<<<<<< HEAD
        String path = PATH_TO_TMP + "/" + System.currentTimeMillis() + "/" + PATH_TO_DRIVER;
=======
        String path = PATH_TO_TMP + "/" + UUID.randomUUID().toString() + "/" + PATH_TO_DRIVER;
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
