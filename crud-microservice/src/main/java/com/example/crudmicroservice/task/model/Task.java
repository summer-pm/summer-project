package com.example.crudmicroservice.task.model;

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
    @Column(name = "task_id")
    private Long taskId;
    @OneToMany(mappedBy = "task")
    @JsonManagedReference("task-attempts")
    private List<SolutionsAttempts> solutionsAttempts;
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Test> tests;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Examples> examples;

    @OneToOne(mappedBy = "task", cascade = CascadeType.ALL)
    @JsonManagedReference
    private TaskParams taskParams;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<TasksLangs> tasksLangs;

    private String title;
    private String level;
    private String description;
    private String nameOfMethod;
    private Integer timeLimit;
    private Long volumeLimit;
    private LocalDateTime creationDate;
    private LocalDateTime editDate;

}
