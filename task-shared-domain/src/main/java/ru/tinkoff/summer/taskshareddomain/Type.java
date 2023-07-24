package ru.tinkoff.summer.taskshareddomain;

public enum Type {
    INTEGER("READ_INTEGER"),
    INTEGER_ARR("READ_INTEGER_ARR"),
    STRING("READ_STRING"),
    STRING_ARR("READ_STRING_ARR"),
    BOOLEAN("READ_BOOLEAN"),;

    public final String readFuncName;

    Type(String readFuncName) {
        this.readFuncName = readFuncName;
    }
}
