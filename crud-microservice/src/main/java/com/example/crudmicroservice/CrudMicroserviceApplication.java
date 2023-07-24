package com.example.crudmicroservice;

import com.example.crudmicroservice.task.model.*;
import com.example.crudmicroservice.task.model.composite_key.TaskLangsId;
import com.example.crudmicroservice.task.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@SpringBootApplication
@EnableDiscoveryClient
@RequiredArgsConstructor
public class CrudMicroserviceApplication implements ApplicationRunner {
    private final LanguageRepository languageRepository;
    private final ExamplesRepository examplesRepository;
    private final TaskRepository taskRepository;
    private final LangsTaskRepo langsTaskRepository;
    private final TaskParamsRepo taskParamsRepo;
    private final TestRepo testRepo;

    public static void main(String[] args) {
        SpringApplication.run(CrudMicroserviceApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {


    }
}
