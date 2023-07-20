package com.example.crudmicroservice.task.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "languages")
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id")
    private Long languageId;

    @OneToMany(mappedBy = "language")
    private List<SolutionsAttempts> attemptsList;

    @OneToMany(mappedBy = "language")
    private List<TasksLangs> tasksLangs;
}
