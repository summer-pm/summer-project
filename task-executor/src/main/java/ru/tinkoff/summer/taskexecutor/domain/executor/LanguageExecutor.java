package ru.tinkoff.summer.taskexecutor.domain.executor;


import ru.tinkoff.summer.taskshareddomain.AttemptForExecuteDTO;
import ru.tinkoff.summer.taskshareddomain.ExecutionResult;
import ru.tinkoff.summer.taskshareddomain.Language;

import java.util.List;

public interface LanguageExecutor {
    String PATH_TO_TMP = "tmp/";

    Language getLanguage();

    List<ExecutionResult> execute(AttemptForExecuteDTO attempt);
}
