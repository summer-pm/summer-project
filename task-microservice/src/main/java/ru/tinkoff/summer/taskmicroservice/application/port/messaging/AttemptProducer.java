package ru.tinkoff.summer.taskmicroservice.application.port.messaging;


import ru.tinkoff.summer.taskshareddomain.AttemptDTO;

public interface AttemptProducer {
    void publishForExecute(AttemptDTO attempt);
}
