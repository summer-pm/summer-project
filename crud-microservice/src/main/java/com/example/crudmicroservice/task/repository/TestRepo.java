package com.example.crudmicroservice.task.repository;

import com.example.crudmicroservice.task.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepo extends JpaRepository<Test,Long> {
    void deleteByTaskTaskId(long taskId);
}
