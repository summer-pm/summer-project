package ru.tinkoff.summer.taskexecutor.domain.driver;


import ru.tinkoff.summer.taskshareddomain.AttemptDTO;
import ru.tinkoff.summer.taskshareddomain.Type;
import ru.tinkoff.summer.taskshareddomain.task.TaskParams;


import java.util.List;

public class PythonDriverProcessor extends DriverProcessor {
    @Override
    public String getPreparedCode(AttemptDTO attempt) {
        String genericDriver = readGenericDriver(attempt.getLanguage());
        String preparedDriver = prepareDriver(genericDriver, attempt);
        return preparedDriver;
    }

    private String prepareDriver(String genericDriver, AttemptDTO attempt) {
        TaskParams params = attempt.getParams();
        return genericDriver
                .replace(PARAM_READ_SECTION, getParamReadSection(params))
                .replace(METHOD_NAME, attempt.getMethodName())
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
