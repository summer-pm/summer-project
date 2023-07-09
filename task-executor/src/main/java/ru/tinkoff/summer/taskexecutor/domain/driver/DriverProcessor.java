package ru.tinkoff.summer.taskexecutor.domain.driver;

import ru.tinkoff.summer.taskexecutor.domain.Attempt;
import ru.tinkoff.summer.taskshareddomain.Language;
import ru.tinkoff.summer.taskshareddomain.task.TaskParams;


import java.io.*;

public abstract class DriverProcessor {
    final String PATH_TO_DRIVER = "Driver";
    final String PARAM_READ_SECTION = "${paramsInputSection}";
    final String INPUT_PARAM_LIST = "${paramList}";
    final String RETURN_TYPE = "${returnType}";
    final String METHOD_NAME = "${methodName}";
    final String SOLUTION = "${solution}";

    public abstract String getPreparedCode(Attempt attempt);
    // TODO: Отдельный класс
    protected String readGenericDriver(Language language) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader =
                new BufferedReader(new FileReader(PATH_TO_DRIVER + "." + language.extension))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    protected String getInputParamsNames(TaskParams params) {
        var array = params.getInputTypes();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.size(); i++) {
            sb.append("param").append(i + 1);
            if (i != array.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}
