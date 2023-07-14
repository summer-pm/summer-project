package ru.tinkoff.summer.taskmicroservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import ru.tinkoff.summer.taskshareddomain.ExecutionStatus;
import ru.tinkoff.summer.taskshareddomain.Language;
import ru.tinkoff.summer.taskshareddomain.Type;
import ru.tinkoff.summer.taskshareddomain.task.TaskTestCase;

import java.util.Arrays;
import java.util.List;



@Getter
@Setter
public class Attempt {
    private long id;
    private String code;
    private Language language;
    @JsonIgnore
    private Task task;
    private ExecutionStatus status;
    private double executionTimeNs;
    private String actualResult;
    private double memoryUsageMb;
    private TaskTestCase testCase;
    private String errorMessage;

    public static Attempt of(String code, Language language, Task task) {
        checkCodeIsNotBlank(code);

        checkCodeHaveSolutionClass(code);

        chackCodeHaveMethod(code, task);

        String[] parameterTypes = extractParameterTypes(code, task.getMethodName());


        checkTypesMatch(language, task, parameterTypes);

        return new Attempt(code, language, task);
    }

    private static void checkTypesMatch(Language language, Task task, String[] parameterTypes) {
        if (language == Language.JAVA) {

            List<Type> neededTypes = task.getParams().getInputTypes();

            for (int i = 0; i < parameterTypes.length; i++) {
                String parameterType = parameterTypes[i];
                Type neededType = neededTypes.get(i);

                if (!parameterType.equals(language.getTypeName(neededType))) {
                    throw new IllegalArgumentException("Входные параметры  в " + task.getMethodName() + " должны быть типов " + task.getParams().getInputTypes().toString());
                }
            }
        }
    }



    private static void chackCodeHaveMethod(String code, Task task) {
        if (!code.contains(task.getMethodName()))
            throw new IllegalArgumentException("Код должен содержать public метод " + task.getMethodName());
    }

    private static void checkCodeHaveSolutionClass(String code) {
        if (!code.replaceAll("\\s+", " ").contains("class Solution"))
            throw new IllegalArgumentException("Код должен содержать класс Solution");
    }

    private static void checkCodeIsNotBlank(String code) {
        if (code == null || code.isBlank())
            throw new IllegalArgumentException("Код не может быть пустым");
    }

    private static String[] extractParameterTypes(String input, String methodName) {
        int startIndex = input.indexOf(methodName) + methodName.length() + 1;
        int endIndex = input.indexOf(')');

        String paramsString = input.substring(startIndex, endIndex);
        String[] paramsArray = paramsString.split("\\s*,\\s*");

        String[] result = new String[paramsArray.length];
        for (int i = 0; i < paramsArray.length; i++) {
            String param = paramsArray[i];
            String[] parts = param.split("\\s+");

            // Извлекаем тип из частей параметра
            result[i] = parts[0];
        }

        return result;
    }

    private Attempt(String code, Language language, Task task) {
        this.code = code;
        this.language = language;
        this.task = task;
    }
}
