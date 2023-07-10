package ru.tinkoff.summer.taskmicroservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Value("${topic.outcoming.name}")
    private String outcomingTopic;
    @Value("${topic.outcoming.partitions}")
    private int outcomingPartitions;

     @Value("${topic.incoming.name}")
    private String incomingTopic;
    @Value("${topic.incoming.partitions}")
    private int incomingPartitions;
    @Bean
    public NewTopic attemptTopic(){
        return TopicBuilder.name(outcomingTopic).partitions(outcomingPartitions).build();
    }
    @Bean
    public NewTopic resultTopic(){
        return TopicBuilder.name(incomingTopic).partitions(incomingPartitions).build();
    }
}
