package ru.tinkoff.summer.taskmicroservice.DTO;

import lombok.Data;
import ru.tinkoff.summer.taskshareddomain.Language;

@Data
public class SolutionData {
   private String code;
   private Language language;
    private long taskId;
   private long userId;
}
