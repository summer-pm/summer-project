package ru.tinkoff.summer.taskmicroservice.application.port.messaging;

import ru.tinkoff.summer.taskmicroservice.domain.Attempt;

public interface ExecutionPort {
    void publishForExecute(Attempt attempt);
}
