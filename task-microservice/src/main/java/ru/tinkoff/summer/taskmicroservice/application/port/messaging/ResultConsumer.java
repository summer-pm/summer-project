package ru.tinkoff.summer.taskmicroservice.application.port.messaging;

import ru.tinkoff.summer.taskshareddomain.TotalExecutionResult;

public interface ResultConsumer {
    void consume(TotalExecutionResult result);
}
