package com.example.crudmicroservice.task.model;

import com.example.crudmicroservice.task.model.composite_key.TaskLangsId;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tasks_langs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TasksLangs {

    @EmbeddedId
    private TaskLangsId taskLangsId;

    @ManyToOne
    @MapsId("taskId")
    @JoinColumn(name = "task_id", insertable = false, updatable = false)
    @JsonBackReference
    private Task task;

    @ManyToOne
    @MapsId("languageId")
    @JoinColumn(name = "language_id", insertable = false, updatable = false)
    @JsonBackReference
    private Language language;

    private String solutionTemplate;
}
