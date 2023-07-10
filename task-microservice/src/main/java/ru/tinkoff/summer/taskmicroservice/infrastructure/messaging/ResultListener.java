package ru.tinkoff.summer.taskmicroservice.infrastructure.messaging;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.tinkoff.summer.taskmicroservice.DTO.AttemptDTO;

@Service
public class ResultListener {

    @Value("${topic.incoming.name}")
    public static final String TOPIC_NAME = "${topic.outcoming.name}";
     @Value("${spring.kafka.consumer.group-id}")
    public static final String GROUP_ID = "${spring.kafka.consumer.group-id}";;

    @KafkaListener(topics = TOPIC_NAME,
                    groupId = GROUP_ID)
    public void consume(AttemptDTO message){
        System.err.println(message.getCode());
    }
}
