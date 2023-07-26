package ru.tinkoff.summer.taskshareddomain.task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExampleDTO {
    private String input;
    private String output;
    private String explanation;
}
