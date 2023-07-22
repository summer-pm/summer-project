package ru.tinkoff.summer.taskshareddomain.task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskFrontendDTO {
    private String title;
    private Long id;
    private String level;
}
