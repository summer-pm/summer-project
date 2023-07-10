package ru.tinkoff.summer.taskmicroservice.infrastructure.messaging;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.tinkoff.summer.taskshareddomain.AttemptDTO;
import ru.tinkoff.summer.taskshareddomain.TotalExecutionResult;

import static ru.tinkoff.summer.taskshareddomain.ConnectionConstants.*;

@Service
public class ResultListener {

  private static final Logger log = LoggerFactory.getLogger(ResultListener.class);

    @KafkaListener(topics = RESULT_TOPIC_NAME,
                    groupId = SERVICE_GROUP_ID)
    public void consume(TotalExecutionResult message){

        log.info(message.toString());
    }
}
