package ru.tinkoff.summer.taskshareddomain.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {

//    private List<SolutionsAttempts> solutionsAttempts;
//    private List<Test> tests;
//    private List<Examples> examples;
//    private TaskParams taskParams;
//    private List<TasksLangs> tasksLangs;

    private String title;
    private String level;
    private String description;
    private String nameOfMethod;
    private Integer timeLimit;
    private Long volumeLimit;
    private LocalDateTime creationDate;
    private LocalDateTime editDate;
}
