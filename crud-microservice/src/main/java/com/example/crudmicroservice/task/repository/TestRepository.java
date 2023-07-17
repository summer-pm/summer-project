package com.example.crudmicroservice.task.repository;

import com.example.crudmicroservice.task.model.Test;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.jpa.repository.JpaRepository;

@ReadingConverter
public interface TestRepository extends JpaRepository<Test, Long> {
}
