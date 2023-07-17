package com.example.crudmicroservice.task.service;

import com.example.crudmicroservice.task.model.SolutionsAttempts;
import com.example.crudmicroservice.task.repository.SolutionsAttemptsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolutionsAttemptsService {
    private final SolutionsAttemptsRepository solutionsAttemptsRepository;

    @Autowired
    public SolutionsAttemptsService(SolutionsAttemptsRepository solutionsAttemptsRepository) {
        this.solutionsAttemptsRepository = solutionsAttemptsRepository;
    }

    public SolutionsAttempts saveSolutionsAttempts(SolutionsAttempts solutionsAttempts) {
        return solutionsAttemptsRepository.save(solutionsAttempts);
    }

    public SolutionsAttempts updateSolutionsAttempts(Long attemptId, SolutionsAttempts solutionsAttempts) {
        SolutionsAttempts existingAttempts = solutionsAttemptsRepository.findById(attemptId)
                .orElseThrow(() -> new IllegalArgumentException("SolutionsAttempts not found"));
        solutionsAttempts.setAttemptId(attemptId);
        return solutionsAttemptsRepository.save(existingAttempts);
    }

    public SolutionsAttempts getSolutionsAttemptsById(Long attemptId) {
        return solutionsAttemptsRepository.findById(attemptId).orElse(null);
    }

    public List<SolutionsAttempts> getAllSolutionsAttempts() {
        return solutionsAttemptsRepository.findAll();
    }

    public void deleteSolutionsAttempts(Long attemptId) {
        solutionsAttemptsRepository.deleteById(attemptId);
    }

}
