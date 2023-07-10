package ru.tinkoff.summer.taskmicroservice.application.port.jpa;

import ru.tinkoff.summer.taskmicroservice.domain.Task;

import java.util.Optional;

public interface TaskPort {
   Task getTask(long taskId);
}
