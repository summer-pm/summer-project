package com.example.crudmicroservice.task.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

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
    @JsonBackReference
    private Task task;

    @ElementCollection
    @CollectionTable(name = "input_parameters", joinColumns = @JoinColumn(name = "test_id"))
    @Column(name = "value")
    private Set<String> inputParameters;
    private String outputParameters;

}
