package com.example.crudmicroservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;
    @OneToMany(mappedBy = "task")
    @JsonManagedReference("task-attempts")
    private List<SolutionsAttempts> solutionsAttempts;
    @OneToMany(mappedBy = "task")
    private List<Test> tests;
    private String title;
    private String level;
    private String description;
    private String inputParameters;
    private String typeOfInputParameters;
    private String outputParameters;
    private String typeOfOutputParameters;
    private String nameOfMethod;
    private Long TimeOfMethod;
    private Long volumeLimit;
    private LocalDateTime creationDate;
    private LocalDateTime editDate;

}
