package ru.tinkoff.summer.taskexecutor.messaging;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import ru.tinkoff.summer.taskshareddomain.ConnectionConstants;
import ru.tinkoff.summer.taskshareddomain.TotalExecutionResult;

import java.util.Properties;

public class ResultProducer {
    KafkaProducer<String, TotalExecutionResult> producer;
    public ResultProducer() {
        producer = getProducer();

    }


    public void send(TotalExecutionResult result) {
         var resultRecord = new ProducerRecord<String,TotalExecutionResult>(ConnectionConstants.RESULT_TOPIC_NAME, result.getAttemptId().toString(), result);
        producer.send(resultRecord);
    }

     private static KafkaProducer<String, TotalExecutionResult> getProducer() {
        Properties propertiesProducer = new Properties();
        propertiesProducer.put("bootstrap.servers", ConnectionConstants.BOOTSTRAP_SERVERS);
        propertiesProducer.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        propertiesProducer.put("value.serializer", "ru.tinkoff.summer.taskexecutor.mapper.TotalExecutionResultMapper");
        var producer = new KafkaProducer<String, TotalExecutionResult>(propertiesProducer);
        return producer;
    }
}
