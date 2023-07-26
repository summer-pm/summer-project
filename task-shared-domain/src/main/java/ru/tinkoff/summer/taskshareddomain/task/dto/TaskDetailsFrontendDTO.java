package ru.tinkoff.summer.taskshareddomain.task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.tinkoff.summer.taskshareddomain.Language;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDetailsFrontendDTO {

    private List<ExampleDTO> examples;
    private Map<Language,String> templates;
    private Long id;
    private String title;
    private String level;
    private String description;
    private Integer timeLimit;
    private Long volumeLimit;
    private LocalDateTime creationDate;
    private LocalDateTime editDate;
}
