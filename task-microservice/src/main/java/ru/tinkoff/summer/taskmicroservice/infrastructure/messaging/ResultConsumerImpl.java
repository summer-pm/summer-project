package ru.tinkoff.summer.taskmicroservice.infrastructure.messaging;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import ru.tinkoff.summer.taskmicroservice.application.port.messaging.ResultConsumer;
import ru.tinkoff.summer.taskmicroservice.application.usecase.ApplyResultUseCase;
import ru.tinkoff.summer.taskshareddomain.TotalExecutionResult;

import static ru.tinkoff.summer.taskshareddomain.ConnectionConstants.*;

@Service
@RequiredArgsConstructor
public class ResultConsumerImpl implements ResultConsumer {

    private static final Logger log = LoggerFactory.getLogger(ResultConsumerImpl.class);
    private final ApplyResultUseCase useCase;
    @KafkaListener(topics = RESULT_TOPIC_NAME,
            groupId = SERVICE_GROUP_ID)
    public void consume(TotalExecutionResult result) {

        log.info(result.toString());
    }
}
