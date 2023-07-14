package ru.tinkoff.summer.taskshareddomain;

<<<<<<< HEAD
=======
import lombok.Getter;

>>>>>>> abe64b06c86ae596b1dcb093b0d2d008782c59ce
public enum Language {
    JAVA("java"),
    PYTHON("py");

    public final String extension;

    Language(String extension) {
        this.extension = extension;
    }

    // TODO: Говно, переделывай!!!!
    public String getTypeName(Type type) {
        switch (this) {
            case JAVA -> {
                return switch (type) {
                    case INTEGER -> "int";
                    case INTEGER_ARR -> "int[]";
                    default -> throw new IllegalArgumentException("Unsupported ParamType: " + type);
                };
            }
            case PYTHON -> {
                return switch (type) {
                    case INTEGER -> "int";
                    case INTEGER_ARR -> "List[int]";
                    default -> throw new IllegalArgumentException("Unsupported ParamType: " + type);
                };
            }
            default -> throw new IllegalArgumentException("Unsupported Language: " + this);
        }
    }
}
<<<<<<< HEAD
=======

>>>>>>> abe64b06c86ae596b1dcb093b0d2d008782c59ce
