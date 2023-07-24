package com.example.crudmicroservice.task.repository;

import com.example.crudmicroservice.task.model.TaskParams;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskParamsRepo extends JpaRepository<TaskParams,Long> {
}
