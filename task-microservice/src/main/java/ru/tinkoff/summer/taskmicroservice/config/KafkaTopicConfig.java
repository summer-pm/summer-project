package ru.tinkoff.summer.taskmicroservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;


import static ru.tinkoff.summer.taskshareddomain.ConnectionConstants.*;

@Configuration
public class KafkaTopicConfig {


    @Bean
    public NewTopic attemptTopic(){
        return TopicBuilder.name(ATTEMPT_TOPIC_NAME).partitions(ATTEMPT_TOPIC_PARTITIONS).build();
    }
    @Bean
    public NewTopic resultTopic(){
        return TopicBuilder.name(RESULT_TOPIC_NAME).partitions(RESULT_TOPIC_PARTITIONS).build();
    }
}
