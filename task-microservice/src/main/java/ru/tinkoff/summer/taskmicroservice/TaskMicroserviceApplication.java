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
        for (int i = 0; i < 200; i++) {
            var data = new SolutionData();
            if(i%2 == 0){
                 data.setCode("class Solution{" +
                    "public int add(int a, int b){return a+b;}}");
                   data.setLanguage(Language.JAVA);
            } else {
                data.setCode("class Solution:\n" +
                        "    def add(self, a,b):\n" +
                        "        return a+b");
                  data.setLanguage(Language.PYTHON);
            }

            data.setTaskId(1L);

            var id = useCase.execute(data);
        }

    }
}
