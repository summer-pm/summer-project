package com.example.crudmicroservice.task.model.criteria;

import com.example.crudmicroservice.task.model.Level;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskSearchCriteria {
    private String title;
    private Level level;
    private boolean publish;
}
