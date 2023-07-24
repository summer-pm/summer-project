package com.example.crudmicroservice.task.repository;

import com.example.crudmicroservice.task.model.SolutionsAttempts;

import com.example.crudmicroservice.task.model.Task;
import com.example.crudmicroservice.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tinkoff.summer.taskshareddomain.ExecutionStatus;

import java.util.Collection;
import java.util.List;

@Repository
public interface SolutionsAttemptsRepository extends JpaRepository<SolutionsAttempts, Long> {
    Page<SolutionsAttempts> findAllByTaskAndUserAndStatusIn(Task task, User user, Collection<String> status, Pageable pageable);
}
