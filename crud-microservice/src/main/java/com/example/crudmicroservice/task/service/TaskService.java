package com.example.crudmicroservice.task.service;

import com.example.crudmicroservice.task.model.Task;
import com.example.crudmicroservice.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public Task updateTask(Long taskId, Task task) {
        Task existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not Found"));

        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Page<Task> getTasks(int page, int pageSize, String sortBy) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(sortBy));
        return taskRepository.findAll(pageRequest);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
