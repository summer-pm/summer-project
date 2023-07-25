package com.example.crudmicroservice.task.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestDTO {
    private long id;

     @NotNull(message = "inputParameters не должен быть пустым")
    @NotEmpty(message = "inputParameters не должен быть пустым")
    private List<String> inputParameters;
     @NotNull(message = "outputParameters не должен быть пустым")
    @NotBlank(message = "outputParameters не должен быть пустым")
    private String outputParameters;


}
