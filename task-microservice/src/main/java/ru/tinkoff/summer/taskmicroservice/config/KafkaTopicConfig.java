package ru.tinkoff.summer.taskmicroservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;




import static ru.tinkoff.summer.taskshareddomain.ConnectionConstants.*;

@Configuration
public class KafkaTopicConfig {

    Logger logger = LoggerFactory.getLogger(KafkaTopicConfig.class);
    @Bean
    public NewTopic attemptTopic(){
        logger.error("Atttempts topics = {}" ,ATTEMPT_TOPIC_PARTITIONS);
        return TopicBuilder.name(ATTEMPT_TOPIC_NAME).partitions(ATTEMPT_TOPIC_PARTITIONS).build();
    }
    @Bean
    public NewTopic resultTopic(){
        logger.error("Result topics = {}" ,RESULT_TOPIC_PARTITIONS);
        return TopicBuilder.name(RESULT_TOPIC_NAME).partitions(RESULT_TOPIC_PARTITIONS).build();
    }
}
