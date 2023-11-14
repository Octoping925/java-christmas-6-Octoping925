package christmas.common;

public class VisitDateNotNumberException extends ChristmasEventException {
    public VisitDateNotNumberException() {
        super("방문 날짜는 숫자여야 합니다");
    }
}
