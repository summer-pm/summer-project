package ru.tinkoff.summer.taskmicroservice.infrastructure.messaging;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.tinkoff.summer.taskshareddomain.AttemptDTO;
import static ru.tinkoff.summer.taskshareddomain.ConnectionConstants.*;

@Service
public class ResultListener {



    @KafkaListener(topics = RESULT_TOPIC_NAME,
                    groupId = SERVICE_GROUP_ID)
    public void consume(AttemptDTO message){
        System.err.println(message.getCode());
    }
}
