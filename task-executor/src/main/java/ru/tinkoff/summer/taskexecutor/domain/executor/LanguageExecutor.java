package ru.tinkoff.summer.taskexecutor.domain.executor;

<<<<<<< HEAD
import ru.tinkoff.summer.taskexecutor.domain.Attempt;
import ru.tinkoff.summer.taskshareddomain.ExecutionResult;
import ru.tinkoff.summer.taskshareddomain.Language;

public interface LanguageExecutor {
    final String PATH_TO_TMP = "tmp/";

    Language getLanguage();

    ExecutionResult execute(Attempt attempt);
=======

import ru.tinkoff.summer.taskshareddomain.AttemptDTO;
import ru.tinkoff.summer.taskshareddomain.ExecutionResult;
import ru.tinkoff.summer.taskshareddomain.Language;

import java.util.List;

public interface LanguageExecutor {
    String PATH_TO_TMP = "tmp/";

    Language getLanguage();

    List<ExecutionResult> execute(AttemptDTO attempt);
>>>>>>> abe64b06c86ae596b1dcb093b0d2d008782c59ce
}
