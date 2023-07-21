package com.example.crudmicroservice.task.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringExclude;

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

    private String language;
    @OneToMany(mappedBy = "language")
    @ToStringExclude
    private List<SolutionsAttempts> attemptsList;

    @OneToMany(mappedBy = "language")
    @ToStringExclude
    private List<TasksLangs> tasksLangs;
}
