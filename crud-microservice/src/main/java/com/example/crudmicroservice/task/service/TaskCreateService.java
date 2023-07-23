package com.example.crudmicroservice.task.service;

import com.example.crudmicroservice.task.dto.CreateTaskDTO;
import com.example.crudmicroservice.task.dto.TaskLangsDTO;
import com.example.crudmicroservice.task.dto.TestDTO;
import com.example.crudmicroservice.task.model.Language;
import com.example.crudmicroservice.task.model.Task;
import com.example.crudmicroservice.task.model.TasksLangs;
import com.example.crudmicroservice.task.model.Test;
import com.example.crudmicroservice.task.model.composite_key.TaskLangsId;
import com.example.crudmicroservice.task.repository.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskCreateService {
    Logger log = LoggerFactory.getLogger(TaskService.class);
    private final TaskRepository taskRepository;
    private final ExamplesRepository examplesRepository;
    private final LanguageRepository languageRepository;
    private final LangsTaskRepo langsTaskRepo;
    private final TaskParamsRepo taskParamsRepo;
    private final TestRepo testRepo;

    @Transactional
    public Task create(CreateTaskDTO dto) {
        log.info("Create task: {}", dto);

        var task = dto.getTaskMainInfo();
        task = taskRepository.save(task);
        var examples = dto.getExamplesEntity(task);
        examplesRepository.saveAll(examples);
        langsTaskRepo.saveAll(getTaskLangs(dto, task));
        var taskParams = dto.getTaskParamsEntity(task);
        task.setTaskParams(taskParams);
        if (dto.getTests() != null) {
            var tests = getTestsEntity(dto.getTests(), task);
            testRepo.saveAll(tests);
        }
        return taskRepository.save(task);
    }

    private List<TasksLangs> getTaskLangs(CreateTaskDTO dto, Task task) {
        var result = new ArrayList<TasksLangs>();
        for (TaskLangsDTO tlDto : dto.getTasksLangs()) {
            var taskLang = getTaskLang(task, tlDto);
            result.add(taskLang);
        }
        return result;
    }

    private TasksLangs getTaskLang(Task task, TaskLangsDTO tlDto) {
        var lang = getLanguage(tlDto);
        var taskLang = new TasksLangs();
        var id = new TaskLangsId(task.getTaskId(), lang.getLanguageId());
        taskLang.setLanguage(lang);
        taskLang.setTask(task);
        taskLang.setTaskLangsId(id);
        taskLang.setSolutionTemplate(tlDto.getSolutionTemplate());
        return taskLang;
    }

    private Language getLanguage(TaskLangsDTO tlDto) {
        var dbLanguage = languageRepository.getByLanguage(tlDto.getLanguage().toString());
        if (dbLanguage.isEmpty()) {
            var lang = new Language();
            lang.setLanguage(tlDto.getLanguage().toString());
            return languageRepository.save(lang);
        } else {
            return dbLanguage.get();
        }
    }

    public List<TestDTO> getTests(long taskId) {
        var taskOpt = taskRepository.findById(taskId);
        if (taskOpt.isEmpty())
            throw new IllegalArgumentException("Task " + taskId + "not exists");

        var result = new ArrayList<TestDTO>();
        for (Test test : taskOpt.get().getTests()) {
            result.add(new TestDTO(test.getTestId(), test.getInputParameters(), test.getOutputParameters()));
        }
        return result;
    }

    public void addTests(long id, List<TestDTO> dtos) {
        var taskOpt = taskRepository.findById(id);
        if (taskOpt.isEmpty())
            throw new IllegalArgumentException("Task " + id + "not exists");
        var task = taskOpt.get();
        var tests = getTestsEntity(dtos, task);
        testRepo.saveAll(tests);
    }

    private ArrayList<Test> getTestsEntity(List<TestDTO> dtos, Task task) {
        var tests = new ArrayList<Test>();
        for (TestDTO dto : dtos) {
            var test = new Test(null, task, dto.getInputParameters(), dto.getOutputParameters(), null);
            task.getTests().add(test);
            tests.add(test);
        }
        return tests;
    }


}
