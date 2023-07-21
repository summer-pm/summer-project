package ru.tinkoff.summer.taskmicroservice.infrastructure.messaging;

import lombok.RequiredArgsConstructor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import ru.tinkoff.summer.taskmicroservice.application.port.messaging.AttemptProducer;
import ru.tinkoff.summer.taskshareddomain.AttemptForExecuteDTO;
import ru.tinkoff.summer.taskshareddomain.ConnectionConstants;

@Component
@RequiredArgsConstructor
public class AttemptProducerImpl implements AttemptProducer {
    private static final Logger log = LoggerFactory.getLogger(AttemptProducerImpl.class);

    private final KafkaTemplate<String, AttemptForExecuteDTO> kafkaTemplate;

    @Override
    public void publishForExecute(AttemptForExecuteDTO dto) {
        log.info("Sending to executor {}",dto);
        kafkaTemplate.send(ConnectionConstants.ATTEMPT_TOPIC_NAME, dto.getId().toString(),dto );
    }
}
