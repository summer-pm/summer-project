package com.example.crudmicroservice.task.repository;

import com.example.crudmicroservice.task.model.TasksLangs;
import com.example.crudmicroservice.task.model.composite_key.TaskLangsId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LangsTaskRepo extends JpaRepository<TasksLangs, TaskLangsId> {
}
