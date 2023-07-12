package ru.tinkoff.summer.taskmicroservice.infrastructure.messaging;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import ru.tinkoff.summer.taskmicroservice.application.port.messaging.ResultConsumer;
import ru.tinkoff.summer.taskmicroservice.application.usecase.ApplyResultUseCase;
import ru.tinkoff.summer.taskshareddomain.TotalExecutionResult;

import java.time.Duration;
import java.time.LocalTime;

import static ru.tinkoff.summer.taskshareddomain.ConnectionConstants.*;

@Service
@RequiredArgsConstructor
public class ResultConsumerImpl implements ResultConsumer {
    private LocalTime start;
    private LocalTime end;
    boolean isStarted = false;
    int count = 0;
    private static final Logger log = LoggerFactory.getLogger(ResultConsumerImpl.class);
    private final ApplyResultUseCase useCase;
    @KafkaListener(topics = RESULT_TOPIC_NAME,
            groupId = SERVICE_GROUP_ID)
    public void consume(TotalExecutionResult result) {
        if(!isStarted){
            start = LocalTime.now();
        }
        isStarted = true;
        count++;
        log.info("Get {}",result.getAttemptId());
        useCase.apply(result);
        if(count == 199){
            end = LocalTime.now();
            Duration duration = Duration.between(start,end);
            log.error("Finish {}", duration.toSeconds());
        }
    }
}
