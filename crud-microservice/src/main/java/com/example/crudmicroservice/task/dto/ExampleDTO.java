package com.example.crudmicroservice.task.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExampleDTO {
    @NotNull(message = "input не должен быть пустым")
    @NotBlank(message = "input не должен быть пустым")
    private String input;
    @NotNull(message = "output не должен быть пустым")
    @NotBlank(message = "output не должен быть пустым")
    private String output;
    private String explanation;
}
