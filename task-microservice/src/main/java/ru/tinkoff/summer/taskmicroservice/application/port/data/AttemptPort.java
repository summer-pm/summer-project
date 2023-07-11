package ru.tinkoff.summer.taskmicroservice.application.port.data;

import ru.tinkoff.summer.taskmicroservice.domain.Attempt;

public interface AttemptPort {
    Attempt save(Attempt attempt);
    Attempt getById(long id);
}
