package ru.tinkoff.summer.taskshareddomain;







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
                    case STRING -> "String";
                    default -> throw new IllegalArgumentException("Unsupported ParamType: " + type);
                };
            }
            case PYTHON -> {
                return switch (type) {
                    case INTEGER -> "int";
                    case INTEGER_ARR -> "List[int]";
                    case STRING -> "str";
                    default -> throw new IllegalArgumentException("Unsupported ParamType: " + type);
                };
            }
            default -> throw new IllegalArgumentException("Unsupported Language: " + this);
        }
    }
}

