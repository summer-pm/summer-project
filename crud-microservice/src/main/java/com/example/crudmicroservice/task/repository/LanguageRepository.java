package com.example.crudmicroservice.task.repository;

import com.example.crudmicroservice.task.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long > {
    Optional<Language> getByLanguage(String language);
}
