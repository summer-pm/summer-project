package ru.tinkoff.summer.taskexecutor;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.LoggerFactory;


import org.slf4j.Logger;
import ru.tinkoff.summer.taskexecutor.domain.executor.JavaExecutor;
import ru.tinkoff.summer.taskshareddomain.AttemptDTO;
import ru.tinkoff.summer.taskshareddomain.ConnectionConstants;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;


public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);
    private static final JavaExecutor javaExecutor = new JavaExecutor();

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "127.0.0.1:9092");
        properties.put("group.id", ConnectionConstants.EXECUTOR_GROUP_ID);
        properties.put("key.deserializer",  "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "ru.tinkoff.summer.taskexecutor.AttemptMapper");
        var consumer = new KafkaConsumer<String, AttemptDTO>(properties);
        consumer.subscribe(Collections.singleton(ConnectionConstants.ATTEMPT_TOPIC_NAME));
        Duration timeout = Duration.ofMillis(100);
        while (true) {
            ConsumerRecords<String, AttemptDTO> records = consumer.poll(timeout);
            for (ConsumerRecord<String, AttemptDTO> record : records) {
                log.info("topic {} , partition {}, offset {}, attempt {}, id? {}",
                        record.topic(), record.partition(), record.offset(), record.value(), record.key());
                try{
                System.err.println(javaExecutor.execute(record.value()));

                }
                catch(RuntimeException e){
                    System.err.println(e.getMessage());
                }
            }
        }

    }
}
