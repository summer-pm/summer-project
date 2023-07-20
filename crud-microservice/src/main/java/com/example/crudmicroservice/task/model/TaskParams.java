package com.example.crudmicroservice.task.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "task_params")
public class TaskParams {

    @Id
    private Long task_id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "task_id")
    @JsonBackReference
    private Task task;

    @ElementCollection
    @CollectionTable(name = "input_types", joinColumns = @JoinColumn(name = "task_id"))
    @OrderColumn(name = "input_types_order")
    @Column(name = "value")
    private List<String> inputTypes;
    private String outputType;
}