package ru.tinkoff.summer.taskservice.domain;

import java.io.IOException;

//TODO: Название подумать
public class ProcessWrapper {

    public void start(String... commands) throws IOException {
        ProcessBuilder compileProcessBuilder = new ProcessBuilder(commands);
            Process compileProcess = compileProcessBuilder.start();
    }

}
