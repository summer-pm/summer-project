package ru.tinkoff.summer.taskexecutor.domain.exceptions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaCompileException extends RuntimeException {
    private String sourceMessage;

    public JavaCompileException(String message) {
        super(message);
        this.sourceMessage = message;
    }

    @Override
    public String getMessage() {
        var resultBuilder = new StringBuilder();

        String[] lines = sourceMessage.split("\n");

        for (String line : lines) {
          if(line.contains("error") && !line.contains("errors")){
             resultBuilder.append( line.substring(line.indexOf("error")));
          } else {
              resultBuilder.append(line);
          }
          resultBuilder.append("\n");
        }
        return resultBuilder.toString();
    }
}
