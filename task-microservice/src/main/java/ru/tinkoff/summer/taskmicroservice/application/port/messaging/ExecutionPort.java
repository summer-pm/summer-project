package ru.tinkoff.summer.taskmicroservice.application.port.messaging;


import ru.tinkoff.summer.taskshareddomain.AttemptDTO;

public interface ExecutionPort {
    void publishForExecute(AttemptDTO attempt);
}
