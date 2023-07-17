package ru.tinkoff.summer.taskmicroservice.application.port.data;

import ru.tinkoff.summer.taskmicroservice.domain.Task;

public interface TaskPort {
   Task getTask(long taskId);
}
