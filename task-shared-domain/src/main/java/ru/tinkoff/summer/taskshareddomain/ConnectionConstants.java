package ru.tinkoff.summer.taskshareddomain;

public class ConnectionConstants {
    public static final String ATTEMPT_TOPIC_NAME = "attemptTopic";
    public static final int ATTEMPT_TOPIC_PARTITIONS = 6;
    public static final String RESULT_TOPIC_NAME = "resultTopic";
    public static final int RESULT_TOPIC_PARTITIONS = 3;
    public static final String EXECUTOR_GROUP_ID ="task-executor";
    public static final String SERVICE_GROUP_ID ="task-service";
    //TODO: Профили тут, task-microservice, docker-compose. для dev - localhost, в остальном - kafka
    public static final String BOOTSTRAP_SERVERS = "kafka:9092";
}
