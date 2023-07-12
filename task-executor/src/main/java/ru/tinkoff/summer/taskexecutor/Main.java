package ru.tinkoff.summer.taskexecutor;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.LoggerFactory;


import org.slf4j.Logger;
import ru.tinkoff.summer.taskexecutor.domain.executor.JavaExecutor;
import ru.tinkoff.summer.taskexecutor.domain.executor.LanguageExecutor;
import ru.tinkoff.summer.taskexecutor.domain.executor.PythonExecutor;
import ru.tinkoff.summer.taskshareddomain.AttemptDTO;
import ru.tinkoff.summer.taskshareddomain.ConnectionConstants;
import ru.tinkoff.summer.taskshareddomain.TotalExecutionResult;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);
    private static final LanguageExecutor javaExecutor = new JavaExecutor();
    private static final LanguageExecutor pythonExecutor = new PythonExecutor();
    private static List<LanguageExecutor> EXECUTORS = List.of(javaExecutor, pythonExecutor);

    private static final int MAX_THREADS = Runtime.getRuntime().availableProcessors()/2;
    public static void main(String[] args) {
         ExecutorService threadExecutor = Executors.newFixedThreadPool(MAX_THREADS);
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("group.id", ConnectionConstants.EXECUTOR_GROUP_ID);
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "ru.tinkoff.summer.taskexecutor.AttemptMapper");
        var consumer = new KafkaConsumer<String, AttemptDTO>(properties);
        consumer.subscribe(Collections.singleton(ConnectionConstants.ATTEMPT_TOPIC_NAME));
        Duration timeout = Duration.ofMillis(100);


        Properties propertiesProducer = new Properties();
        propertiesProducer.put("bootstrap.servers", "localhost:9092");
        propertiesProducer.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        propertiesProducer.put("value.serializer", "ru.tinkoff.summer.taskexecutor.TotalExecutionResultMapper");
        var producer = new KafkaProducer<String, TotalExecutionResult>(propertiesProducer);
        while (true) {
            ConsumerRecords<String, AttemptDTO> records = consumer.poll(timeout);
            for (ConsumerRecord<String, AttemptDTO> record : records) {


                threadExecutor.execute(() -> {
                    TotalExecutionResult result;
                    AttemptDTO attempt = record.value();
                    var codeExecutor = EXECUTORS.stream().filter(e -> e.getLanguage().equals(attempt.getLanguage())).findFirst().get();
                    try {
                        log.info("Start {}",record.key());
                        result = new TotalExecutionResult(attempt, codeExecutor.execute(attempt));
                        log.debug(result.toString());
                    } catch (RuntimeException e) {
                        result = new TotalExecutionResult(attempt, e.getMessage());
                        log.warn(result.toString());
                    }
                    log.info("Finish {}",record.key());
                    var resultRecord = new ProducerRecord<>(ConnectionConstants.RESULT_TOPIC_NAME, attempt.getId().toString(), result);

                    producer.send(resultRecord);
                });

            }

        }


    }
}
