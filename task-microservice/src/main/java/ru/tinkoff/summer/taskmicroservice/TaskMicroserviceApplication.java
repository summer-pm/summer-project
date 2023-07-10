package ru.tinkoff.summer.taskmicroservice;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.tinkoff.summer.taskmicroservice.DTO.SolutionData;
import ru.tinkoff.summer.taskmicroservice.application.usecase.SendSolutionUseCase;
import ru.tinkoff.summer.taskshareddomain.Language;

@SpringBootApplication
public class TaskMicroserviceApplication implements ApplicationRunner {

    private final SendSolutionUseCase useCase;

    public TaskMicroserviceApplication(SendSolutionUseCase useCase) {
        this.useCase = useCase;
    }

    public static void main(String[] args) {
        SpringApplication.run(TaskMicroserviceApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        var data = new SolutionData();
        data.setCode("class Solution{" +
                "public void add(int a, int b){return b}}");
        data.setTaskId(1L);
        data.setLanguage(Language.JAVA);
        var id = useCase.execute(data);
    }
}
