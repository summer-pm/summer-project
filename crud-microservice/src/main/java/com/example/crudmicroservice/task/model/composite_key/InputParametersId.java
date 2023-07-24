package com.example.crudmicroservice.task.model.composite_key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class InputParametersId implements Serializable {

    @Column(name = "test_id")
    private Long testId;

    @Column(name = "value")
    private String value;
}