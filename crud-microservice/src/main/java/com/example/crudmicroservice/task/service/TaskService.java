package com.example.crudmicroservice.task.service;


import com.example.crudmicroservice.task.model.*;
import com.example.crudmicroservice.task.model.criteria.TaskSearchCriteria;
import com.example.crudmicroservice.task.model.criteria.TaskSpecifications;
import com.example.crudmicroservice.task.repository.LangsTaskRepo;
import com.example.crudmicroservice.task.repository.LanguageRepository;
import com.example.crudmicroservice.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.tinkoff.summer.taskshareddomain.Language;
import ru.tinkoff.summer.taskshareddomain.Type;
import ru.tinkoff.summer.taskshareddomain.task.TaskParams;
import ru.tinkoff.summer.taskshareddomain.task.TaskTestCase;
import ru.tinkoff.summer.taskshareddomain.task.dto.ExampleDTO;
import ru.tinkoff.summer.taskshareddomain.task.dto.TaskDetailsFrontendDTO;
import ru.tinkoff.summer.taskshareddomain.task.dto.TaskForAttemptDTO;
import ru.tinkoff.summer.taskshareddomain.task.dto.TaskFrontendDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    Logger log = LoggerFactory.getLogger(TaskService.class);
    private final TaskRepository taskRepository;
    private final LanguageRepository languageRepository;
    private final LangsTaskRepo langsTaskRepo;


    public TaskDetailsFrontendDTO getTaskById(Long id) {
        log.info("Get task for front id: {}", id);
        var task = taskRepository.findById(id).orElse(null);
        if (task == null) return null;
        var dto = new TaskDetailsFrontendDTO();
        dto.setId(task.getTaskId());
        dto.setDescription(task.getDescription());
        dto.setTitle(task.getTitle());
        dto.setLevel(task.getLevel().toString());
        dto.setEditDate(task.getEditDate());
        dto.setVolumeLimit(task.getVolumeLimit());
        dto.setTimeLimit(task.getTimeLimit());
        dto.setCreationDate(task.getCreationDate());
        var examples = new ArrayList<ExampleDTO>();
        for (Examples example : task.getExamples()) {
            examples.add(new ExampleDTO(example.getInput(), example.getOutput(), example.getExplanation()));
        }
        var templates = new HashMap<Language,String>();
        for (TasksLangs tasksLangs:task.getTasksLangs()) {
            templates.put(Language.valueOf(tasksLangs.getLanguage().getLanguage()),tasksLangs.getSolutionTemplate() );
        }
        dto.setTemplates(templates);
        dto.setExamples(examples);
        log.info("Return task for front : {}", dto);
        return dto;
    }

    public Task updateTask(Long taskId, Task task) {
        Task existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not Found"));

        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Page<TaskFrontendDTO> getTasks(int page, int pageSize, String title, Level level, String sortBy, boolean publish) {
        PageRequest pageRequest = PageRequest.of(page, pageSize, Sort.by(sortBy));
        var query = new TaskSearchCriteria(title,level,publish);
        var spec = TaskSpecifications.searchByCriteria(query);
        var tasks = taskRepository.findAll(spec, pageRequest);
        return tasks.map(task -> new TaskFrontendDTO(task.getTitle(), task.getTaskId(), task.getLevel().toString()));
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public TaskForAttemptDTO getTaskDetailsById(Long id) {
        log.info("Get task details id :{}",id);
        var task = taskRepository.findById(id).orElse(null);
        if (task == null) return null;
        var dto = new TaskForAttemptDTO();
        dto.setMethodName(task.getNameOfMethod());
        dto.setVolumeLimitMs(task.getVolumeLimit());
        dto.setTimeLimitMs(task.getTimeLimit());
        dto.setId(task.getTaskId());


        var dtoTests = new HashSet<TaskTestCase>();
        for (Test test : task.getTests()) {
            var testCase = new TaskTestCase(test.getTestId(),test.getInputParameters(), test.getOutputParameters());
            dtoTests.add(testCase);
        }
        dto.setTaskTestCases(dtoTests);
        var testTaskParams = task.getTaskParams();
        List<Type> types = new ArrayList<>();
        for (String type : testTaskParams.getInputTypes()) {
            types.add(Type.valueOf(type));
        }
        TaskParams dtoTaskParams = new TaskParams(types, Type.valueOf(testTaskParams.getOutputType()));
        dto.setParams(dtoTaskParams);
        log.info("Get task details :{}",dto);
        return dto;
    }

    public void setPublish(Long id, boolean publish) {
        log.info("SetPublish task details id :{} publish {}",id, publish);
        var taskOpt = taskRepository.findById(id);
        if(taskOpt.isPresent()){
            var task = taskOpt.get();
            task.setIsPublish(publish);
            taskRepository.save(task);
        }
    }
}
