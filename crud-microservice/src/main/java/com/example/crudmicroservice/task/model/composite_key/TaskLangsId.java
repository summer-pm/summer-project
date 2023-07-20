package com.example.crudmicroservice.task.model.composite_key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class TaskLangsId implements Serializable {

    @Column(name = "task_id")
    private Long taskId;

    @Column(name = "language_id")
    private Long languageId;
}