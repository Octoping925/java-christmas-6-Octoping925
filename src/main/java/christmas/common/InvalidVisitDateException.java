package christmas.common;

public class InvalidVisitDateException extends ChristmasEventException {
    public InvalidVisitDateException() {
        super("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }
}
