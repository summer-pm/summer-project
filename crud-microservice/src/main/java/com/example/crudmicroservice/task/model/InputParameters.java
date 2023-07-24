package com.example.crudmicroservice.task.model;

import com.example.crudmicroservice.task.model.composite_key.InputParametersId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "input_parameters")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputParameters {

    @EmbeddedId
    private InputParametersId id;

    @ManyToOne
    @MapsId("testId")
    @JoinColumn(name = "test_id", insertable = false, updatable = false)
    private Test test;
}
