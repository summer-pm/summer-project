package ru.tinkoff.summer.taskexecutor.messaging;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tinkoff.summer.taskexecutor.Main;
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

public class AttemptListener {
    private static final Logger log = LoggerFactory.getLogger(AttemptListener.class);
    private static final int MAX_THREADS = Runtime.getRuntime().availableProcessors() / 2;
    private static final List<LanguageExecutor> EXECUTORS = List.of(new JavaExecutor(), new PythonExecutor());
    private final ResultProducer producer;
    KafkaConsumer<String, AttemptDTO> consumer;

    public AttemptListener() {
        producer = new ResultProducer();
        consumer = getConsumer();
        consumer.subscribe(Collections.singleton(ConnectionConstants.ATTEMPT_TOPIC_NAME));
        startListening();
    }

    private void startListening() {
        ExecutorService threadExecutor = Executors.newFixedThreadPool(MAX_THREADS);
        Duration timeout = Duration.ofMillis(100);
        while (true) {
            ConsumerRecords<String, AttemptDTO> records = consumer.poll(timeout);
            for (ConsumerRecord<String, AttemptDTO> record : records) {
                threadExecutor.execute(() -> {
                    TotalExecutionResult result;
                    AttemptDTO attempt = record.value();
                    LanguageExecutor codeExecutor = getCodeExecutor(attempt);
                    try {
                        log.info("Start {}", record.key());
                        result = new TotalExecutionResult(attempt, codeExecutor.execute(attempt));
                        log.debug(result.toString());
                    } catch (RuntimeException e) {
                        result = new TotalExecutionResult(attempt, e.getMessage());
                        log.warn(result.toString());
                    }
                    log.info("Finish {}", record.key());
                    producer.send(result);
                });

            }

        }
    }


    private static LanguageExecutor getCodeExecutor(AttemptDTO attempt) {
        return EXECUTORS.stream().filter(e -> e.getLanguage().equals(attempt.getLanguage())).findFirst().get();
    }

    private static KafkaConsumer<String, AttemptDTO> getConsumer() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", ConnectionConstants.BOOTSTRAP_SERVERS);
        properties.put("group.id", ConnectionConstants.EXECUTOR_GROUP_ID);
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "ru.tinkoff.summer.taskexecutor.mapper.AttemptMapper");
        return new KafkaConsumer<>(properties);
    }
}
