package ru.tinkoff.summer.taskexecutor.domain.driver;

import ru.tinkoff.summer.taskshareddomain.AttemptDTO;
import ru.tinkoff.summer.taskshareddomain.Language;
import ru.tinkoff.summer.taskshareddomain.Type;
import ru.tinkoff.summer.taskshareddomain.task.TaskParams;


import java.util.List;

public class JavaDriverProcessor extends DriverProcessor {
    @Override
    public String getPreparedCode(AttemptDTO attempt) {
        String genericDriver = readGenericDriver(Language.JAVA);
        return prepareDriver(genericDriver, attempt);
    }

    private String prepareDriver(String genericDriver, AttemptDTO attempt) {

        TaskParams params = attempt.getParams();

        return genericDriver
                .replace(PARAM_READ_SECTION, getInputReadSection(params, attempt.getLanguage()))
                .replace(METHOD_NAME, attempt.getMethodName())
                .replace(INPUT_PARAM_LIST, getInputParamsNames(params))
                .replace(RETURN_TYPE, getOutputTypeName(attempt))
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

    private static String getOutputTypeName(AttemptDTO attemptDTO) {
        return attemptDTO.getLanguage().getTypeName(attemptDTO.getParams().getOutputType());
    }
}
