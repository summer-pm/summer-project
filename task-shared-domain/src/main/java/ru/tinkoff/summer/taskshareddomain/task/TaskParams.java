package ru.tinkoff.summer.taskshareddomain.task;

<<<<<<< HEAD
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.tinkoff.summer.taskshareddomain.Type;


import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TaskParams {
    private List<Type> inputTypes;
    private Type outputType;
=======
import lombok.*;
import ru.tinkoff.summer.taskshareddomain.Type;


import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class TaskParams implements Serializable {
    private List<Type> inputTypes;
    private Type outputType;

    public TaskParams(List<Type> inputTypes, Type outputType) {
        this.inputTypes = inputTypes;
        this.outputType = outputType;
    }
>>>>>>> abe64b06c86ae596b1dcb093b0d2d008782c59ce
}
