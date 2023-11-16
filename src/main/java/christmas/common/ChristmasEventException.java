package christmas.common;

public abstract class ChristmasEventException extends IllegalArgumentException {
    private static final String ERROR_PREFIX = "[ERROR] ";

    protected ChristmasEventException(String errorMessage) {
        super(ERROR_PREFIX + errorMessage);
    }
}
