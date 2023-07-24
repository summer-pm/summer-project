package com.example.crudmicroservice.task.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity(name = "tests")
@Getter
@Setter
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
    @OrderColumn(name = "input_parameters_order")
    @Column(name = "value")
    private List<String> inputParameters;
    private String outputParameters;
    @OneToMany(mappedBy = "failedTest")
    @JsonIgnore
    private List<SolutionsAttempts> solutionsAttempts;

}
