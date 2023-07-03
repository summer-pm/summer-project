package ru.tinkoff.summer.taskservice.domain;

public interface LanguageExecutor {
    final String PATH_TO_TMP = "tmp/";

    Language getLanguage();

    ExecutionResult execute(Attempt attempt);
}
