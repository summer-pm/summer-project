package ru.tinkoff.summer.taskservice.domain;

public enum Type {
    INTEGER("READ_INTEGER"),
    INTEGER_ARR("READ_INTEGER_ARR");

    public final String readFuncName;

    Type(String readFuncName) {
        this.readFuncName = readFuncName;
    }
}
