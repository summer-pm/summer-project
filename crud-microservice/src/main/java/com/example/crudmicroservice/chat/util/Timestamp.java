package com.example.crudmicroservice.chat.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Timestamp {
    private static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    // TODO: Подобрать паттерн хранения даты
    public static LocalDateTime get() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_FORMAT);
        return LocalDateTime.now();
    }
}
