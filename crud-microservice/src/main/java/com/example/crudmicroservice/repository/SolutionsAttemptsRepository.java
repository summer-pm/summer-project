package com.example.crudmicroservice.repository;

import com.example.crudmicroservice.model.SolutionsAttempts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolutionsAttemptsRepository extends JpaRepository<SolutionsAttempts, Long> {
}
