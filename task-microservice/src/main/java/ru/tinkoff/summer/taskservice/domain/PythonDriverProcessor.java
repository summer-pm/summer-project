package ru.tinkoff.summer.taskservice.domain;

import java.util.List;

public class PythonDriverProcessor extends DriverProcessor {
    @Override
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
