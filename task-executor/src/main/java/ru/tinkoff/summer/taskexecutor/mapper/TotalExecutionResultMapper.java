package ru.tinkoff.summer.taskexecutor.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;
import ru.tinkoff.summer.taskshareddomain.TotalExecutionResult;

import java.io.IOException;
import java.util.Map;

public class TotalExecutionResultMapper implements Serializer<TotalExecutionResult>, Deserializer<TotalExecutionResult> {
    private final ObjectMapper objectMapper;
    public TotalExecutionResultMapper() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        this.objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
    }

    @Override
    public TotalExecutionResult deserialize(String topic, byte[] data) {
         try {
            return objectMapper.readValue(data, TotalExecutionResult.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public byte[] serialize(String topic, TotalExecutionResult data) {
         try {
            return objectMapper.writeValueAsBytes(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void close() {
        Serializer.super.close();
    }
}
