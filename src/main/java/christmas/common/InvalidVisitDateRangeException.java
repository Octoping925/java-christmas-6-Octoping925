package christmas.common;

public class InvalidVisitDateRangeException extends ChristmasEventException {
    public InvalidVisitDateRangeException() {
        super("방문 날짜는 1일부터 31일까지여야 합니다");
    }
}
