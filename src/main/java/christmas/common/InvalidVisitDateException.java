package christmas.common;

public class InvalidVisitDateException extends ChristmasEventException {
    public InvalidVisitDateException() {
        super("방문 날짜는 1일부터 31일까지의 숫자여야 합니다");
    }
}
