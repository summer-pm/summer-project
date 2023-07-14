package com.example.crudmicroservice.task.repository;

import com.example.crudmicroservice.task.model.SolutionsAttempts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolutionsAttemptsRepository extends JpaRepository<SolutionsAttempts, Long> {
}
