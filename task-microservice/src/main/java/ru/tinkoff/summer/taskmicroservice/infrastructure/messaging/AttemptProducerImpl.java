package ru.tinkoff.summer.taskmicroservice.infrastructure.messaging;

import lombok.RequiredArgsConstructor;


import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import ru.tinkoff.summer.taskmicroservice.application.port.messaging.AttemptProducer;
import ru.tinkoff.summer.taskshareddomain.AttemptDTO;
import ru.tinkoff.summer.taskshareddomain.ConnectionConstants;

@Component
@RequiredArgsConstructor
public class AttemptProducerImpl implements AttemptProducer {

    private final KafkaTemplate<String, AttemptDTO> kafkaTemplate;

    @Override
    public void publishForExecute(AttemptDTO dto) {
        kafkaTemplate.send(ConnectionConstants.ATTEMPT_TOPIC_NAME, dto.getId().toString(),dto );
    }
}
