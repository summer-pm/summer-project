package com.example.crudmicroservice.task.dto;

import com.example.crudmicroservice.task.model.Level;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskAttemptsDTO {
    private long taskID;
    private String title;
    private Level level;
    private Page<AttemptsPreviewDTO> attempts;
}
