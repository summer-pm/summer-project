package com.example.crudmicroservice.task.dto;

import com.example.crudmicroservice.task.model.Examples;
import com.example.crudmicroservice.task.model.Level;
import com.example.crudmicroservice.task.model.Task;
import com.example.crudmicroservice.task.model.TaskParams;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTaskDTO {

    @NotNull(message = "Title не должен быть пустым")
    @NotBlank(message = "Title не должен быть пустым")
    private String title;
    private Level level;
    @NotNull(message = "description не должен быть пустым")
    @NotBlank(message = "description не должен быть пустым")
    private String description;
    @NotNull(message = "nameOfMethod не должен быть пустым")
    @NotBlank(message = "nameOfMethod не должен быть пустым")
    private String nameOfMethod;
    @Min(10)
    @NotNull(message = "timeLimit не должен быть пустым")
    private Integer timeLimit;
    @Min(1)
    @NotNull(message = "volumeLimit не должен быть пустым")
    private Long volumeLimit;
    @NotNull(message = "taskParams не должен быть пустым")
    TaskParamsDTO taskParams;
    @NotNull(message = "examples не должен быть пустым")
    @NotEmpty(message = "examples не должен быть пустым")
    List<ExampleDTO> examples;
    @NotNull(message = "taskLangs не должен быть пустым")
    @NotEmpty(message = "taskLangs не должен быть пустым")
    List<TaskLangsDTO> tasksLangs;

    List<TestDTO> tests;

    public Task getTaskMainInfo() {
        var task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setLevel(level);
        task.setNameOfMethod(nameOfMethod);
        task.setVolumeLimit(volumeLimit);
        task.setTimeLimit(timeLimit);
        task.setCreationDate(LocalDateTime.now());
        task.setEditDate(LocalDateTime.now());
        return task;
    }

    public List<Examples> getExamplesEntity(Task task) {
        var result = new ArrayList<Examples>();
        for (ExampleDTO dto : examples) {
            var entity = new Examples(
                    null, task, dto.getInput(), dto.getOutput(),
                    dto.getExplanation()
            );
            result.add(entity);
        }
        return result;
    }

    public TaskParams getTaskParamsEntity(Task task) {
        var entity =  new TaskParams();
        entity.setTask(task);
        entity.setTask_id(task.getTaskId());
        entity.setOutputType(taskParams.getOutputType().toString());
        entity.setInputTypes(taskParams.getInputTypes().stream().map(Enum::toString).collect(Collectors.toList()));

        return entity;
    }
}
