package com.example.crudmicroservice.task.repository;

import com.example.crudmicroservice.task.model.Examples;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamplesRepository extends JpaRepository<Examples, Long> {
}
