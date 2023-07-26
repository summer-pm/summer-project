package com.example.crudmicroservice.task.model;

import com.example.crudmicroservice.task.model.composite_key.InputTypesId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "input_types")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class InputTypes {

    @EmbeddedId
    private InputTypesId id;

    @ManyToOne
    @MapsId("taskId")
    @JoinColumn(name = "task_id", insertable = false, updatable = false)
    private TaskParams taskParams;

    @Column(name = "value")
    private String value;
}
