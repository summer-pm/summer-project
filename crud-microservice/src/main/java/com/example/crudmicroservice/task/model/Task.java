package com.example.crudmicroservice.task.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringExclude;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @ToStringExclude
    private List<SolutionsAttempts> solutionsAttempts;
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    @JsonManagedReference
    @ToStringExclude
    private List<Test> tests = new ArrayList<>();

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    @JsonManagedReference
    @ToStringExclude
    private List<Examples> examples;

    @OneToOne(mappedBy = "task", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @JsonManagedReference
    @ToStringExclude
    private TaskParams taskParams;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    @JsonManagedReference
    @ToStringExclude
    private List<TasksLangs> tasksLangs;

    private String title;
    @Enumerated(EnumType.STRING)
    private Level level;
    private String description;
    private String nameOfMethod;
    private Integer timeLimit;
    private Long volumeLimit;
    private LocalDateTime creationDate;
    private LocalDateTime editDate;

}
