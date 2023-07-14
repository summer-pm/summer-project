package com.example.crudmicroservice.task.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "tests")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tests")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long testId;
    @ManyToOne
    @JoinColumn(name = "task_id")
    @JsonBackReference("task-attempts")
    private Task task;
    private String inputParameters;
    private String outputParameters;

}
