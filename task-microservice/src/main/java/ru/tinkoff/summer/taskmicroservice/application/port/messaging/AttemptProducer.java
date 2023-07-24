package ru.tinkoff.summer.taskmicroservice.application.port.messaging;


import ru.tinkoff.summer.taskshareddomain.AttemptForExecuteDTO;

public interface AttemptProducer {
    void publishForExecute(AttemptForExecuteDTO attempt);
}
