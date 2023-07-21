package ru.tinkoff.summer.taskshareddomain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import ru.tinkoff.summer.taskshareddomain.task.TaskTestCase;
import ru.tinkoff.summer.taskshareddomain.task.dto.TaskForAttemptDTO;

import java.time.LocalDateTime;
import java.util.List;



@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class AttemptDTO {
    private long id;
    private String code;
    private Language language;
    @JsonIgnore
    private TaskForAttemptDTO task;
    private ExecutionStatus status;
    private Double executionTimeNs;
    private String actualResult;
    private Double memoryUsageMb;
    private TaskTestCase testCase;
    private String errorMessage;

    private LocalDateTime creationDate;
    public static AttemptDTO of(String code, Language language, TaskForAttemptDTO task) {
        checkCodeIsNotBlank(code);

        checkCodeHaveSolutionClass(code);

        chackCodeHaveMethod(code, task);

        String[] parameterTypes = extractParameterTypes(code, task.getMethodName());


        checkTypesMatch(language, task, parameterTypes);

        return new AttemptDTO(code, language, task);
    }

    private static void checkTypesMatch(Language language, TaskForAttemptDTO task, String[] parameterTypes) {
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



    private static void chackCodeHaveMethod(String code, TaskForAttemptDTO task) {
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
            result[i] = parts[0];
        }

        return result;
    }

    private AttemptDTO(String code, Language language, TaskForAttemptDTO task) {
        this.code = code;
        this.language = language;
        this.task = task;
    }
}
