package ru.tinkoff.summer.taskexecutor.domain.driver;

<<<<<<< HEAD
import ru.tinkoff.summer.taskexecutor.domain.Attempt;
import ru.tinkoff.summer.taskexecutor.domain.task.Task;
=======

import ru.tinkoff.summer.taskshareddomain.AttemptDTO;
>>>>>>> abe64b06c86ae596b1dcb093b0d2d008782c59ce
import ru.tinkoff.summer.taskshareddomain.Type;
import ru.tinkoff.summer.taskshareddomain.task.TaskParams;


import java.util.List;

public class PythonDriverProcessor extends DriverProcessor {
    @Override
<<<<<<< HEAD
    public String getPreparedCode(Attempt attempt) {
        String genericDriver = readGenericDriver(attempt.getLanguage());
        String preparedDriver = prepareDriver(genericDriver, attempt);
        return preparedDriver;
    }

    private String prepareDriver(String genericDriver, Attempt attempt) {
        Task task = attempt.getTask();
        TaskParams params = task.getParams();
        return genericDriver
                .replace(PARAM_READ_SECTION, getParamReadSection(params))
                .replace(METHOD_NAME, task.getMethodName())
=======
    public String getPreparedCode(AttemptDTO attempt) {
        String genericDriver = readGenericDriver(attempt.getLanguage());
        return prepareDriver(genericDriver, attempt);
    }

    private String prepareDriver(String genericDriver, AttemptDTO attempt) {
        TaskParams params = attempt.getParams();
        return genericDriver
                .replace(PARAM_READ_SECTION, getParamReadSection(params))
                .replace(METHOD_NAME, attempt.getMethodName())
>>>>>>> abe64b06c86ae596b1dcb093b0d2d008782c59ce
                .replace(INPUT_PARAM_LIST, getInputParamsNames(params))
                .replace(SOLUTION, attempt.getCode());
    }

    private String getParamReadSection(TaskParams params) {
        var inputSection = new StringBuilder();
        List<Type> inputTypes = params.getInputTypes();
        for (int i = 0; i < inputTypes.size(); i++) {
            inputSection
                    .append("    ")
                    .append("param")
                    .append(i + 1)
                    .append(" = ")
                    .append(inputTypes.get(i).readFuncName)
                    .append("()\n");
        }
        return inputSection.toString();
    }
}
