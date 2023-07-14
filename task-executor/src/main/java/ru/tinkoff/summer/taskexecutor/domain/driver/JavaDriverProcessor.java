package ru.tinkoff.summer.taskexecutor.domain.driver;

<<<<<<< HEAD
import ru.tinkoff.summer.taskexecutor.domain.*;
import ru.tinkoff.summer.taskexecutor.domain.task.Task;
=======
import ru.tinkoff.summer.taskshareddomain.AttemptDTO;
>>>>>>> abe64b06c86ae596b1dcb093b0d2d008782c59ce
import ru.tinkoff.summer.taskshareddomain.Language;
import ru.tinkoff.summer.taskshareddomain.Type;
import ru.tinkoff.summer.taskshareddomain.task.TaskParams;


import java.util.List;

public class JavaDriverProcessor extends DriverProcessor {
    @Override
<<<<<<< HEAD
    public String getPreparedCode(Attempt attempt) {
=======
    public String getPreparedCode(AttemptDTO attempt) {
>>>>>>> abe64b06c86ae596b1dcb093b0d2d008782c59ce
        String genericDriver = readGenericDriver(Language.JAVA);
        return prepareDriver(genericDriver, attempt);
    }

<<<<<<< HEAD
    private String prepareDriver(String genericDriver, Attempt attempt) {
        Task task = attempt.getTask();
        TaskParams params = task.getParams();

        return genericDriver
                .replace(PARAM_READ_SECTION, getInputReadSection(params, attempt.getLanguage()))
                .replace(METHOD_NAME, task.getMethodName())
                .replace(INPUT_PARAM_LIST, getInputParamsNames(params))
                .replace(RETURN_TYPE, getOutputTypeName(attempt.getLanguage(), task))
=======
    private String prepareDriver(String genericDriver, AttemptDTO attempt) {

        TaskParams params = attempt.getParams();

        return genericDriver
                .replace(PARAM_READ_SECTION, getInputReadSection(params, attempt.getLanguage()))
                .replace(METHOD_NAME, attempt.getMethodName())
                .replace(INPUT_PARAM_LIST, getInputParamsNames(params))
                .replace(RETURN_TYPE, getOutputTypeName(attempt))
>>>>>>> abe64b06c86ae596b1dcb093b0d2d008782c59ce
                .replace(SOLUTION, attempt.getCode());
    }

    private CharSequence getInputReadSection(TaskParams params, Language language) {
        var inputSection = new StringBuilder();
        List<Type> inputTypes = params.getInputTypes();
        for (int i = 0; i < inputTypes.size(); i++) {
            Type type = inputTypes.get(i);
            inputSection
                    .append(language.getTypeName(type))
                    .append(" param")
                    .append(i + 1)
                    .append(" = ")
                    .append(type.readFuncName)
                    .append("()")
                    .append(";")
                    .append("\n");
        }
        return inputSection.toString();
    }

<<<<<<< HEAD
    private static String getOutputTypeName(Language language, Task task) {
        return language.getTypeName(task.getParams().getOutputType());
=======
    private static String getOutputTypeName(AttemptDTO attemptDTO) {
        return attemptDTO.getLanguage().getTypeName(attemptDTO.getParams().getOutputType());
>>>>>>> abe64b06c86ae596b1dcb093b0d2d008782c59ce
    }
}
