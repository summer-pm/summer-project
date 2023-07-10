package ru.tinkoff.summer.taskexecutor.domain.executor;


import ru.tinkoff.summer.taskshareddomain.AttemptDTO;
import ru.tinkoff.summer.taskshareddomain.ExecutionResult;
import ru.tinkoff.summer.taskshareddomain.Language;

public interface LanguageExecutor {
    final String PATH_TO_TMP = "tmp/";

    Language getLanguage();

    ExecutionResult execute(AttemptDTO attempt);
}
