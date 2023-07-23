package ru.tinkoff.summer.taskshareddomain;

public enum Type {
    INTEGER("READ_INTEGER"),
    INTEGER_ARR("READ_INTEGER_ARR"),
    STRING("READ_STRING");

    public final String readFuncName;

    Type(String readFuncName) {
        this.readFuncName = readFuncName;
    }
}
