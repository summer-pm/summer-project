package ru.tinkoff.summer.taskmicroservice.infrastructure.messaging;

import lombok.RequiredArgsConstructor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import ru.tinkoff.summer.taskmicroservice.application.port.messaging.AttemptProducer;
import ru.tinkoff.summer.taskshareddomain.AttemptDTO;
import ru.tinkoff.summer.taskshareddomain.ConnectionConstants;

@Component
@RequiredArgsConstructor
public class AttemptProducerImpl implements AttemptProducer {
    private static final Logger log = LoggerFactory.getLogger(AttemptProducerImpl.class);

    private final KafkaTemplate<String, AttemptDTO> kafkaTemplate;

    @Override
    public void publishForExecute(AttemptDTO dto) {
        log.info("Sending {}",dto.getId());
        kafkaTemplate.send(ConnectionConstants.ATTEMPT_TOPIC_NAME, dto.getId().toString(),dto );
    }
}
