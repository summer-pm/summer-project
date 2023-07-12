package com.example.crudmicroservice.repository;

import com.example.crudmicroservice.model.Test;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.jpa.repository.JpaRepository;

@ReadingConverter
public interface TestRepository extends JpaRepository<Test, Long> {
}
